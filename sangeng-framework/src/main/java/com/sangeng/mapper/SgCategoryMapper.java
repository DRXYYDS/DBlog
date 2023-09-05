package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.SgCategory;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(SgCategory)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-27 16:17:07
 */
@Mapper
public interface SgCategoryMapper extends BaseMapper<SgCategory> {

}

