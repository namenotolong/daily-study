package com.huyong.study.plan.memento;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-10-13 3:33 下午
 */
public class Caretaker {
    Memoto memoto;
    public void archive(Memoto memoto) {
        this.memoto = memoto;
    }

    public Memoto getMemoto() {
        return memoto;
    }
}
