package com.sangeng.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName ArticleQueryVo
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryVo {
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //创建人
    private Long createBy;

    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;
    //创建时间
    private Date createTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //标签
    private List<String> tags;
}
