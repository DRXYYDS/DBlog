package com.sangeng.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.ArticleDto;
import com.sangeng.domain.dto.ArticleQueryDto;
import com.sangeng.domain.vo.ArticleListVo;
import com.sangeng.domain.vo.ArticleQueryVo;
import com.sangeng.domain.vo.ArticleVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleController
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @PostMapping
    public ResponseResult AddArticle(@RequestBody ArticleDto articleDto){
        return articleService.AddArticle(articleDto);
    }
    @GetMapping("/list")
    public ResponseResult Articlelist(Integer pageNum, Integer pageSize, ArticleDto articleDto){
        PageVo pagevo =  articleService.Articlelist(pageNum,pageSize,articleDto);
        return ResponseResult.okResult(pagevo);
    }
    @GetMapping("/{id}")
    public ResponseResult QueryArticle(@PathVariable("id") Long id){
        ArticleQueryVo queryVo = articleService.QueryArticle(id);
        return ResponseResult.okResult(queryVo);
    }
    @PutMapping
    public ResponseResult UpdateArticle(@RequestBody ArticleQueryDto articleQueryDto){
        return articleService.UpdateArticle(articleQueryDto);
    }
    @DeleteMapping("/{id}")
    public ResponseResult DeleteArticle(@PathVariable("id")List<Long> ids){
        return articleService.DeleteArticle(ids);
    }
}
