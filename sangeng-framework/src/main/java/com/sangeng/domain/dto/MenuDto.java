package com.sangeng.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sangeng.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName MenuDto
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    //菜单名称
    private String menuName;
    //父菜单ID
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //路由地址
    private String path;

    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //菜单状态（0显示 1隐藏）
    private String visible;
    //菜单状态（0正常 1停用）
    private String status;
    //菜单图标
    private String icon;

}
