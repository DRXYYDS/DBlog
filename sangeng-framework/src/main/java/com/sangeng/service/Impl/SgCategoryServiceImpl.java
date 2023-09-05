package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.SgCategory;
import com.sangeng.domain.vo.CategoryListVo;
import com.sangeng.domain.vo.CategoryVo;
import com.sangeng.mapper.SgCategoryMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.SgCategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(SgCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-07-27 16:17:08
 */
@Service("sgCategoryService")
public class SgCategoryServiceImpl extends ServiceImpl<SgCategoryMapper, SgCategory> implements SgCategoryService {

    @Autowired
    ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布的文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(queryWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = list.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表
        List<SgCategory> categories = listByIds(categoryIds);
        //只留下状态正常的分类
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryListVo>  listAllCategory() {
        List<SgCategory> list = list();
        List<CategoryListVo> categoryListVos = BeanCopyUtils.copyBeanList(list, CategoryListVo.class);
        return categoryListVos;
    }
}
