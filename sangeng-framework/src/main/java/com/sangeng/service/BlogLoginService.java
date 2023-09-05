package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author 曼迪卡尔多
 * @ClassName BlogLoginService
 * @description: TODO
 * @date 2023年07月28日
 * @version: 1.0
 */
public interface BlogLoginService  {
    ResponseResult login(User user);

    ResponseResult logout();

}
