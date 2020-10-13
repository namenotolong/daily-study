package com.huyong.study.plan.memento;

/**
 * 描述: 人 originator
 *
 * @author huyong
 * @date 2020-10-13 2:04 下午
 */
public class PersonOriginator {
    String name ;
    int age;
    int hp;

    public Memoto createMemoto() {
        Memoto memoto = new Memoto();
        memoto.age = age;
        memoto.hp = hp;
        return memoto;
    }

    public void reStore(Memoto memoto) {
        this.age = memoto.age;
        this.hp = memoto.age;
    }

    @Override
    public String toString() {
        return "PersonOriginator{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hp=" + hp +
                '}';
    }
}
