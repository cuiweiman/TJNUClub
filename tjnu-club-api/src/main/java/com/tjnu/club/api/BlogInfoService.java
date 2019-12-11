package com.tjnu.club.api;

import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/11 14:45
 * @Description: 帖子服务
 */
public interface BlogInfoService {

    /**
     * 新增帖子 / 发布帖子
     *
     * @param blogInfoVO
     * @return
     */
    ResultVO<Boolean> saveBlogInfo(BlogInfoVO blogInfoVO);

    /**
     * 修改帖子
     *
     * @param blogInfoVO
     * @return
     */
    ResultVO<Boolean> updateBlogInfo(BlogInfoVO blogInfoVO);

    /**
     * 删除帖子
     *
     * @param blogId
     * @return
     */
    ResultVO<Boolean> deleteBlogInfo(String blogId);


    /**
     * 查询 指定版块ID 下的 主贴列表
     * @param categoryId 版块ID，不填写则展示全板块
     * @return
     */
    ResultVO<Map<String, Object>> listBlogInfoByCategoryId(String categoryId);

    /**
     * 获取 当天 热门盖楼的主贴 列表
     * @param topN topN条帖子
     * @return
     */
    ResultVO<List<BlogInfoVO>> listBlogInfoTopN(Integer topN);

    /**
     * 根据帖子ID，获取 帖子详情 和 盖楼的回帖
     *
     * @param blogId
     * @return
     */
    ResultVO<Map<String, Object>> listBlogInfoByBlogId(String blogId);

    /**
     * 用户发布的帖子
     *
     * @param userId
     * @return
     */
    ResultVO<Map<String, Object>> listBlogInfoByUserId(String userId);

}
