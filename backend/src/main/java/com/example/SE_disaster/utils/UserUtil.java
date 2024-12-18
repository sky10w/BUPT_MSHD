package com.example.SE_disaster.utils;

public class UserUtil {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setId(Long uid) {
        threadLocal.set(uid);
    }

    public static Long getId() {
        return threadLocal.get();
    }

    public static void removeId() {
        threadLocal.remove();
    }
}
