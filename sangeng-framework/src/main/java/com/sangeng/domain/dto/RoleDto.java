package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曼迪卡尔多
 * @ClassName RoleDto
 * @description: TODO
 * @date 2023年08月03日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String roleId;
    private String RoleName;
    private String status;
}
