package com.huyong.spring.mybatis.mapper;

import com.huyong.spring.mybatis.domain.UserInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    UserInfo selectById(@Param("id") Long id);


    List<UserInfo> selectByListAndName(@Param("list") List<Long> collection,@Param("name") String name);


    List<UserInfo> selectByList(List<Long> collection);

    @MapKey("id")
    Map<Long, UserInfo> selectMap();
}
