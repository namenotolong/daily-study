package com.huyong.study.utils;


import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 1:45 下午
 */
public class EnumUtils {
    public static <R, E extends Enum<E>> Map<R, E> getValueMap(Class<E> clazz, Function<E, R> func) {
        return EnumSet.allOf(clazz).stream().collect(Collectors.toMap(func, e -> e));
    }
}
