package com.tjnu.club.service.impl;

import com.tjnu.club.api.UserInfoService;
import com.tjnu.club.component.UserInfoComponent;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.service.util.ServiceTransferUtil;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/5 14:49
 * @Description: 用户中心
 */
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
            UserInfo userInfo = ServiceTransferUtil.userVo2Info(userInfoVO);
            Boolean result = userInfoComponent.updateUserInfo(userInfo);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
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
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<UserInfoVO> getUserInfoByUserId(String userId) {
        super.notBlank("用户ID", userId);
        try {
            UserInfo info = userInfoComponent.getUserInfoByUserId(userId);
            UserInfoVO vo = ServiceTransferUtil.userInfo2VO(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    //参数校验
    private void checkParam(UserInfoVO userInfoVO) {
        super.notNull("用户信息", userInfoVO);
    }

}
