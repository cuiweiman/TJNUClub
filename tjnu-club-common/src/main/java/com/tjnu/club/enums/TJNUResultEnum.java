package com.tjnu.club.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/7 11:26
 * @Description: 返回码 和 返回信息
 */
@Getter
public enum TJNUResultEnum implements Serializable {

    /**
     * 系统错误码 0-1000
     */
    SYSTEM_PARAM_ERROR(460, "参数错误"),
    SYSTEM_ERROR(500, "系统错误"),
    SYSTEM_UNSUPPORTED_REQUEST_BODY(500, "请求类型不支持"),
    SYSTEM_BAD_GATEWAY(502, "网关异常"),
    SYSTEM_API_NAME_ERROR(510, "接口不存在异常"),
    SYSTEM_TOKEN_FAILURE(520, "TJNUToken不能为空"),
    UPLOAD_IMG_EMPTY(550,"上传的图片为空"),
    UPLOAD_IMG_NAME_INVALID(551,"上传图片名称非法"),
    UPLOAD_IMG_TYPE_INVALID(552,"上传图片的类型不合法"),
    UPLOAD_IMG_FAILURE(553,"上传图片失败"),
    DOWN_IMG_KEY_FAILURE(555,"图片的key错误，下载失败"),
    DOWN_IMG_FAILURE(555,"图片下载失败"),

    /**
     * 用户注册 错误码 1000-1020
     */
    USER_NICKNAME_REPEATED(1000, "用户昵称被使用"),
    USER_EMAIL_REPEATED(1001, "邮箱已被使用"),
    USER_SAVE_FAILURE(1002, "用户保存失败"),
    USER_NOT_EXISTS(1003, "该用户不存在"),
    USER_UPDATE_FAILURE(1004, "用户更新失败"),
    USER_DELETE_FAILURE(1005, "用户删除失败"),
    USER_VERIFY_SEND_FAILURE(1006, "邮箱验证码发送失败"),
    USER_VERIFY_SAVE_FAILURE(1007, "邮箱验证码获取失败"),
    USER_VERIFY_CODE_ERROR(1008, "邮箱验证码输入错误"),
    USER_PASSWORD_ERROR(1009, "用户名或密码输入错误"),
    USER_NOT_LOG_IN(1010, "用户尚未登录"),
    USER_LOGOUT_ERROR(1010, "用户登出失败"),
    USER_LOGIN_ERROR(1011,"用户登陆失败"),
    USER_UPDATE_LOGIN_ERROR(1012,"更新用户登录信息失败"),

    /**
     * 版块信息 错误码 1021-1040
     */
    CATEGORY_NAME_REPEATED(1021, "版块名称重复"),
    CATEGORY_SAVE_FAILURE(1022, "版块信息保存失败"),
    CATEGORY_NOT_EXISTS(1023,"该版块信息不存在"),
    CATEGORY_UPDATE_FAILURE(1024,"版块信息修改失败"),
    CATEGORY_DELETE_FORBIDDEN(1025,"该板块下有子版块或者文章，禁止删除"),
    CATEGORY_DELETE_FAILURE(1026,"版块信息删除失败"),
    CATEGORY_HAS_COLLECTED(1027,"该板块已收藏"),
    CATEGORY_COLLECTED_FAILURE(1028,"版块收藏失败"),
    CATEGORY_HAS_NOT_COLLECTED(1029,"该板块尚未被收藏"),
    CATEGORY_CANCEL_COLLECTED_FAILURE(1030,"该板块取消收藏失败"),
    CATEGORY_DELETE_CANNOT(1031,"该板块下有帖子，不能删除"),

    /**
     * 帖子 错误码 1041-1060
     */
    BLOG_NAME_REPEATED(1041,"帖子名称已存在"),
    BLOG_SAVE_FAILURE(1042,"帖子信息保存失败"),
    BLOG_NOT_EXISTS(1043,"该帖子信息不存在"),
    BLOG_UPDATE_FAILURE(1044,"帖子信息修改失败"),
    BLOG_DELETE_FAILURE(1045,"帖子信息删除失败"),
    BLOG_HAS_COLLECTED(1046,"该帖子已收藏"),
    BLOG_COLLECTED_FAILURE(1047,"帖子收藏失败"),
    BLOG_HAS_NOT_COLLECTED(1048,"该帖子尚未被收藏"),
    BLOG_CANCEL_COLLECTED_FAILURE(1049,"该帖子取消收藏失败"),

    ;

    private Integer code;

    private String message;

    TJNUResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
