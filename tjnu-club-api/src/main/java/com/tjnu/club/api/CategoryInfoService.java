package com.tjnu.club.api;

import com.tjnu.club.vo.CategoryAllInfoVO;
import com.tjnu.club.vo.CategoryInfoVO;
import com.tjnu.club.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/10 10:17
 * @Description: 帖子版块服务
 */
public interface CategoryInfoService {

    /**
     * 新增版块
     *
     * @param categoryInfoVO
     * @return
     */
    ResultVO<Boolean> saveCategoryInfo(CategoryInfoVO categoryInfoVO);

    /**
     * 修改版块，只能修改版块名称
     *
     * @param categoryInfoVO
     * @return
     */
    ResultVO<Boolean> updateCategoryInfo(CategoryInfoVO categoryInfoVO);

    /**
     * 删除版块
     *
     * @param categoryId
     * @return
     */
    ResultVO<Boolean> deleteCategoryInfo(String categoryId);

    /**
     * 根据版块ID获取版块
     *
     * @param categoryId
     * @return
     */
    ResultVO<CategoryInfoVO> getCategoryInfoById(String categoryId);

    /**
     * 获取所有父版块
     *
     * @return
     */
    ResultVO<List<CategoryInfoVO>> listCategoryInfo();

    /**
     * 根据版块ID获取所有子版块列表
     *
     * @param categoryId
     * @return
     */
    ResultVO<List<CategoryInfoVO>> listChildCategoryInfoById(String categoryId);

    /**
     * 用户收藏版块
     *
     * @param userId
     * @param categoryId
     * @return
     */
    ResultVO<Boolean> categoryCollected(String userId, String categoryId);

    /**
     * 用户取消收藏版块
     *
     * @param userId
     * @param categoryId
     * @return
     */
    ResultVO<Boolean> categoryCollectedCancel(String userId, String categoryId);

    /**
     * 获取用户收藏的版块
     *
     * @param userId
     * @return
     */
    ResultVO<List<CategoryInfoVO>> listCategoryInfoCollected(String userId);

    /**
     * 获取父版块及其对应的子版块，并查询子版块下所属帖子的数量。
     *
     * @return
     */
    ResultVO<List<CategoryAllInfoVO>> listAllCategoryInfo();

}
