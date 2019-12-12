package com.tjnu.club.component.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tjnu.club.component.BlogInfoComponent;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.BlogInfo;
import com.tjnu.club.info.UserBlogInfo;
import com.tjnu.club.mapper.BlogInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class BlogInfoComponentImpl implements BlogInfoComponent {

    @Resource
    private BlogInfoMapper blogInfoMapper;


    @Override
    public Boolean saveBlogInfo(BlogInfo blogInfo) {
        // 校验 帖子名称 是否重复
        BlogInfo query = getBlogInfoByBlogName(null, blogInfo.getBlogName());
        if (ObjectUtil.isNotEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.BLOG_NAME_REPEATED);
        }
        Integer result = blogInfoMapper.saveBlogInfo(blogInfo);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.BLOG_NAME_REPEATED);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateBlogInfo(BlogInfo blogInfo) {
        // 校验帖子是否存在
        BlogInfo query = getBlogInfoByBlogId(blogInfo.getBlogId());
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.BLOG_NOT_EXISTS);
        }
        //校验帖子名称是否有重复
        BlogInfo query2 = getBlogInfoByBlogName(blogInfo.getBlogId(), blogInfo.getBlogName());
        if (ObjectUtil.isNotEmpty(query2)) {
            throw new TJNUException(TJNUResultEnum.BLOG_NAME_REPEATED);
        }
        Integer result = blogInfoMapper.updateBlogInfo(blogInfo);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.BLOG_UPDATE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteBlogInfo(String blogId) {
        // 校验帖子是否存在
        BlogInfo query = getBlogInfoByBlogId(blogId);
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.BLOG_NOT_EXISTS);
        }
        Integer result = blogInfoMapper.deleteBlogInfo(blogId);
        if (result == 0) {
            throw new TJNUException(TJNUResultEnum.BLOG_DELETE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Map<String, Object> listMainBlogInfoByCategoryId(String categoryId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Long count = Optional.ofNullable(blogInfoMapper.countMainBlogInfoByCategoryId(categoryId)).orElse(0L);
        List<BlogInfo> infoList = Optional.ofNullable(blogInfoMapper.listMainBlogInfoByCategoryId(categoryId, currentPage, pageSize)).orElse(new ArrayList<>());
        map.put("count", count);
        map.put("data", infoList);
        return map;
    }

    @Override
    public List<BlogInfo> listBlogInfoTopN(Integer topN) {
        long current = System.currentTimeMillis();    //当前时间毫秒数
        long beginT = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
        long endT = beginT + 24 * 60 * 60 * 1000;  //明天0点0分0秒的毫秒数
        Date begin = new Date(beginT);
        Date end = new Date(endT);
        List<BlogInfo> infoList = Optional.ofNullable(blogInfoMapper.listBlogInfoTopN(topN, begin, end)).orElse(new ArrayList<>());
        return infoList;
    }

    @Override
    public UserBlogInfo getMainBlogInfoByBlogId(String blogId) {
        UserBlogInfo info = blogInfoMapper.getMainBlogInfoByBlogId(blogId);
        return info;
    }

    @Override
    public Map<String, Object> listChildBlogInfoByBlogId(String blogId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Long count = Optional.ofNullable(blogInfoMapper.countChildBlogInfoByBlogId(blogId)).orElse(0L);
        List<UserBlogInfo> infoList = Optional.ofNullable(blogInfoMapper.listChildBlogInfoByBlogId(blogId, currentPage, pageSize)).orElse(new ArrayList<>());
        map.put("count", count);
        map.put("data", infoList);
        return map;
    }

    @Override
    public Map<String, Object> listBlogInfoByUserId(String userId, Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Long count = Optional.ofNullable(blogInfoMapper.countBlogInfoByUserId(userId)).orElse(0L);
        List<BlogInfo> infoList = Optional.ofNullable(blogInfoMapper.listBlogInfoByUserId(userId, currentPage, pageSize)).orElse(new ArrayList<>());
        map.put("count", count);
        map.put("data", infoList);
        return map;
    }

    // 根据帖子ID查询帖子信息
    private BlogInfo getBlogInfoByBlogId(String blogId) {
        BlogInfo info = blogInfoMapper.getBlogInfoByBlogId(blogId);
        return info;
    }

    // 根据帖子名称查询帖子信息
    private BlogInfo getBlogInfoByBlogName(String blogId, String blogName) {
        BlogInfo info = blogInfoMapper.getBlogInfoByBlogName(blogId,blogName);
        return info;
    }
}
