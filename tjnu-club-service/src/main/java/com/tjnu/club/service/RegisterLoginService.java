package com.tjnu.club.service;

import com.tjnu.club.info.UserInfo;

public interface RegisterLoginService {

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
     * 发送邮箱验证码
     *
     * @param email
     * @return
     */
    Boolean emailVerify(String email);

}
