package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleQueryDto;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.ArticleQueryVo;
import com.sangeng.domain.vo.PageVo;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleService
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult AddArticle(ArticleDto articleDto);

    PageVo Articlelist(Integer pageNum, Integer pageSize, ArticleDto articleDto);

    ArticleQueryVo QueryArticle(Long id);

    ResponseResult UpdateArticle(ArticleQueryDto articleQueryDto);

    ResponseResult DeleteArticle(List<Long> id);
}
