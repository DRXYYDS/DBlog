package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-27 21:54:56
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

