package com.huyong.spring;

import com.google.common.collect.Lists;
import com.huyong.spring.bean.*;
import com.huyong.spring.event.Dog;
import com.huyong.spring.event.Event;
import com.huyong.spring.event.PeopleEvent;
import com.huyong.spring.event.UserEvent;
import com.huyong.spring.mybatis.domain.UserInfo;
import com.huyong.spring.mybatis.mapper.TeacherMapper;
import com.huyong.spring.mybatis.mapper.UserInfoMapper;
import com.huyong.spring.publish.Publish;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;

/**
 * @author huyong
 */
public class Test1 {

    public static void main(String[] args) {
        //xml();
        //annotation();

        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        list.stream().parallel().forEach(e -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(e);
            try {
                Thread.sleep(300);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
    }

    public static void xml() {
        ApplicationContext xml = new ClassPathXmlApplicationContext("spring.xml");
        UserInfoMapper bean = xml.getBean(UserInfoMapper.class);
        UserInfo userInfo = bean.selectById(1L);
        System.out.println(userInfo);
    }



















    public static void annotation() {
        ApplicationContext annotation = new AnnotationConfigApplicationContext("com.huyong.spring");
        Techer bean = annotation.getBean(Techer.class);
        System.out.println(bean);
    }



    public static void testResource() {
        URL resource = Test1.class.getResource("");
        System.out.println(resource);
        try {
            Enumeration<URL> resources = Test1.class.getClassLoader().getResources("spring.xml");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                URLConnection urlConnection = url.openConnection();
                new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).lines().forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
