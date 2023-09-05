package com.sangeng.domain.vo;

import com.sangeng.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName RoutersVo
 * @description: TODO
 * @date 2023年08月01日
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {
    private List<Menu> menus;
}
