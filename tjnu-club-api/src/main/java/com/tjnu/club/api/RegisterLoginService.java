package com.tjnu.club.api;

import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 20:23
 * @Description: 登录注册服务
 */
public interface RegisterLoginService {

    /**
     * 用户登录
     *
     * @param nickNameOrEmail 昵称/邮箱
     * @param password
     * @return
     */
    ResultVO<UserInfoVO> login(String nickNameOrEmail, String password);

    /**
     * 用户注册
     *
     * @param userInfoVO
     * @return
     */
    ResultVO<Boolean> register(UserInfoVO userInfoVO);

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    ResultVO<Boolean> logout(String token);

    /**
     * 发送邮箱验证码
     *
     * @param email
     * @return
     */
    ResultVO<Boolean> emailVerify(String email);

}
