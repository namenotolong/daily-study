package com.huyong.spring.mybatis.mapper;

import com.huyong.spring.mybatis.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    Teacher selectById(@Param("id") Long id);
}
