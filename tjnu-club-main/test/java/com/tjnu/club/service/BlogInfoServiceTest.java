package com.tjnu.club.service;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.api.BlogInfoService;
import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserBlogInfoVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Blob;
import java.util.List;

public class BlogInfoServiceTest extends TJNUClubTest {

    @Resource
    private BlogInfoService blogInfoService;

    @Test
    public void saveBlogInfo() {
    }

    @Test
    public void updateBlogInfo() {
    }

    @Test
    public void deleteBlogInfo() {
    }

    @Test
    public void listMainBlogInfoByCategoryId() throws Exception {
        String categoryId = "157596070314861";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<BlogInfoVO>> result = blogInfoService.listMainBlogInfoByCategoryId(categoryId, currentPage, pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listBlogInfoTopN() {
        Integer topN = 10;
        ResultVO<List<BlogInfoVO>> result = blogInfoService.listBlogInfoTopN(topN);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getMainBlogInfoByBlogId() {
        String blogId = "1000";
        ResultVO<UserBlogInfoVO> result = blogInfoService.getMainBlogInfoByBlogId(blogId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listChildBlogInfoByBlogId() {
        String blogId = "1000";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<UserBlogInfoVO>> result = blogInfoService.listChildBlogInfoByBlogId(blogId,currentPage,pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listBlogInfoByUserId() {
        String userId = "1000";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<BlogInfoVO>> result = blogInfoService.listBlogInfoByUserId(userId,currentPage,pageSize);
        System.out.println(JSON.toJSON(result));
    }
}