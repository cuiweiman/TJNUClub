package com.tjnu.club.service;

import com.tjnu.club.info.UserInfo;

import java.util.List;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/2 15:28
 * @Description: 用户信息
 */
public interface UserInfoService {

    /**
     * 保存用户信息
     *
     * @param userInfo
     * @return
     */
    Boolean saveUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    Boolean updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    Boolean deleteUserInfo(String userId);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfoByUserId(String userId);

    /**
     * 根据用户昵称获取用户信息
     * @param nickName
     * @return
     */
    UserInfo getUserInfoByNickName(String nickName);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    UserInfo getUserInfoByEmail(String email);

    /**
     * 查询用户信息列表
     *
     * @param page
     * @param size
     * @return
     */
    List<UserInfo> listUserInfo(Integer page, Integer size);


}
