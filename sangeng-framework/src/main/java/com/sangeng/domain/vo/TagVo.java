package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 曼迪卡尔多
 * @ClassName TagVo
 * @description: TODO
 * @date 2023年08月01日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    private Long id;
    //标签名
    private String name;

    //备注
    private String remark;
}
