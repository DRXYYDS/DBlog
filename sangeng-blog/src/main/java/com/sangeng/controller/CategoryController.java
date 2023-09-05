package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.SgCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author 曼迪卡尔多
 * @ClassName CategoryController
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private SgCategoryService categoryService;
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();

    }
}
