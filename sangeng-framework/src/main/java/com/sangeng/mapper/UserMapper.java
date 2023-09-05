package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 08:59:32
 */
@Mapper
@Repository(value = "UserMapper")
public interface UserMapper extends BaseMapper<User> {

}

