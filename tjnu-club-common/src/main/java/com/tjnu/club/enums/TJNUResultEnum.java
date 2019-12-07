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

    //系统错误码 0-1000
    PARAM_ERROR(460, "参数错误"),
    SYSTEM_ERROR(500, "系统错误"),
    UNSUPPORTED_REQUEST_BODY(500, "请求类型不支持"),
    BAD_GATEWAY(502, "网关异常"),
    API_NAME_ERROR(510, "接口不存在异常"),
    TJNU_TOKEN_FAILURE(520,"TJNUToken不能为空"),

    /**
     * 用户注册 错误码 1000-1020
     */
    NICK_NAME_REPEAT(1000, "用户昵称被使用"),
    EMAIL_REPEAT(1001, "邮箱已被使用"),
    SAVE_USER_ERROR(1002, "用户保存失败"),
    USER_NOT_EXISTS(1003, "该用户不存在"),
    UPDATE_USER_ERROR(1004, "用户更新失败"),
    DELETE_USER_ERROR(1005, "用户删除失败"),
    VERIFY_SEND_FAILURE(1006, "邮箱验证码发送失败"),
    VERIFY_SAVE_FAILURE(1007, "邮箱验证码获取失败"),
    VERIFY_CODE_ERROR(1008, "邮箱验证码输入错误"),
    USER_PASSWORD_ERROR(1009, "用户名或密码输入错误"),
    USER_NOT_LOG_IN(1010, "用户尚未登录"),
    LOG_OUT_ERROR(1010, "登出失败，用户信息错误")



    ;

    private Integer code;

    private String message;

    TJNUResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
