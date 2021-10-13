package com.huyong.spring.mybatis;

import com.google.gson.Gson;
import com.huyong.spring.mybatis.domain.Teacher;
import com.huyong.spring.mybatis.domain.UserInfo;
import com.huyong.spring.mybatis.mapper.TeacherMapper;
import com.huyong.spring.mybatis.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        System.out.println(gson);
    }
}
