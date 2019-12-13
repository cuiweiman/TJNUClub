package com.tjnu.club.service.impl;

import com.tjnu.club.api.BlogInfoService;
import com.tjnu.club.component.BlogInfoComponent;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.enums.TJNUStatusEnums;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.BlogInfo;
import com.tjnu.club.info.UserBlogInfo;
import com.tjnu.club.service.util.ServiceTransferUtil;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.vo.BlogInfoVO;
import com.tjnu.club.vo.PageInfoVO;
import com.tjnu.club.vo.ResultVO;
import com.tjnu.club.vo.UserBlogInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogInfoServiceImpl extends TJNUService implements BlogInfoService {

    @Resource
    private BlogInfoComponent blogInfoComponent;

    @Override
    public ResultVO<Boolean> saveBlogInfo(BlogInfoVO blogInfoVO) {
        checkParam(blogInfoVO);
        try {
            //生成 blogId
            blogInfoVO.setBlogId(KeyFactory.genDefaultSerialNo());
            BlogInfo info = ServiceTransferUtil.blogVo2Info(blogInfoVO);
            Boolean result = blogInfoComponent.saveBlogInfo(info);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> updateBlogInfo(BlogInfoVO blogInfoVO) {
        checkParam(blogInfoVO);
        super.notBlank("帖子ID", blogInfoVO.getBlogId());
        try {
            BlogInfo info = ServiceTransferUtil.blogVo2Info(blogInfoVO);
            Boolean result = blogInfoComponent.updateBlogInfo(info);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> deleteBlogInfo(String blogId) {
        super.notBlank("帖子ID", blogId);
        try {
            Boolean result = blogInfoComponent.deleteBlogInfo(blogId);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<PageInfoVO<UserBlogInfoVO>> listMainBlogInfoByCategoryId(String categoryId, Integer currentPage, Integer pageSize) {
        super.notBlank("版块ID", categoryId).checkPage(currentPage, pageSize);
        Integer current = (currentPage - 1) * pageSize;
        try {
            Map<String, Object> map = blogInfoComponent.listMainBlogInfoByCategoryId(categoryId, current, pageSize);
            Long count = (long) map.get("count");
            List<UserBlogInfo> infoList = (List<UserBlogInfo>) map.get("data");
            List<UserBlogInfoVO> voList = infoList.stream().map(info -> ServiceTransferUtil.userBlogInfo2Vo(info)).collect(Collectors.toList());
            PageInfoVO<UserBlogInfoVO> result = new PageInfoVO<>(count, voList);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<List<UserBlogInfoVO>> listBlogInfoTopN(Integer topN) {
        super.notNull("热门帖子条数", topN);
        try {
            List<UserBlogInfo> infoList = blogInfoComponent.listBlogInfoTopN(topN);
            List<UserBlogInfoVO> voList = infoList.stream().map(info -> ServiceTransferUtil.userBlogInfo2Vo(info)).collect(Collectors.toList());
            return new ResultVO<>(voList);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<UserBlogInfoVO> getMainBlogInfoByBlogId(String blogId) {
        super.notBlank("帖子ID", blogId);
        try {
            UserBlogInfo info = blogInfoComponent.getMainBlogInfoByBlogId(blogId);
            UserBlogInfoVO vo = ServiceTransferUtil.userBlogInfo2Vo(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<PageInfoVO<UserBlogInfoVO>> listChildBlogInfoByBlogId(String blogId, Integer currentPage, Integer pageSize) {
        super.notBlank("帖子ID", blogId).checkPage(currentPage, pageSize);
        Integer current = (currentPage - 1) * pageSize;
        try {
            Map<String, Object> map = blogInfoComponent.listChildBlogInfoByBlogId(blogId, current, pageSize);
            Long count = (Long) map.get("count");
            List<UserBlogInfo> infoList = (List<UserBlogInfo>) map.get("data");
            List<UserBlogInfoVO> voList = infoList.stream().map(info -> ServiceTransferUtil.userBlogInfo2Vo(info)).collect(Collectors.toList());
            PageInfoVO<UserBlogInfoVO> result = new PageInfoVO<>(count, voList);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<PageInfoVO<UserBlogInfoVO>> listBlogInfoByUserId(String userId, Integer currentPage, Integer pageSize) {
        super.notBlank("用户ID", userId).checkPage(currentPage, pageSize);
        Integer current = (currentPage - 1) * pageSize;
        try {
            Map<String, Object> map = blogInfoComponent.listBlogInfoByUserId(userId, current, pageSize);
            Long count = (Long) map.get("count");
            List<UserBlogInfo> infoList = (List<UserBlogInfo>) map.get("data");
            List<UserBlogInfoVO> voList = infoList.stream().map(info -> ServiceTransferUtil.userBlogInfo2Vo(info)).collect(Collectors.toList());
            PageInfoVO<UserBlogInfoVO> result = new PageInfoVO<>(count, voList);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> blogCollected(String userId, String blogId) {
        super.notBlank("用户ID", userId).notBlank("帖子ID", blogId);
        try {
            Boolean result = blogInfoComponent.blogCollected(userId, blogId);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<Boolean> blogCollectedCancel(String userId, String blogId) {
        super.notBlank("用户ID", userId).notBlank("帖子ID", blogId);
        try {
            Boolean result = blogInfoComponent.blogCollectedCancel(userId, blogId);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<PageInfoVO<UserBlogInfoVO>> listBlogInfoCollected(String userId, Integer currentPage, Integer pageSize) {
        super.notBlank("用户ID", userId).checkPage(currentPage, pageSize);
        Integer current = (currentPage - 1) * pageSize;
        try {
            Map<String, Object> map = blogInfoComponent.listBlogInfoCollected(userId, current, pageSize);
            Long count = (long) map.get("count");
            List<UserBlogInfo> infoList = (List<UserBlogInfo>) map.get("data");
            List<UserBlogInfoVO> voList = infoList.stream().map(info -> ServiceTransferUtil.userBlogInfo2Vo(info)).collect(Collectors.toList());
            PageInfoVO<UserBlogInfoVO> result = new PageInfoVO<>(count, voList);
            return new ResultVO<>(result);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    private void checkParam(BlogInfoVO vo) {
        super.notNull("帖子信息", vo).notNull("帖子内容", vo.getBlogContent())
                .notBlank("帖子所属版块ID", vo.getCategoryId()).notBlank("用户ID", vo.getUserId())
                .notNull("主贴标识", vo.getTopBlog());
        if (vo.getTopBlog() == TJNUStatusEnums.BLOG_TOP.getCode()) { //主贴校验帖名
            super.notBlank("帖名", vo.getBlogName());
            vo.setParentBlogId(null);
        } else if (vo.getTopBlog() == TJNUStatusEnums.BLOG_NOT_TOP.getCode()) { //回帖校验主贴ID
            super.notBlank("主贴ID", vo.getParentBlogId());
            vo.setBlogName(null);
        } else {
            throw new TJNUException(TJNUResultEnum.SYSTEM_PARAM_ERROR);
        }
    }

}
