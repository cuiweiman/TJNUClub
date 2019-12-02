package com.tjnu.club.constants;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/2 17:58
 * @Description: 常量类
 */
public class TJNUConstants {

    public static TJNUCode SUCCESS = new TJNUCode(200, "成功");
    public static TJNUCode REQUEST_ERROR = new TJNUCode(400, "请求错误");
    public static TJNUCode PARAM_ERROR = new TJNUCode(460, "参数错误");
    public static TJNUCode SYSTEM_ERROR = new TJNUCode(500, "系统错误");


    /**
     * 用户注册错误码 1000-1010
     */
    public static TJNUCode NICK_NAME_REPEAT = new TJNUCode(1000, "用户昵称被使用");
    public static TJNUCode EMAIL_REPEAT = new TJNUCode(1001, "邮箱已被使用");
    public static TJNUCode SAVE_USER_ERROR = new TJNUCode(1002, "用户保存失败");
    public static TJNUCode USER_NOT_EXISTS = new TJNUCode(1003, "该用户不存在");
    public static TJNUCode UPDATE_USER_ERROR = new TJNUCode(1004, "用户更新失败");
    public static TJNUCode DELETE_USER_ERROR = new TJNUCode(1004, "用户删除失败");



}
