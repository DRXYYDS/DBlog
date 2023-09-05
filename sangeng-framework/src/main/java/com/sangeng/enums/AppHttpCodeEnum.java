package com.sangeng.enums;

/**
 * @author 曼迪卡尔多
 * @ClassName AppHttpCodeEnum
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONENT_NOT_NULL(506,"评论内容不能为空" ),
    USER_UPDATE_ERROR(508,"用户信息更新失败"),
    USERNAME_NOT_NULL(509,"用户名不能为空"),
    NICKNAME_NOT_NULL(510,"昵称不能为空"),
    PASSWORD_NOT_NULL(511,"密码不能为空"),
    EMAIL_NOT_NULL(512,"邮箱不能为空"),
    NICKNAME_EXIST(513,"昵称已存在"),
    TAGNAME_NOT_NULL(514,"标签名称不能为空" ),
    TAGREMARK_NOT_NULL(515,"标签备注不能为空" ),
    TAG_UPDATE_ERROR(516,"标签更新失败" ),
    TAG_INSERT_ERROR(517,"标签插入失败" ),
    TAG_NOT_NULL(518,"标签不能为空" ),
    TAGID_NOT_NULL(519,"标签不能为空" ),
    FILE_TYPE_ERROR(507,"文件类型错误,请上传图片文件" ),
    ARTICLE_NOT_EXIST(520, "该文章不存在"),
    ARTICLE_UPDATE_ERROR(521,"文章更新失败" ),
    MENUNAME_NOT_NULL(522,"菜单名称不能为空" ),
    MENUORDERNUM_NOT_NULL(523,"菜单显示顺序不能为空" ),
    MENUPATH_NOT_NULL(524,"菜单路由地址不能为空" ),
    MENU_UPDATE_ERROR(525,"上级菜单不能选择自己" ),
    MENU_CHILDREN_EXIST(526,"存在子菜单，不允许删除" );

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
