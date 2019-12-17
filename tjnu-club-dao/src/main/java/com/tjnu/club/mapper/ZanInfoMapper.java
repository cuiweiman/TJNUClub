package com.tjnu.club.mapper;

import com.tjnu.club.info.ZanInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZanInfoMapper {

    /**
     * 点赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    Integer zan(@Param("userId") String userId, @Param("blogId") String blogId);


    /**
     * 取消赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    Integer zanCancel(@Param("userId") String userId, @Param("blogId") String blogId);


    /**
     * 用于判断是否已点赞
     *
     * @param userId
     * @param blogId
     * @return
     */
    ZanInfo getZan(@Param("userId") String userId, @Param("blogId") String blogId);


    /**
     * 获取点赞数量
     *
     * @param blogId
     * @return
     */
    Integer zanCount(@Param("blogId") String blogId);

}
