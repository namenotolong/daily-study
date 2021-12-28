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
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        LoggerFactory.getLogger(Test.class).info("${JAVA_HOME}");
    }

    public static void http() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder().uri(new URI("https://www.baidu.com")).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }
}
