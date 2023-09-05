package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.MenuDto;
import com.sangeng.domain.entity.Menu;
import com.sangeng.domain.vo.MenuByIdVo;
import com.sangeng.domain.vo.MenuListVo;
import com.sangeng.domain.vo.menuVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.MenuMapper;
import com.sangeng.service.MenuService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 18:37:46
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，则获取全部权限(按钮，菜单)
        if(id == 1){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        return menuMapper.selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu(userId);
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult MenuList(String status,String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(status),Menu::getStatus,status);
        queryWrapper.like(StringUtils.hasText(menuName),Menu::getMenuName,menuName);
        queryWrapper.orderByAsc(Menu::getOrderNum);
        queryWrapper.orderByAsc(Menu::getParentId);
        List<Menu> list = list(queryWrapper);
        return ResponseResult.okResult(list);
    }

    @Override
    public ResponseResult AddMenu(MenuDto menuDto) {
        if(!StringUtils.hasText(menuDto.getMenuName())){
            throw new SystemException(AppHttpCodeEnum.MENUNAME_NOT_NULL);
        }
        if(menuDto.getOrderNum() == null){
            throw new SystemException(AppHttpCodeEnum.MENUORDERNUM_NOT_NULL);
        }
        if(!StringUtils.hasText(menuDto.getPath())){
            throw new SystemException(AppHttpCodeEnum.MENUPATH_NOT_NULL);
        }
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
        save(menu);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult UpdateMenu(Menu menu) {
        if(menu.getParentId().equals(menu.getId())){
            throw new SystemException(AppHttpCodeEnum.MENU_UPDATE_ERROR);
        }
        updateById(menu);
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult selectById(Long id) {
        Menu menu = menuMapper.selectById(id);
        MenuByIdVo menuVo = BeanCopyUtils.copyBean(menu, MenuByIdVo.class);

        return ResponseResult.okResult(menuVo);
    }

    @Override
    public ResponseResult DeleteById(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId,id);
        List<Menu> list = list(queryWrapper);
        if(list.size() == 0){
            LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Menu::getId,id);
            updateWrapper.set(Menu::getDelFlag,SystemConstants.Del);
            update(updateWrapper);
            return ResponseResult.okResult();
        }else{
            throw new SystemException(AppHttpCodeEnum.MENU_CHILDREN_EXIST);
        }
    }

    @Override
    public ResponseResult treeselect() {
        List<Menu> menuList = menuMapper.treeSelect();
        List<MenuListVo> menuListVos = BeanCopyUtils.copyBeanList(menuList, MenuListVo.class);
        List<MenuListVo> menuListVos1 = builderMenuTreeVo(menuListVos, 0L);
        // List<Menu> menuTree = builderMenuTree(menuList, 0L);
        return ResponseResult.okResult(menuListVos1);
    }


    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }
    private List<MenuListVo> builderMenuTreeVo(List<MenuListVo> menus, Long parentId) {
        List<MenuListVo> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildrenVo(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
    private List<MenuListVo> getChildrenVo(MenuListVo menu, List<MenuListVo> menus) {
        List<MenuListVo> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildrenVo(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}
