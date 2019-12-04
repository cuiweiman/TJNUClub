package com.tjnu.club.component;

import com.tjnu.club.info.UserInfo;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 9:10
 * @Description: 注册和登录信息
 */
public interface RegisterLoginComponent {

    /**
     * 用户登录
     *
     * @param nickNameOrEmail
     * @param password
     * @return
     */
    UserInfo login(String nickNameOrEmail, String password);

    /**
     * 用户注册
     *
     * @param code
     * @param info
     * @return
     */
    Boolean register(String code, UserInfo info);

    /**
     * 用户登出
     *
     * @return
     */
    Boolean logout(String token);

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @return
     */
    Boolean emailVerify(String email);

}
