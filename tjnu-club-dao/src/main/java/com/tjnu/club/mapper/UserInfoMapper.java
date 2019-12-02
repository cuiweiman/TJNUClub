package com.tjnu.club.mapper;

import com.tjnu.club.info.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    Integer saveUserInfo(UserInfo userInfo);

    Integer updateUserInfo(UserInfo userInfo);

    Integer deleteUserInfo(@Param("userId") String userId);

    UserInfo getUserInfoByUserId(String userId);

    UserInfo getUserInfoByNickName(String nickName);

    UserInfo getUserInfoByEmail(String email);


    List<UserInfo> listUserInfo();


}
