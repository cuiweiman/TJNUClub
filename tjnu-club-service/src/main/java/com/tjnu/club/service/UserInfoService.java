package com.tjnu.club.service;

import com.tjnu.club.info.UserInfo;

import java.util.List;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/2 15:28
* @Description: 用户信息
*/
public interface UserInfoService {

    List<UserInfo> listUserInfo(Integer page,Integer size);

}
