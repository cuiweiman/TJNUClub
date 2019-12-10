package com.tjnu.club.component;

import com.tjnu.club.info.CategoryInfo;

import java.util.List;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/10 11:47
 * @Description: 版块服务-业务层
 */
public interface CategoryInfoComponent {


    /**
     * 新增版块
     *
     * @param categoryInfo
     * @return
     */
    Boolean saveCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 修改版块
     *
     * @param categoryInfo
     * @return
     */
    Boolean updateCategoryInfo(CategoryInfo categoryInfo);

    /**
     * 删除版块
     *
     * @param categoryId
     * @return
     */
    Boolean deleteCategoryInfo(String categoryId);

    /**
     * 根据版块ID获取版块
     *
     * @param categoryId
     * @return
     */
    CategoryInfo getCategoryInfoById(String categoryId);

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
    List<CategoryInfo> listChildCategoryInfoById(String categoryId);


}
