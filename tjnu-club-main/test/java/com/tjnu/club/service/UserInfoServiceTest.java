package com.tjnu.club.service;

import com.alibaba.fastjson.JSON;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.api.UserInfoService;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import org.junit.Test;

import javax.annotation.Resource;

public class UserInfoServiceTest extends TJNUClubTest {

    @Resource
    private UserInfoService userInfoService;


    @Test
    public void getUserInfoByUserId(){
        String userId = "1000";
        ResultVO<UserInfoVO> user = userInfoService.getUserInfoByUserId(userId);
        System.out.println(JSON.toJSON(user));
    }

}