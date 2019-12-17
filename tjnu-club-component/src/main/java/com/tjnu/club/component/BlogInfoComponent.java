package com.tjnu.club.component;

import com.tjnu.club.info.BlogInfo;
import com.tjnu.club.info.UserBlogInfo;

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
     *
     * @param categoryId 版块ID，不填写则展示全板块
     * @return
     */
    Map<String, Object> listMainBlogInfoByCategoryId(String categoryId, Integer currentPage, Integer pageSize);

    /**
     * 获取 当天 热门盖楼的主贴 列表
     *
     * @param topN topN条帖子
     * @return
     */
    List<UserBlogInfo> listBlogInfoTopN(Integer topN);

    /**
     * 根据帖子ID，获取 帖子详情 主贴
     *
     * @param blogId
     * @return
     */
    UserBlogInfo getMainBlogInfoByBlogId(String blogId);

    /**
     * 根据帖子ID，获取 主贴 盖楼 的 回帖
     *
     * @param blogId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Map<String, Object> listChildBlogInfoByBlogId(String blogId, Integer currentPage, Integer pageSize);

    /**
     * 用户发布的帖子
     *
     * @param userId
     * @return
     */
    Map<String, Object> listBlogInfoByUserId(String userId, Integer currentPage, Integer pageSize);


    /**
     * 用户收藏帖子
     *
     * @param userId
     * @param blogId
     * @return
     */
    Boolean blogCollected(String userId, String blogId);

    /**
     * 用户取消帖子的收藏
     *
     * @param userId
     * @param blogId
     * @return
     */
    Boolean blogCollectedCancel(String userId, String blogId);

    /**
     * 用户 收藏 帖子 的列表
     *
     * @param userId
     * @return
     */
    Map<String, Object> listBlogInfoCollected(String userId, Integer currentPage, Integer pageSize);


    /**
     * 帖子点赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    Integer blogZan(String userId, String blogId);
}
