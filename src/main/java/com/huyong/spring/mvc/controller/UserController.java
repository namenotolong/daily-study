package com.huyong.spring.mvc.controller;

import com.huyong.spring.mybatis.domain.UserInfo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author huyong
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private BeanFactory beanFactory;

    @Bean
    public String test() {
        System.out.println("hello world");
        System.out.println(beanFactory.getBean(UserController.class));
        return "1";
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        ArrayList<MediaType> objects = new ArrayList<>();
        objects.add(MediaType.APPLICATION_JSON);
        stringHttpMessageConverter.setSupportedMediaTypes(objects);
        return stringHttpMessageConverter;
    }

    @GetMapping("/gerUser")
    @ResponseBody
    public UserInfo getUser(@RequestParam("name") String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setId(1L);
        return userInfo;
    }

    @GetMapping("/info")
    public String info(@RequestParam("name") String name) {
       return name;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name) {
        return name;
    }

}
