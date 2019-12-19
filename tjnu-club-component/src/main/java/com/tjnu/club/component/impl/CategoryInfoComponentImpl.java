package com.tjnu.club.component.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tjnu.club.component.CategoryInfoComponent;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.CategoryAllInfo;
import com.tjnu.club.info.CategoryInfo;
import com.tjnu.club.mapper.BlogInfoMapper;
import com.tjnu.club.mapper.CategoryInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class CategoryInfoComponentImpl implements CategoryInfoComponent {

    @Resource
    private CategoryInfoMapper categoryInfoMapper;

    @Resource
    private BlogInfoMapper blogInfoMapper;

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
        // 禁止删除 有帖子的版块
        Long count = blogInfoMapper.countMainBlogInfoByCategoryId(categoryId);
        if (count > 0L) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_DELETE_CANNOT);
        }
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

    @Override
    public Boolean categoryCollected(String userId, String categoryId) {
        // 校验该用户是否已收藏该版块
        CategoryInfo query = getCategoryInfoByCollected(userId, categoryId);
        if (!ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_HAS_COLLECTED);
        }
        Integer result = categoryInfoMapper.categoryCollected(userId, categoryId);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_COLLECTED_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean categoryCollectedCancel(String userId, String categoryId) {
        CategoryInfo query = getCategoryInfoByCollected(userId, categoryId);
        if (ObjectUtil.isEmpty(query)) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_HAS_NOT_COLLECTED);
        }
        Integer result = categoryInfoMapper.categoryCollectedCancel(userId, categoryId);
        if (result != 1) {
            throw new TJNUException(TJNUResultEnum.CATEGORY_CANCEL_COLLECTED_FAILURE);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<CategoryInfo> listCategoryInfoCollected(String userId) {
        List<CategoryInfo> infoList = Optional.ofNullable(categoryInfoMapper.listCategoryInfoCollected(userId)).orElse(new ArrayList<>());
        return infoList;
    }

    @Override
    public List<CategoryAllInfo> listAllCategoryInfo() {
        List<CategoryAllInfo> result = new ArrayList<>();
        // 获取父版块列表
        List<CategoryInfo> parentList = listCategoryInfo();
        //循环获取子版块列表和版块中帖子的总数
        parentList.forEach(parent->{
            CategoryAllInfo info = new CategoryAllInfo();
            BeanUtils.copyProperties(parent,info);
            List<CategoryInfo> childList = Optional.ofNullable(categoryInfoMapper.listChildCategoryInfoAndNumByParentId(parent.getCategoryId())).orElse(new ArrayList<>());
            info.setChild(childList);
            result.add(info);
        });
        return result;
    }

    @Override
    public Map<String, Object> getCategoryBasicInfoByCategoryId(String categoryId) {
        Map<String,Object> map = new HashMap<>(4);
        CategoryInfo info = categoryInfoMapper.getCategoryInfoById(categoryId);
        if(ObjectUtil.isEmpty(info)){
            throw new TJNUException(TJNUResultEnum.CATEGORY_NOT_EXISTS);
        }

        Map<String,Object> parentMap = new HashMap<>(4);
        if(StrUtil.isNotBlank(info.getParentCategoryId())){
            CategoryInfo parent = categoryInfoMapper.getCategoryInfoById(info.getParentCategoryId());
            if(ObjectUtil.isEmpty(parent)){
                throw new TJNUException(TJNUResultEnum.CATEGORY_NOT_EXISTS);
            }
            parentMap.put("categoryId",parent.getCategoryId());
            parentMap.put("categoryName",parent.getCategoryName());
        }

        map.put("categoryId",info.getCategoryId());
        map.put("categoryName",info.getCategoryName());
        map.put("parent",parentMap);
        return map;
    }

    // 根据 版块名称 获取 除 指定版块ID 之外的其他版块，用于 版块名称查重
    private CategoryInfo getCategoryInfoByCategoryName(String categoryId, String categoryName) {
        CategoryInfo info = categoryInfoMapper.getCategoryInfoByCategoryName(categoryId, categoryName);
        return info;
    }

    // 获取 被 指定用户 收藏的 指定版块
    private CategoryInfo getCategoryInfoByCollected(String userId, String categoryId) {
        CategoryInfo info = categoryInfoMapper.getCategoryInfoByCollected(userId, categoryId);
        return info;
    }
}
