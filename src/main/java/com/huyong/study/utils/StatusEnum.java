package com.huyong.study.utils;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 2:09 下午
 */
public enum StatusEnum {
    ONE(1, "one"), TWO(2, "two"), THREE(3, "three");
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    StatusEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StatusEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }


}
