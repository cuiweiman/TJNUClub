package com.tjnu.club.constants;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/2 17:58
 * @Description: 常量/常量类
 */
public class TJNUConstants {

    public static final String VERIFY_CODE_KEY_PREFIX = "VERIFY_CODE_";
    public static final String VERIFY_CODE_KEY_SUFIX = "_20191201";
    public static final String TOKEN_KEY_PREFIX = "LOGIN_TOKEN_";
    public static final String TOKEN_KEY_SUFIX = "_20191201";
    public static final Integer TOKEN_EFFECTIVE_TIME = 7200; //token有效期为2小时


    /**
     * 系统错误码 0-1000
     */
    public static TJNUCode PARAM_ERROR = new TJNUCode(460, "参数错误");
    public static TJNUCode SYSTEM_ERROR = new TJNUCode(500, "系统错误");
    public static TJNUCode UNSUPPORTED_REQUEST_BODY = new TJNUCode(500, "请求类型不支持");
    public static TJNUCode BAD_GATEWAY = new TJNUCode(502, "网关异常");
    public static TJNUCode API_NAME_ERROR = new TJNUCode(510, "接口不存在异常");


    /**
     * 用户注册 错误码 1000-1020
     */
    public static TJNUCode NICK_NAME_REPEAT = new TJNUCode(1000, "用户昵称被使用");
    public static TJNUCode EMAIL_REPEAT = new TJNUCode(1001, "邮箱已被使用");
    public static TJNUCode SAVE_USER_ERROR = new TJNUCode(1002, "用户保存失败");
    public static TJNUCode USER_NOT_EXISTS = new TJNUCode(1003, "该用户不存在");
    public static TJNUCode UPDATE_USER_ERROR = new TJNUCode(1004, "用户更新失败");
    public static TJNUCode DELETE_USER_ERROR = new TJNUCode(1005, "用户删除失败");
    public static TJNUCode VERIFY_SEND_FAILURE = new TJNUCode(1006, "邮箱验证码发送失败");
    public static TJNUCode VERIFY_SAVE_FAILURE = new TJNUCode(1007, "邮箱验证码获取失败");
    public static TJNUCode VERIFY_CODE_ERROR = new TJNUCode(1008, "邮箱验证码输入错误");
    public static TJNUCode USER_PASSWORD_ERROR = new TJNUCode(1009, "用户名或密码输入错误");
    public static TJNUCode USER_NOT_LOG_IN = new TJNUCode(1010, "用户尚未登录");
    public static TJNUCode LOG_OUT_ERROR = new TJNUCode(1010, "登出失败，用户信息错误");


}
