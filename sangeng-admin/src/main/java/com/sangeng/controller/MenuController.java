package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.MenuDto;
import com.sangeng.domain.entity.Menu;
import com.sangeng.service.MenuService;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曼迪卡尔多
 * @ClassName MenuController
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    public ResponseResult MenuList(@RequestParam(value = "status",required = false) String status,@RequestParam(value = "menuName",required = false) String menuName){
        return menuService.MenuList(status,menuName);
    }
    @PostMapping
    public ResponseResult AddMenu(@RequestBody MenuDto menuDto){
        return menuService.AddMenu(menuDto);
    }
    @PutMapping
    public ResponseResult UpdateMenu(@RequestBody Menu menu){
        return menuService.UpdateMenu(menu);
    }
    @GetMapping("/{id}")
    public ResponseResult selectById(@PathVariable("id") Long id){
        return menuService.selectById(id);
    }
    @DeleteMapping("/{menuid}")
    public ResponseResult DeleteById(@PathVariable("menuid") Long id){
        return menuService.DeleteById(id);
    }
    @GetMapping("/treeselect")
    public ResponseResult treeselect(){
        return menuService.treeselect();
    }
}
