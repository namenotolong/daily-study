package com.huyong.study.myserver;

import java.util.Map;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-09 9:52 下午
 */
public interface IRequest {
    Map<String, String> getParams();
    String getParam(String name);
}
