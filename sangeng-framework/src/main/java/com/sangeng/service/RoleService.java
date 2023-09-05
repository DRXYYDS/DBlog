package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 18:42:01
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult RoleList(Integer pageNum, Integer pageSize, RoleDto roleDto);

    ResponseResult changeStatus(RoleDto roleDto);
}
