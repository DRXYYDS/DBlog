package com.sangeng.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleListVo
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名字
    private String categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;

    private Date createTime;
}
