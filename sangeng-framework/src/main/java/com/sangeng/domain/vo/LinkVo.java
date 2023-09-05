package com.sangeng.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 曼迪卡尔多
 * @ClassName LinkVo
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;


    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;

}
