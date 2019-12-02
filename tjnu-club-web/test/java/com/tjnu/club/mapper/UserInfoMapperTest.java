package com.tjnu.club.mapper;

import com.alibaba.fastjson.JSON;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.info.UserInfo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserInfoMapperTest extends TJNUClubTest {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void listUserInfo() {
        List<UserInfo> userInfoList = userInfoMapper.listUserInfo();
        System.out.println(JSON.toJSON(userInfoList));
    }
}