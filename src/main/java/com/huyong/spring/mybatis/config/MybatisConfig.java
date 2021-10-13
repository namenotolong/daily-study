package com.huyong.spring.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huyong
 */
@MapperScan("com.huyong.spring.mybatis.mapper")
@Configuration
public class MybatisConfig {

}
