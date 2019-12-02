package com.tjnu.club.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.vo.PageInfoVO;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class UserInfoServiceTest extends TJNUClubTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void listUserInfo() {
        List<UserInfo> list = userInfoService.listUserInfo(1,1);

        System.out.println(JSON.toJSON(list));
    }
}