package com.huyong.jdk11;


import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Test {
    public Test() {
        System.out.println("i am construct");
    }

    {
        System.out.println("i am common code block");
    }

    static {
        System.out.println("i am static code block");
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        System.out.println(Son.age);
        String s = new String();
        System.out.println(s);
    }

    public static void http() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder().uri(new URI("https://www.baidu.com")).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }
}
class Parent{
    public static int age;
    public Parent() {
        System.out.println("i am parent construct");
    }
    static {
        System.out.println("i am parent static code block");
    }
    {
        System.out.println("i am parent common code block");
    }
}
class Son extends Parent {
    public Son() {
        System.out.println("i am son");
    }

    static {
        System.out.println("i am son static code");
    }
}
