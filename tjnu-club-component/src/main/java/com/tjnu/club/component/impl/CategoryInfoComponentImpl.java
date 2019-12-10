package com.tjnu.club.component.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tjnu.club.component.CategoryInfoComponent;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.CategoryInfo;
import com.tjnu.club.mapper.CategoryInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryInfoComponentImpl implements CategoryInfoComponent {

    @Resource
    private CategoryInfoMapper categoryInfoMapper;

    @Override
    public Boolean saveCategoryInfo(CategoryInfo categoryInfo) {
        //校验版块名称是否重复
        CategoryInfo query = getCategoryInfoByCategoryName(null, categoryInfo.getCategoryName());
        if (!ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_NAME_REPEATED);
        }
        Integer result = categoryInfoMapper.saveCategoryInfo(categoryInfo);
        if (result != 1) { // 保存失败
            throw new TJNUException(TJNUResultEnum.CATEGORY_SAVE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateCategoryInfo(CategoryInfo categoryInfo) {
        // 校验 被修改的版块 是否存在
        CategoryInfo query = getCategoryInfoById(categoryInfo.getCategoryId());
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_NOT_EXISTS);
        }
        //校验 修改的名称是否重复
        CategoryInfo query2 = getCategoryInfoByCategoryName(categoryInfo.getCategoryId(), categoryInfo.getCategoryName());
        if (!ObjectUtil.isEmpty(query2)) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_NAME_REPEATED);
        }
        Integer result = categoryInfoMapper.updateCategoryInfo(categoryInfo);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_UPDATE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteCategoryInfo(String categoryId) { //如果有 子版块/文章，则禁止删除
        CategoryInfo query = getCategoryInfoById(categoryId);
        if (ObjectUtil.isEmpty(query)) { //根据ID指定的版块不存在
            throw new TJNUException(TJNUResultEnum.CATEGORY_NOT_EXISTS);
        }
        // 禁止删除有子版块的版块
        List<CategoryInfo> childCategoryList = listChildCategoryInfoById(categoryId);
        if (childCategoryList.size() > 0) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_DELETE_FORBIDDEN);
        }
        // TODO 禁止删除有文章的版块
        Integer result = categoryInfoMapper.deleteCategoryInfo(categoryId);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_DELETE_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public CategoryInfo getCategoryInfoById(String categoryId) {
        CategoryInfo info = categoryInfoMapper.getCategoryInfoById(categoryId);
        return info;
    }

    @Override
    public List<CategoryInfo> listCategoryInfo() {
        List<CategoryInfo> infoList = Optional.ofNullable(categoryInfoMapper.listCategoryInfo()).orElse(new ArrayList<>());
        return infoList;
    }

    @Override
    public List<CategoryInfo> listChildCategoryInfoById(String categoryId) {
        List<CategoryInfo> infoList = Optional.ofNullable(categoryInfoMapper.listChildCategoryInfoById(categoryId)).orElse(new ArrayList<>());
        return infoList;
    }

    private CategoryInfo getCategoryInfoByCategoryName(String categoryId, String categoryName) {
        CategoryInfo info = categoryInfoMapper.getCategoryInfoByCategoryName(categoryId, categoryName);
        return info;
    }
}
