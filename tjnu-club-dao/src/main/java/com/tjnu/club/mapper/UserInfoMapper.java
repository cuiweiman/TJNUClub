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

    UserInfo getUserInfoByUserId(@Param("userId") String userId);

    UserInfo getUserInfoByNickName(@Param("nickName") String nickName);

    UserInfo getUserInfoByEmail(@Param("email") String email);


    List<UserInfo> listUserInfo();


}
