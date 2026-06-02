package com.hotelmanagement.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolConfig {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(5);

    private ThreadPoolConfig() {
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}
