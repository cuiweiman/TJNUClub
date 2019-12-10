package com.tjnu.club.service.impl;

import com.tjnu.club.api.CategoryInfoService;
import com.tjnu.club.component.CategoryInfoComponent;
import com.tjnu.club.constants.TJNUService;
import com.tjnu.club.enums.TJNUStatusEnums;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.CategoryInfo;
import com.tjnu.club.service.util.ServiceTransferUtil;
import com.tjnu.club.utils.KeyFactory;
import com.tjnu.club.vo.CategoryInfoVO;
import com.tjnu.club.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryInfoServiceImpl extends TJNUService implements CategoryInfoService {

    @Resource
    private CategoryInfoComponent categoryInfoComponent;

    @Override
    public ResultVO<Boolean> saveCategoryInfo(CategoryInfoVO categoryInfoVO) {
        checkParam(categoryInfoVO);
        try {
            //生成categoryId
            categoryInfoVO.setCategoryId(KeyFactory.genDefaultSerialNo());
            CategoryInfo info = ServiceTransferUtil.categoryVo2Info(categoryInfoVO);
            Boolean result = categoryInfoComponent.saveCategoryInfo(info);
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
    public ResultVO<Boolean> updateCategoryInfo(CategoryInfoVO categoryInfoVO) {
        checkParam(categoryInfoVO);
        super.notBlank("版块ID", categoryInfoVO.getCategoryId());
        try {
            CategoryInfo info = ServiceTransferUtil.categoryVo2Info(categoryInfoVO);
            Boolean result = categoryInfoComponent.updateCategoryInfo(info);
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
    public ResultVO<Boolean> deleteCategoryInfo(String categoryId) {
        super.notBlank("版块ID", categoryId);
        try {
            Boolean result = categoryInfoComponent.deleteCategoryInfo(categoryId);
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
    public ResultVO<CategoryInfoVO> getCategoryInfoById(String categoryId) {
        super.notBlank("版块ID", categoryId);
        try {
            CategoryInfo info = categoryInfoComponent.getCategoryInfoById(categoryId);
            CategoryInfoVO vo = ServiceTransferUtil.categoryInfo2Vo(info);
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<List<CategoryInfoVO>> listCategoryInfo() {
        try {
            List<CategoryInfo> infoList = categoryInfoComponent.listCategoryInfo();
            List<CategoryInfoVO> vo = infoList.stream().map(info -> ServiceTransferUtil.categoryInfo2Vo(info)).collect(Collectors.toList());
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    @Override
    public ResultVO<List<CategoryInfoVO>> listChildCategoryInfoById(String categoryId) {
        super.notBlank("版块ID", categoryId);
        try {
            List<CategoryInfo> infoList = categoryInfoComponent.listChildCategoryInfoById(categoryId);
            List<CategoryInfoVO> vo = infoList.stream().map(info -> ServiceTransferUtil.categoryInfo2Vo(info)).collect(Collectors.toList());
            return new ResultVO<>(vo);
        } catch (TJNUException e) {
            log.error(e.getMsg(), e);
            return new ResultVO<>(e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResultVO<>(new TJNUException());
        }
    }

    private void checkParam(CategoryInfoVO vo) {
        super.notNull("版块信息", vo).notBlank("版块名称", vo.getCategoryName())
                .notNull("顶级版块标识", vo.getTopCategory());
        if (vo.getTopCategory() == TJNUStatusEnums.CATEGORY_NOT_TOP.getCode()) { //非顶级版块，校验父版块ID
            super.notBlank("父版块", vo.getParentCategoryId());
        } else { // 父版块
            vo.setParentCategoryId(null);
        }
    }
}
