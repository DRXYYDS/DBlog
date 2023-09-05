package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName MenuListVo
 * @description: TODO
 * @date 2023年08月03日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MenuListVo {
    private Long id;
    private String label;
    private Long parentId;
    private List<MenuListVo> children;

}
