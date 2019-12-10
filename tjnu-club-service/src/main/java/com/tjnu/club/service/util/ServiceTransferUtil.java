package com.tjnu.club.service.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tjnu.club.info.CategoryInfo;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.vo.CategoryInfoVO;
import com.tjnu.club.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/7 14:20
 * @Description: 类型转换工具
 */
public class ServiceTransferUtil {

    /**
     * 用户信息 VO 转 Info
     *
     * @param vo
     * @return
     */
    public static UserInfo userVo2Info(UserInfoVO vo) {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    /**
     * 用户信息 Info 转 VO
     *
     * @param info
     * @return
     */
    public static UserInfoVO userInfo2VO(UserInfo info) {
        if (ObjectUtil.isEmpty(info) || StrUtil.isEmpty(info.getUserId())) {
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
        vo.setPassword(null);
        return vo;
    }

    /**
     * 版块信息 Vo 转 Info
     * @param vo
     * @return
     */
    public static CategoryInfo categoryVo2Info(CategoryInfoVO vo) {
        CategoryInfo info = new CategoryInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    public static CategoryInfoVO categoryInfo2Vo(CategoryInfo info){
        if (ObjectUtil.isEmpty(info) || StrUtil.isEmpty(info.getCategoryId())) {
            return null;
        }
        CategoryInfoVO vo = new CategoryInfoVO();
        BeanUtils.copyProperties(info,vo);
        if(info.getGmtCreate()!=null){
            vo.setGmtCreate(info.getGmtCreate().getTime());
        }
        if(info.getGmtModified()!=null){
            vo.setGmtModified(info.getGmtModified().getTime());
        }
        return vo;
    }

}
