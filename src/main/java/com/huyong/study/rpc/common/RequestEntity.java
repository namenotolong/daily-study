package com.huyong.study.rpc.common;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 9:19 下午
 */
public class RequestEntity implements Serializable {
    private Class<?> clazz;

    private String method;

    private Object[] params;

    private Class<?>[] types;

    public Class<?>[] getTypes() {
        return types;
    }

    public void setTypes(final Class<?>[] types) {
        this.types = types;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(final Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(final Object[] params) {
        this.params = params;
    }
}
