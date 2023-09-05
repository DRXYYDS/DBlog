package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.entity.ArticleTag;

import java.util.List;


/**
 * 文章标签关联表(ArticleTag)表服务接口
 *
 * @author makejava
 * @since 2023-08-02 10:21:54
 */
public interface ArticleTagService extends IService<ArticleTag> {

    List<String> selectById(Long id);

}
