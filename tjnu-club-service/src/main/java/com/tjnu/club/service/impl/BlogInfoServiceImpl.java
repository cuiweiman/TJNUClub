package com.tjnu.club.service.impl;

import com.tjnu.club.api.BlogInfoService;
import com.tjnu.club.component.BlogInfoComponent;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BlogInfoServiceImpl extends TJNUService implements BlogInfoService {

    @Resource
    private BlogInfoComponent blogInfoComponent;

    @Override
    public ResultVO<Boolean> saveBlogInfo(BlogInfoVO blogInfoVO) {
        return null;
    }

    @Override
    public ResultVO<Boolean> updateBlogInfo(BlogInfoVO blogInfoVO) {
        return null;
    }

    @Override
    public ResultVO<Boolean> deleteBlogInfo(String blogId) {
        return null;
    }

    @Override
    public ResultVO<Map<String, Object>> listBlogInfoByCategoryId(String categoryId) {
        return null;
    }

    @Override
    public ResultVO<List<BlogInfoVO>> listBlogInfoTopN(Integer topN) {
        return null;
    }

    @Override
    public ResultVO<Map<String, Object>> listBlogInfoByBlogId(String blogId) {
        return null;
    }

    @Override
    public ResultVO<Map<String, Object>> listBlogInfoByUserId(String userId) {
        return null;
    }
}
