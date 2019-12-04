package com.tjnu.club.web;

import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.constants.TJNUWeb;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.api.UserInfoService;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/4 9:05
 * @Description: 用户信息管理
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoWeb extends TJNUWeb {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/update")
    public ResultVO<Boolean> updateUserInfo(String token, UserInfoVO userInfoVO) {
        checkParam(userInfoVO);
        super.notNull("用户ID", userInfoVO.getUserId());
        try {
            UserInfo userInfo = vo2Info(userInfoVO);
            Boolean result = userInfoService.updateUserInfo(userInfo);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @PostMapping("/delete")
    public ResultVO<Boolean> deleteUserInfo(String token, String userId) {
        super.notNull("用户ID", userId).notNull("token",token);
        try {
            Boolean result = userInfoService.deleteUserInfo(userId);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    //参数校验
    private void checkParam(UserInfoVO userInfoVO) {
        super.notNull("用户信息", userInfoVO).notBlank("昵称", userInfoVO.getNickName())
                .notBlank("邮箱", userInfoVO.getEmail()).notBlank("密码", userInfoVO.getPassword())
                .notBlank("专业", userInfoVO.getMajor()).notBlank("学院", userInfoVO.getCollege())
                .notBlank("学校", userInfoVO.getUniversity()).notBlank("年级", userInfoVO.getGrade());
    }

    //vo 转 info
    private UserInfo vo2Info(UserInfoVO vo) {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }
}
