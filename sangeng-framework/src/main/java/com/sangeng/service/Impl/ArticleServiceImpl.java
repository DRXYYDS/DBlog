package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleQueryDto;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.ArticleTag;
import com.sangeng.domain.entity.SgCategory;
import com.sangeng.domain.vo.*;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.mapper.ArticleTagMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.ArticleTagService;
import com.sangeng.service.SgCategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sangeng.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleServiceImpl
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
        @Autowired
        private SgCategoryService categoryService;
        @Autowired
        private RedisCache redisCache;
        @Autowired
        private ArticleMapper articleMapper;
        @Autowired
        ArticleTagService articleTagService;
        @Autowired
        ArticleTagMapper articleTagMapper;
        @Override
        public ResponseResult hotArticleList() {
                //查询热门文章，封装成ResponseResult返回
                LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
                //必须是正式文章
                queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
                //按照浏览量进行排序
                queryWrapper.orderByDesc(Article::getViewCount);
                //最多只查询十条
                Page<Article> page = new Page(1,10);
                page(page,queryWrapper);
                List<Article> records = page.getRecords();
//                for (Article record : records) {
//                        HotArticleVo hotArticleVo = new HotArticleVo();
//                        BeanUtils.copyProperties(record,hotArticleVo);
//                        articleVos.add(hotArticleVo);
//                }
                List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
                return ResponseResult.okResult(articleVos);
        }

        @Override
        public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
                //查询条件
                //如果有categoryId,就要，查询时要跟传入的相同
                //状态时正式发布的
                //对isTop进行降序排序
                LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId)
                                .eq(Article::getStatus, ARTICLE_STATUS_NORMAL)
                                        .orderByDesc(Article::getIsTop);
                //分页查询
                Page<Article> page = new Page<>(pageNum,pageSize);
                 page(page, queryWrapper);
                 //查询categoryName
                List<Article> articles = page.getRecords();
                articles = articles.stream()
                        .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                        .collect(Collectors.toList());
                //articleId去查询articlesName进行设置
//                for (Article article : articles) {
//                        SgCategory category = categoryService.getById(article.getCategoryId());
//                        article.setCategoryName(category.getName());
//                }
                List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
                PageVo pageVo = new PageVo(articleListVos,page.getTotal());

                return ResponseResult.okResult(pageVo);
        }

        @Override
        public ResponseResult getArticleDetail(Long id) {
                //根据id查询文章
                Article article = getById(id);
                //从redis中获取viewCount
                Integer viewCount = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT, id.toString());
                article.setViewCount(viewCount.longValue());
                //转换成VO
                ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
                //根据分类id查询分类名
                SgCategory category = categoryService.getById(articleDetailVo.getCategoryId());
                if(category!=null){
                        articleDetailVo.setCategoryName(category.getName());
                }
                //封装响应返回
                return ResponseResult.okResult(articleDetailVo);
        }

        @Override
        public ResponseResult updateViewCount(Long id) {
                //更新redis中对应id的浏览量
                redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString(),1);
                return ResponseResult.okResult();
        }

        @Override
        public ResponseResult AddArticle(ArticleDto articleDto) {
                Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
                save(article);
                List<Long> tags = articleDto.getTags();
                List<ArticleTag> articleTagList = tags.stream()
                        .map(tag -> new ArticleTag(article.getId(), tag))
                        .collect(Collectors.toList());
                articleTagService.saveBatch(articleTagList);
                return ResponseResult.okResult();
        }

        @Override
        public PageVo Articlelist(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
                LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.like(StringUtils.hasText(articleDto.getTitle()),Article::getTitle,articleDto.getTitle());
                queryWrapper.like(StringUtils.hasText(articleDto.getSummary()),Article::getSummary,articleDto.getSummary());
                queryWrapper.eq(Article::getDelFlag,SystemConstants.NO_Del);
                Page<Article> page = new Page<>(pageNum,pageSize);
                page(page, queryWrapper);
                List<Article> articles = page.getRecords();
                List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);
                PageVo pageVo = new PageVo(articleVos, page.getTotal());
                return pageVo;
        }

        @Override
        public ArticleQueryVo QueryArticle(Long id) {
                Article article = getById(id);
                if(article == null){
                        throw new SystemException(AppHttpCodeEnum.ARTICLE_NOT_EXIST);
                }
                List<String> list = articleTagService.selectById(id);
                ArticleQueryVo queryVo = BeanCopyUtils.copyBean(article, ArticleQueryVo.class);
                queryVo.setTags(list);
                return queryVo;
        }

        @Override
        public ResponseResult UpdateArticle(ArticleQueryDto articleQueryDto) {
                Article article = BeanCopyUtils.copyBean(articleQueryDto, Article.class);
                updateById(article);
                LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ArticleTag::getArticleId,article.getId());
                //首先删除文章对应的标签
                articleTagService.remove(queryWrapper);
                //将标签转为Long类型
                List<String> tags = articleQueryDto.getTags();
                List<Long> lists = tags.stream()
                        .map(tag -> Long.parseLong(tag))
                        .collect(Collectors.toList());
                //逐个添加
                for (Long list : lists) {
                        ArticleTag articleTag = new ArticleTag();
                        articleTag.setArticleId(article.getId());
                        articleTag.setTagId(list.longValue());
                        articleTagService.save(articleTag);
                }
                return ResponseResult.okResult();
        }

        @Override
        public ResponseResult DeleteArticle(List<Long> ids) {
                for (Long id : ids) {
                        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
                        updateWrapper.eq(Article::getId,id);
                        updateWrapper.set(Article::getDelFlag,SystemConstants.Del);
                        boolean update = update(updateWrapper);
                        if(!update){
                                throw new SystemException(AppHttpCodeEnum.ARTICLE_UPDATE_ERROR);
                        }
                }
                return ResponseResult.okResult();
        }
}
