package com.example.fuzz.util;

public class ThreadLocalUtils {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    public static String getCurrentUserId() {
        return contextHolder.get();
    }

    public static void setCurrentUserId(String userId) {
        contextHolder.set(userId);
    }
}
