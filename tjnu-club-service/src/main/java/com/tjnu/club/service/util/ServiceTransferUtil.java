package com.tjnu.club.service.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tjnu.club.info.BlogInfo;
import com.tjnu.club.info.CategoryInfo;
import com.tjnu.club.info.UserBlogInfo;
import com.tjnu.club.info.UserInfo;
import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.CategoryInfoVO;
import com.tjnu.club.vo.UserBlogInfoVO;
import com.tjnu.club.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

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
     *
     * @param vo
     * @return
     */
    public static CategoryInfo categoryVo2Info(CategoryInfoVO vo) {
        CategoryInfo info = new CategoryInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    public static CategoryInfoVO categoryInfo2Vo(CategoryInfo info) {
        if (ObjectUtil.isEmpty(info) || StrUtil.isEmpty(info.getCategoryId())) {
            return null;
        }
        CategoryInfoVO vo = new CategoryInfoVO();
        BeanUtils.copyProperties(info, vo);
        if (info.getGmtCreate() != null) {
            vo.setGmtCreate(info.getGmtCreate().getTime());
        }
        if (info.getGmtModified() != null) {
            vo.setGmtModified(info.getGmtModified().getTime());
        }
        return vo;
    }

    /**
     * 博客/帖子 VO 转 Info
     *
     * @param vo
     * @return
     */
    public static BlogInfo blogVo2Info(BlogInfoVO vo) {
        BlogInfo info = new BlogInfo();
        BeanUtils.copyProperties(vo, info);
        return info;
    }

    public static BlogInfoVO blogInfo2Vo(BlogInfo info) {
        if (ObjectUtil.isEmpty(info) || StrUtil.isEmpty(info.getBlogId())) {
            return null;
        }
        BlogInfoVO vo = new BlogInfoVO();
        BeanUtils.copyProperties(info, vo);
        if (info.getGmtCreate() != null) {
            vo.setGmtCreate(info.getGmtCreate().getTime());
        }
        if (info.getGmtModified() != null) {
            vo.setGmtModified(info.getGmtModified().getTime());
        }
        return vo;
    }


    public static UserBlogInfoVO userBlogInfo2Vo(UserBlogInfo info) {
        if (ObjectUtil.isEmpty(info)) {
            return null;
        }
        UserInfoVO userInfoVO = Optional.ofNullable(userInfo2VO(info.getUserInfo())).orElse(new UserInfoVO());
        BlogInfoVO blogInfoVO = Optional.ofNullable(blogInfo2Vo(info.getBlogInfo())).orElse(new BlogInfoVO());
        UserBlogInfoVO vo = new UserBlogInfoVO();
        vo.setUserInfoVO(userInfoVO);
        vo.setBlogInfoVO(blogInfoVO);
        return vo;
    }

}
