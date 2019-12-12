package com.tjnu.club.mapper;

import com.tjnu.club.info.BlogInfo;
import com.tjnu.club.info.UserBlogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BlogInfoMapper {


    /**
     * 新增帖子 / 发布帖子
     *
     * @param blogInfo
     * @return
     */
    Integer saveBlogInfo(BlogInfo blogInfo);


    /**
     * 修改帖子
     *
     * @param blogInfo
     * @return
     */
    Integer updateBlogInfo(BlogInfo blogInfo);


    /**
     * 删除帖子
     *
     * @param blogId
     * @return
     */
    Integer deleteBlogInfo(@Param("blogId") String blogId);


    /**
     * 查询 指定版块ID 下的 主贴列表
     *
     * @param categoryId 版块ID，不填写则展示全板块
     * @return
     */
    long countMainBlogInfoByCategoryId(@Param("categoryId") String categoryId);

    List<BlogInfo> listMainBlogInfoByCategoryId(@Param("categoryId") String categoryId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);


    /**
     * 获取 当天 热门盖楼的主贴 列表
     *
     * @param topN  topN条帖子
     * @param begin 开始时间
     * @param end   结束时间
     * @return
     */
    List<BlogInfo> listBlogInfoTopN(@Param("topN") Integer topN, @Param("begin") Date begin, @Param("end") Date end);


    /**
     * 根据帖子ID，获取 帖子详情 和 盖楼的回帖
     *
     * @param blogId
     * @return
     */
    UserBlogInfo getMainBlogInfoByBlogId(@Param("blogId") String blogId);

    Long countChildBlogInfoByBlogId(@Param("blogId") String blogId);

    List<UserBlogInfo> listChildBlogInfoByBlogId(@Param("blogId") String blogId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);


    /**
     * 用户发布的帖子
     *
     * @param userId
     * @return
     */
    Long countBlogInfoByUserId(@Param("userId") String userId);

    List<BlogInfo> listBlogInfoByUserId(@Param("userId") String userId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);


    /**
     * 用户收藏帖子
     *
     * @param userId
     * @param blogId
     * @return
     */
    Integer blogCollected(@Param("userId") String userId, @Param("blogId") String blogId);


    /**
     * 用户取消收藏帖子
     *
     * @param userId
     * @param blogId
     * @return
     */
    Integer blogCollectedCancel(@Param("userId") String userId, @Param("blogId") String blogId);


    /**
     * 获取 用户收藏的 帖子 列表
     *
     * @param userId
     * @return
     */
    Long countBlogInfoCollected(@Param("userId") String userId);

    List<BlogInfo> listBlogInfoCollected(@Param("userId") String userId, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);


    // 根据 帖子ID获取帖子信息，不分主贴和回帖
    BlogInfo getBlogInfoByBlogId(@Param("blogId") String blogId);

    // 根据 帖子名称，获取除指定ID之外的帖子信息
    BlogInfo getBlogInfoByBlogName(@Param("blogId") String blogId, @Param("blogName") String blogName);

    // 根据 用户ID 和 帖子ID 获取用户已收藏的 帖子信息
    BlogInfo getBlogInfoCollected(@Param("userId") String userId, @Param("blogId") String blogId);
}
