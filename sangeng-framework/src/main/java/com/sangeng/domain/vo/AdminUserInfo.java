package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName AdminUserInfo
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfo {
    private List<String> permissions;

    private List<String> roles;

    private UserInfoVo user;
}
