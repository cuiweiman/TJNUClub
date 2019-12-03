package com.tjnu.club.web;

import cn.hutool.core.util.StrUtil;
import com.tjnu.club.constants.TJNUConstants;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.service.UserInfoService;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoWeb extends TJNUWeb {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/save")
    public ResultVO<Boolean> saveUserInfo(UserInfoVO userInfoVO) {
        checkParam(userInfoVO);
        try {
            UserInfo userInfo = vo2Info(userInfoVO);
            Boolean result = userInfoService.saveUserInfo(userInfo);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg(), Boolean.FALSE);
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg(), Boolean.FALSE);
        }
    }

    @PostMapping("/update")
    public ResultVO<Boolean> updateUserInfo(UserInfoVO userInfoVO) {
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
    public ResultVO<Boolean> deleteUserInfo(String userId) {
        super.notNull("用户ID", userId);
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

    @PostMapping("/get")
    public ResultVO<UserInfoVO> getUserInfoByUserId(String userId) {
        super.notNull("用户ID", userId);
        try {
            UserInfo info = userInfoService.getUserInfoByUserId(userId);
            UserInfoVO vo = info2Vo(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg());
        }
    }

    @PostMapping("/list")
    public ResultVO<PageInfoVO> listUserInfo(Integer page, Integer size) {
        super.checkPage(page, size);
        try {
            Integer currentPage = (page - 1) * size;
            Map<String, Object> map = userInfoService.listUserInfo(currentPage, size);
            Long count = (Long) map.get("count");
            List<UserInfo> infoList = (List<UserInfo>) map.get("data");
            if (count == 0L || infoList.size() == 0) {
                return new ResultVO<>(new PageInfoVO());
            }
            List<UserInfoVO> vos = infoList.stream().map(item -> info2Vo(item)).collect(Collectors.toList());
            PageInfoVO<UserInfoVO> pageInfoVO = new PageInfoVO<>(count, vos);
            return new ResultVO<>(pageInfoVO);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e.getCode(), e.getMsg());
        } catch (Exception e) {
            return new ResultVO<>(TJNUConstants.SYSTEM_ERROR.getCode(), TJNUConstants.SYSTEM_ERROR.getMsg());
        }
    }


    //参数校验
    private void checkParam(UserInfoVO userInfoVO) {
        super.notNull("用户信息", userInfoVO).notBlank("昵称", userInfoVO.getNickName())
                .notBlank("邮箱", userInfoVO.getEmail()).notBlank("密码", userInfoVO.getPassword())
                .notBlank("专业", userInfoVO.getMajor()).notBlank("学院", userInfoVO.getCollege())
                .notBlank("学校", userInfoVO.getUniversity()).notBlank("年级", userInfoVO.getGrade());
    }

    private UserInfo vo2Info(UserInfoVO vo) {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    private UserInfoVO info2Vo(UserInfo info) {
        if (ObjectUtils.isEmpty(info) || StrUtil.isEmpty(info.getUserId())) {
            return null;
        }
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(info, vo);
        if (info.getGmtCreate() != null) {
            vo.setGmtCreate(info.getGmtCreate().getTime());
        }
        if (info.getGmtModified() != null) {
            vo.setGmtModified(info.getGmtModified().getTime());
        }
        if (info.getLastLoginTime() != null) {
            vo.setLastLoginTime(info.getLastLoginTime().getTime());
        }
        return vo;
    }

}
