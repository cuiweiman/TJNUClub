package com.tjnu.club.service;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tjnu.club.TJNUClubTest;
import com.tjnu.club.api.BlogInfoService;
import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserBlogInfoVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class BlogInfoServiceTest extends TJNUClubTest {

    @Resource
    private BlogInfoService blogInfoService;

    @Test
    public void saveBlogInfo() {
        BlogInfoVO vo = new BlogInfoVO();
        vo.setBlogName("艾依灵科技中南校招");
        vo.setBlogContent("这尼玛是真的吗？我得赶快准备简历了哇！QAQ");
        vo.setCategoryId("157596836253956");
        vo.setUserId("1001");
        vo.setTopBlog(1);
        vo.setParentBlogId("157614967003842");
        ResultVO<Boolean> result = blogInfoService.saveBlogInfo(vo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void updateBlogInfo() {
        BlogInfoVO vo = new BlogInfoVO();
        vo.setBlogName("艾依灵科技中南图书馆进行校招");
        vo.setBlogContent("驰名中外的艾依灵科技将在明天10点，中南图书馆门前进行校招");
        vo.setCategoryId("157596836253956");
        vo.setUserId("1002");
        vo.setTopBlog(0);
        vo.setBlogId("157614967003842");
        ResultVO<Boolean> result = blogInfoService.updateBlogInfo(vo);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void deleteBlogInfo() {
        String blogId = "157614967003842";
        ResultVO<Boolean> result = blogInfoService.deleteBlogInfo(blogId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listMainBlogInfoByCategoryId() throws Exception {
        String categoryId = "157596070314861";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<UserBlogInfoVO>> result = blogInfoService.listMainBlogInfoByCategoryId(categoryId, currentPage, pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listBlogInfoTopN() {
        Integer topN = 10;
        ResultVO<List<UserBlogInfoVO>> result = blogInfoService.listBlogInfoTopN(topN);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void getMainBlogInfoByBlogId() {
        String blogId = "100";
        ResultVO<UserBlogInfoVO> result = blogInfoService.getMainBlogInfoByBlogId(blogId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listChildBlogInfoByBlogId() {
        String blogId = "1000";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<UserBlogInfoVO>> result = blogInfoService.listChildBlogInfoByBlogId(blogId, currentPage, pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listBlogInfoByUserId() {
        String userId = "1000";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<UserBlogInfoVO>> result = blogInfoService.listBlogInfoByUserId(userId, currentPage, pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void blogCollected() {
        String userId = "1000";
        String blogId = "1006";
        ResultVO<Boolean> result = blogInfoService.blogCollected(userId, blogId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void blogCollectedCancel() {
        String userId = "1000";
        String blogId = "1005";
        ResultVO<Boolean> result  = blogInfoService.blogCollectedCancel(userId, blogId);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void listBlogInfoCollected() {
        String userId = "1000";
        Integer currentPage = 1;
        Integer pageSize = 100;
        ResultVO<PageInfoVO<UserBlogInfoVO>> result = blogInfoService.listBlogInfoCollected(userId, currentPage, pageSize);
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void blogZan() {
        String userId = "1000";
        String blogId = "1005";
        ResultVO<Integer> result = blogInfoService.blogZan(userId, blogId);
        System.out.println(JSON.toJSON(result));
    }

}