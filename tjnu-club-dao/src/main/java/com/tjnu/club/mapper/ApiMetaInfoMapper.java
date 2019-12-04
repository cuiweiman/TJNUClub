package com.tjnu.club.mapper;

import com.tjnu.club.info.ApiMetaInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiMetaInfoMapper {

    ApiMetaInfo getApiMetaInfoByApiName(@Param("apiName") String apiName);

}
