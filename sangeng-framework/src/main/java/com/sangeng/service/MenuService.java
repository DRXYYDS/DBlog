package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.MenuDto;
import com.sangeng.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 18:37:45
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult MenuList(String status,String menuName);

    ResponseResult AddMenu(MenuDto menuDto);

    ResponseResult UpdateMenu(Menu menu);

    ResponseResult selectById(Long id);

    ResponseResult DeleteById(Long id);

    ResponseResult treeselect();
}
