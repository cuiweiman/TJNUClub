package com.tjnu.club.component;

import com.tjnu.club.info.ApiMetaInfo;

/**
 * 网关接口服务
 */
public interface ApiMetaInfoService {

    ApiMetaInfo getApiMetaInfoByApiName(String apiName);

}
