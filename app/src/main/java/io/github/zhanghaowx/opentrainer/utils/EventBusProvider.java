package io.github.zhanghaowx.opentrainer.utils;

import android.content.Context;

import com.squareup.otto.Bus;

/**
 * A singleton class that providers bus to whole application
 */
public class EventBusProvider {

    private static Bus mBus;

    public static Bus getBus() {
        if(mBus == null) {
            synchronized (EventBusProvider.class) {
                if(mBus == null) {
                    mBus = new Bus();
                }
            }
        }
        return mBus;
    }

    public static void shutdown() {
        mBus = null;
    }
}
