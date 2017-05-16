package com.lhrl.common.util;

/**
 *
 * @author Mei Xianzhi
 */
public class ThreadLocalUserUtils {

    private static final ThreadLocal<String> LOCAL_USERNAME = new ThreadLocal<>();

    /**
     * 设置当前线程绑定的用户
     *
     * @param userName
     */
    public static void setUser(String userName) {
        LOCAL_USERNAME.set(userName);
    }

    /**
     * 移除当前线程绑定的用户
     */
    public static void removeUser() {
        LOCAL_USERNAME.remove();
    }

    /**
     * 获取当前线程绑定的用户
     *
     * @return
     */
    public static String getUser() {
        return LOCAL_USERNAME.get();
    }

}
