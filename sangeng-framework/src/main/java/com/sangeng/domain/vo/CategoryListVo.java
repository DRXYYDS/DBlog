package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曼迪卡尔多
 * @ClassName CategoryListVo
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListVo {
    private Long id;
    private String name;
    private String description;
}
