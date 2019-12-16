package com.tjnu.club.mapper;

import com.tjnu.club.info.CategoryAllInfo;
import com.tjnu.club.info.CategoryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryInfoMapper {

    /**
     * 新增版块
     *
     * @param categoryInfo
     * @return
     */
    Integer saveCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 修改版块
     *
     * @param categoryInfo
     * @return
     */
    Integer updateCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 删除版块
     *
     * @param categoryId
     * @return
     */
    Integer deleteCategoryInfo(@Param("categoryId") String categoryId);

    /**
     * 根据版块ID获取版块
     *
     * @param categoryId
     * @return
     */
    CategoryInfo getCategoryInfoById(@Param("categoryId") String categoryId);

    /**
     * 获取所有父版块
     *
     * @return
     */
    List<CategoryInfo> listCategoryInfo();

    /**
     * 根据版块ID获取所有子版块列表
     *
     * @param categoryId
     * @return
     */
    List<CategoryInfo> listChildCategoryInfoById(@Param("categoryId") String categoryId);


    /**
     * 根据版块名获取版块信息，除版块ID之外的其他的
     *
     * @param categoryId
     * @param categoryName
     * @return
     */
    CategoryInfo getCategoryInfoByCategoryName(@Param("categoryId") String categoryId, @Param("categoryName") String categoryName);


    /**
     * 用户收藏版块
     *
     * @param userId
     * @param categoryId
     * @return
     */
    Integer categoryCollected(@Param("userId") String userId, @Param("categoryId") String categoryId);

    /**
     * 用户取消收藏版块
     *
     * @param userId
     * @param categoryId
     * @return
     */
    Integer categoryCollectedCancel(@Param("userId") String userId, @Param("categoryId") String categoryId);

    /**
     * 获取 被 指定用户收藏的 指定版块
     *
     * @param userId
     * @param categoryId
     * @return
     */
    CategoryInfo getCategoryInfoByCollected(@Param("userId") String userId, @Param("categoryId") String categoryId);

    /**
     * 获取用户收藏的 版块列表
     *
     * @param userId
     * @return
     */
    List<CategoryInfo> listCategoryInfoCollected(@Param("userId") String userId);

    List<CategoryInfo> listChildCategoryInfoAndNumByParentId(@Param("parentId") String parentId);
}
