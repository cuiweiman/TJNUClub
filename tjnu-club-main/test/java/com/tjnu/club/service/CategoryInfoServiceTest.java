package com.tjnu.club.service;

import com.alibaba.fastjson.JSON;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.api.CategoryInfoService;
import com.tjnu.club.vo.CategoryAllInfoVO;
import com.tjnu.club.vo.CategoryInfoVO;
import com.tjnu.club.vo.ResultVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: WeiMan Cui
 * @Date: 2019/12/10 14:44
 * @Description: 版块 测试
 */
public class CategoryInfoServiceTest extends TJNUClubTest {

    @Resource
    private CategoryInfoService categoryInfoService;

    @Test
    public void saveCategoryInfo() {
        CategoryInfoVO vo = new CategoryInfoVO();
        vo.setCategoryName("青年教师联谊");
        vo.setTopCategory(1);
        vo.setParentCategoryId("157596138576589");
        ResultVO<Boolean> result = categoryInfoService.saveCategoryInfo(vo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void updateCategoryInfo() {
        CategoryInfoVO vo = new CategoryInfoVO();
        vo.setCategoryId("157596341020912");
        vo.setCategoryName("师大理工联谊");
        vo.setTopCategory(1);
        vo.setParentCategoryId("157596138576589");
        ResultVO<Boolean> result = categoryInfoService.updateCategoryInfo(vo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void deleteCategoryInfo() {
        String categoryId = "157596836253956";
        ResultVO<Boolean> result = categoryInfoService.deleteCategoryInfo(categoryId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getCategoryInfoById() {
        String categoryId = "157596839810138";
        ResultVO<CategoryInfoVO> result = categoryInfoService.getCategoryInfoById(categoryId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listCategoryInfo() {
        ResultVO<List<CategoryInfoVO>> result = categoryInfoService.listCategoryInfo();
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listChildCategoryInfoById() {
        String categoryId = "157596138576589";
        ResultVO<List<CategoryInfoVO>> result = categoryInfoService.listChildCategoryInfoById(categoryId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void categoryCollected() {
        String userId = "1000";
        String categoryId = "157596138576589";
        ResultVO<Boolean> result = categoryInfoService.categoryCollected(userId, categoryId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void categoryCollectedCancel() {
        String userId = "1000";
        String categoryId = "157596138576589";
        ResultVO<Boolean> result = categoryInfoService.categoryCollectedCancel(userId, categoryId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listCategoryInfoCollected() {
        String userId = "1000";
        ResultVO<List<CategoryInfoVO>> result = categoryInfoService.listCategoryInfoCollected(userId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listAllCategoryInfo() {
        ResultVO<List<CategoryAllInfoVO>> result = categoryInfoService.listAllCategoryInfo();
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getCategoryBasicInfoByCategoryId() {
        String categoryId = "157596341020912";
        ResultVO<Map<String, Object>> result = categoryInfoService.getCategoryBasicInfoByCategoryId(categoryId);
        System.out.println(JSON.toJSON(result));
    }

}
