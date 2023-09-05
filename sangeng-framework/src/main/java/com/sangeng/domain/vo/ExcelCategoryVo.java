package com.sangeng.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曼迪卡尔多
 * @ClassName ExcelCategoryVo
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCategoryVo {
    @ExcelProperty("分类名")
    private String name;
    @ExcelProperty("描述")
    private String description;
    @ExcelProperty("状态:0正常,1禁用")
    private String status;
}
