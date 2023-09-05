package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;

/**
 * @author 曼迪卡尔多
 * @ClassName LoginService
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult getInfo();

    ResponseResult logout();

}
