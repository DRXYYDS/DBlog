package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.QueryTagDto;
import com.sangeng.domain.dto.RoleDto;
import com.sangeng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曼迪卡尔多
 * @ClassName RoleController
 * @description: TODO
 * @date 2023年08月03日
 * @version: 1.0
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public ResponseResult RoleList(Integer pageNum, Integer pageSize, RoleDto roleDto){
        return roleService.RoleList(pageNum,pageSize,roleDto);
    }
    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleDto roleDto){
        return roleService.changeStatus(roleDto);
    }
    @GetMapping("{id}")
    public ResponseResult queryRoleById(@PathVariable("id") )
}
