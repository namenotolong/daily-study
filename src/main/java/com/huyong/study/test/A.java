package com.huyong.study.test;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-09-10 4:22 下午
 */
public class A {
    public static void main(String[] args) {
        new B();
    }
}
class B {
    C c = new C();
}
class C {
    B b = new B();
}
