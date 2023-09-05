package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.LoginUser;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.entity.User;
import com.sangeng.domain.vo.AdminUserInfo;
import com.sangeng.domain.vo.RoutersVo;
import com.sangeng.domain.vo.UserInfoVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.service.LoginService;
import com.sangeng.service.MenuService;
import com.sangeng.service.RoleService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.RedisCache;
import com.sangeng.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName LoginController
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisCache redisCache;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        Long id = user.getId();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(id);
        //根据用户id查询角色信息
          List<String> roleKeyList = roleService.selectRoleKeyByUserId(id);
                //获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfo adminUserInfo = new AdminUserInfo(perms,roleKeyList,userInfoVo);

        return ResponseResult.okResult(adminUserInfo);
    }
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //获取userId
        Long userId = SecurityUtils.getUserId();
        //查询Menu，结果时tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
