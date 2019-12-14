package com.tjnu.club.service;

import com.alibaba.fastjson.JSON;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.api.RegisterLoginService;
import com.tjnu.club.vo.ResultVO;
import org.junit.Test;

import javax.annotation.Resource;

public class RegisterLoginServiceTest extends TJNUClubTest {

    @Resource
    private RegisterLoginService service;

    @Test
    public void emailVerify(){
        String email = "1287024833@qq.com";
        ResultVO<Boolean> result = service.emailVerify(email);
        System.out.println(JSON.toJSON(result));
    }



}
