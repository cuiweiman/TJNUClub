package com.tjnu.club.api;

import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;

/**
* @Author: WeiMan Cui
* @Date: 2019/12/4 20:40
* @Description: 用户信息服务
*/
public interface UserInfoService {

    ResultVO<Boolean> updateUserInfo(UserInfoVO userInfoVO);

    ResultVO<Boolean> deleteUserInfo(String userId);

}
