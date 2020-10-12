package com.huyong.study.test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-27 9:55 下午
 */
public class MySynchronizer extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(final int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected int tryAcquireShared(final int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    @Override
    protected boolean tryRelease(final int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected boolean tryReleaseShared(final int arg) {
        return super.tryReleaseShared(arg);
    }
}
