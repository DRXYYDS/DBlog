package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曼迪卡尔多
 * @ClassName LinkController
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@RestController
@RequestMapping("link")
public class LinkController {

    @Autowired
    LinkService linkService;
    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
