package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 曼迪卡尔多
 * @ClassName RoleByIdDto
 * @description: TODO
 * @date 2023年09月05日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleByIdDto {
    private Long id;
    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //显示顺序
    private Integer roleSort;
    //角色状态（0正常 1停用）
    private String status;
    //备注
    private String remark;
}
