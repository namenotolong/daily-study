package com.huyong.study.plan.memento;

/**
 * 描述: 备忘录，避免了直接操作对象
 *
 * @author huyong
 * @date 2020-10-13 11:23 上午
 */
public class Test {
    public static void main(String[] args) {
        PersonOriginator personOriginator = new PersonOriginator();
        personOriginator.age = 10;
        personOriginator.hp = 100;
        Memoto memoto = personOriginator.createMemoto();
        System.out.println(personOriginator);
        personOriginator.age = 100;
        personOriginator.hp = 1000;
        System.out.println(personOriginator);
        personOriginator.reStore(memoto);
        System.out.println(personOriginator);
    }
}
