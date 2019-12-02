package com.tjnu.club.mapper;

import com.tjnu.club.info.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    List<UserInfo> listUserInfo();


}
