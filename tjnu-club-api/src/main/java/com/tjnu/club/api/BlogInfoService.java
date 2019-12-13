package com.tjnu.club.api;

import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserBlogInfoVO;

import java.util.List;

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
     *
     * @param categoryId 版块ID，不填写则展示全板块
     * @return
     */
    ResultVO<PageInfoVO<UserBlogInfoVO>> listMainBlogInfoByCategoryId(String categoryId, Integer currentPage, Integer pageSize);

    /**
     * 获取 当天 热门盖楼的主贴 列表
     *
     * @param topN topN条帖子
     * @return
     */
    ResultVO<List<UserBlogInfoVO>> listBlogInfoTopN(Integer topN);

    /**
     * 根据帖子ID，获取 帖子详情 主贴
     *
     * @param blogId
     * @return
     */
    ResultVO<UserBlogInfoVO> getMainBlogInfoByBlogId(String blogId);

    /**
     * 根据帖子ID，获取 帖子详情 主贴 的 回帖
     *
     * @param blogId
     * @param currentPage
     * @param pageSize
     * @return
     */
    ResultVO<PageInfoVO<UserBlogInfoVO>> listChildBlogInfoByBlogId(String blogId, Integer currentPage, Integer pageSize);

    /**
     * 用户发布的帖子
     *
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    ResultVO<PageInfoVO<UserBlogInfoVO>> listBlogInfoByUserId(String userId, Integer currentPage, Integer pageSize);

    /**
     * 用户收藏帖子
     *
     * @param userId
     * @param blogId
     * @return
     */
    ResultVO<Boolean> blogCollected(String userId, String blogId);

    /**
     * 用户取消帖子的收藏
     *
     * @param userId
     * @param blogId
     * @return
     */
    ResultVO<Boolean> blogCollectedCancel(String userId, String blogId);

    /**
     * 用户 收藏 帖子 的列表
     *
     * @param userId
     * @return
     */
    ResultVO<PageInfoVO<UserBlogInfoVO>> listBlogInfoCollected(String userId,Integer currentPage,Integer pageSize);

}
