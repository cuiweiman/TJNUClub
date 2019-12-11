package com.tjnu.club.component;

import com.tjnu.club.info.BlogInfo;

import java.util.List;
import java.util.Map;

public interface BlogInfoComponent {


    /**
     * 新增帖子 / 发布帖子
     *
     * @param blogInfo
     * @return
     */
    Boolean saveBlogInfo(BlogInfo blogInfo);

    /**
     * 修改帖子
     *
     * @param blogInfo
     * @return
     */
    Boolean updateBlogInfo(BlogInfo blogInfo);

    /**
     * 删除帖子
     *
     * @param blogId
     * @return
     */
    Boolean deleteBlogInfo(String blogId);


    /**
     * 查询 指定版块ID 下的 主贴列表
     * @param categoryId 版块ID，不填写则展示全板块
     * @return
     */
    Map<String, Object> listBlogInfoByCategoryId(String categoryId);

    /**
     * 获取 当天 热门盖楼的主贴 列表
     * @param topN topN条帖子
     * @return
     */
    List<BlogInfo> listBlogInfoTopN(Integer topN);

    /**
     * 根据帖子ID，获取 帖子详情 和 盖楼的回帖
     *
     * @param blogId
     * @return
     */
    Map<String, Object> listBlogInfoByBlogId(String blogId);

    /**
     * 用户发布的帖子
     *
     * @param userId
     * @return
     */
    Map<String, Object> listBlogInfoByUserId(String userId);


}
