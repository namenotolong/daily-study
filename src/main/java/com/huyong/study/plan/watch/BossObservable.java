package com.huyong.study.plan.watch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-13 3:58 下午
 */
public class BossObservable implements Observable {

    private List<Listener> listeners = new ArrayList<>();

    @Override
    public void addListener(final Listener... listener) {
        listeners.addAll(Arrays.asList(listener));
    }

    @Override
    public void removeListener(final Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListener() {
        for (final Listener listener : listeners) {
            listener.execute();
        }
    }
}
