package com.sangeng.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    public static final String STATUS_NORMAL = "0";
    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 跟评论
     */
    public static final String ROOT_COMMENT = "-1";

    public static final Long TO_COMMENT_ID = Long.valueOf(-1);
    /**
     * 评论类型：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友链评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     * 将浏览数量存入redis 的键
     */
    public static final String ARTICLE_VIEW_COUNT = "article:viewCount";
    /**
     * 后台系统JWT的KEY
     */
    public static final String BACKBROUND_JWT_KEY = "login:";
    /**
     * 菜单权限
     */
    public static final String MENU = "C";
    /**
     * 按钮权限
     */
    public static final String BUTTON = "F";
    /**
     * 未删除
     */
    public static final int NO_Del = 0;
    /**
     * 删除
     */
    public static final int Del = 1;

    /**
     * 是否为管理员
     */
    public static final String ADMIN = "1";
}