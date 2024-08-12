package com.edp.edp_proj;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusInstance {

    private static EventBus eventBusInstance = new AsyncEventBus(Executors.newCachedThreadPool());

    public static EventBus getEventBus() {
        return eventBusInstance;
    }

}