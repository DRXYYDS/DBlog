package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曼迪卡尔多
 * @ClassName TagListVo
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListVo {
    private Long id;
    //标签名
    private String name;
}
