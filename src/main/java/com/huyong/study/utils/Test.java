package com.huyong.study.utils;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 2:11 下午
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Map<Integer, StatusEnum> collect = EnumSet.allOf(StatusEnum.class).stream().collect(Collectors.toMap(StatusEnum::getCode, e -> e));
        System.out.println(collect);
        System.out.println(Integer.valueOf(""));
    }
}
