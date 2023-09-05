package com.sangeng.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曼迪卡尔多
 * @ClassName QueryTagDto
 * @description: TODO
 * @date 2023年08月01日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryTagDto {
    //标签名
    private String name;
    //备注
    private String remark;
}
