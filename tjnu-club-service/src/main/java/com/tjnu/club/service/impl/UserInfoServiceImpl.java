package com.tjnu.club.service.impl;

import com.tjnu.club.api.UserInfoService;
import com.tjnu.club.component.UserInfoComponent;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserInfoServiceImpl extends TJNUService implements UserInfoService {

    @Resource
    private UserInfoComponent userInfoComponent;

    @Override
    public ResultVO<Boolean> updateUserInfo(UserInfoVO userInfoVO) {
        checkParam(userInfoVO);
        super.notBlank("用户ID", userInfoVO.getUserId());
        try {
            UserInfo userInfo = vo2Info(userInfoVO);
            Boolean result = userInfoComponent.updateUserInfo(userInfo);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @Override
    public ResultVO<Boolean> deleteUserInfo(String userId) {
        super.notBlank("用户ID", userId);
        try {
            Boolean result = userInfoComponent.deleteUserInfo(userId);
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
