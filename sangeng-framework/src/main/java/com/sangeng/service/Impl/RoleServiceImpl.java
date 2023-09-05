package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.domain.entity.Role;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.RoleListVo;
import com.sangeng.mapper.RoleMapper;
import com.sangeng.service.RoleService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 18:42:02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否管理员，如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询这个用户所对应的角色信息

        return roleMapper.selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult RoleList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(StringUtils.hasText(roleDto.getStatus()),Role::getStatus,roleDto.getStatus());
        queryWrapper.like(StringUtils.hasText(roleDto.getRoleName()),Role::getRoleName,roleDto.getRoleName());
        queryWrapper.orderByAsc(Role::getRoleSort);
        Page<Role> page = new Page(pageNum, pageSize);
        page(page,queryWrapper);
        List<Role> records = page.getRecords();
        List<RoleListVo> roleListVos = BeanCopyUtils.copyBeanList(records, RoleListVo.class);
        PageVo pageVo = new PageVo(roleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleDto roleDto) {
        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper();
        updateWrapper.eq(Role::getId,Long.parseLong(roleDto.getRoleId()));
        updateWrapper.set(Role::getStatus,roleDto.getStatus());
        update(updateWrapper);
        return ResponseResult.okResult();
    }
}
