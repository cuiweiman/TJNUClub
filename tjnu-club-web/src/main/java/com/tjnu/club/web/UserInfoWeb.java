package com.tjnu.club.web;

import com.tjnu.club.info.UserInfo;
import com.tjnu.club.service.UserInfoService;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoWeb {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/list")
    public ResultVO<PageInfoVO> list(Integer page, Integer size){
        List<UserInfo> userInfoList = userInfoService.listUserInfo(page, size);
        PageInfoVO<UserInfo> result = new PageInfoVO<>(userInfoList);
        return new ResultVO<>(result);
    }

}
