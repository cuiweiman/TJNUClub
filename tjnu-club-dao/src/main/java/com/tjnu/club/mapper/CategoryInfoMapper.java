package com.tjnu.club.mapper;

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

}
