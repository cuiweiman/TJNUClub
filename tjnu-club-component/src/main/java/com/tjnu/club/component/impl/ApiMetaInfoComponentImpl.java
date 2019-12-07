package com.tjnu.club.component.impl;

import com.tjnu.club.component.ApiMetaInfoComponent;
import com.tjnu.club.enums.TJNUResultEnum;
import com.tjnu.club.exceptions.TJNUException;
import com.tjnu.club.info.ApiMetaInfo;
import com.tjnu.club.mapper.ApiMetaInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Component
public class ApiMetaInfoComponentImpl implements ApiMetaInfoComponent {

    @Resource
    private ApiMetaInfoMapper apiMetaInfoMapper;

    @Override
    public ApiMetaInfo getApiMetaInfoByApiName(String apiName) {
        ApiMetaInfo apiMetaInfo = apiMetaInfoMapper.getApiMetaInfoByApiName(apiName);
        if(ObjectUtils.isEmpty(apiMetaInfo)){
            throw new TJNUException(TJNUResultEnum.API_NAME_ERROR);
        }
        return apiMetaInfo;
    }
}
