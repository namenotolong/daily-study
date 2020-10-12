package com.huyong.study.myserver;

/**
 * @author weidian
 */

public enum RequestType {
    /**
     * get请求
     */
    GET("GET"),
    /**
     * post请求
     */
    POST("post");
    String name;

    RequestType(String name) {
        this.name = name;
    }
}
