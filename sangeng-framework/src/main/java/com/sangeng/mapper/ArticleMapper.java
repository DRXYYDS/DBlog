package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleMapper
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
