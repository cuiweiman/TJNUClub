package com.tjnu.club.mapper;

import com.tjnu.club.info.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogInfoMapper {


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
    long countBlogInfoByCategoryId(String categoryId);

    List<BlogInfo> listBlogInfoByCategoryId(String categoryId);


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
    BlogInfo getBlogInfoByBlogId(String blogId);

    Long countChildBlogInfoByBlogId(String blogId);

    List<BlogInfo> listChildBlogInfoByBlogId(String blogId);


    /**
     * 用户发布的帖子
     *
     * @param userId
     * @return
     */
    Long countBlogInfoByUserId(String userId);

    List<BlogInfo> listBlogInfoByUserId(String userId);


}
