package com.example.service;

/**
 * @author zhougaojun
 */
public class UserSingletonService {

    private volatile static UserSingletonService userSingleton;

    /**
     * 性能高的单例模式，推荐方式 👍👍👍👍👍
     * userSingleton 要用 volatile 修饰禁止指令重排，防止拿到未初始化完成的对象
     *
     * @return
     */
    public static UserSingletonService getInstance() {
        if (userSingleton == null) {
            synchronized (UserSingletonService.class) {
                if (userSingleton == null) {
                    userSingleton = new UserSingletonService();
                }
            }
        }
        return userSingleton;
    }

    /**
     * 性能差的单例模式，锁粒度过大
     *
     * @return
     */
    public static UserSingletonService getInstance2() {
        synchronized (UserSingletonService.class) {
            if (userSingleton == null) {
                userSingleton = new UserSingletonService();
            }
            return userSingleton;
        }
    }

    /**
     * 不安全的单利模式，高并发先无法保证单例
     *
     * @return
     */
    public static UserSingletonService getInstance3() {
        if (userSingleton == null) {
            synchronized (UserSingletonService.class) {
                userSingleton = new UserSingletonService();
            }
        }
        return userSingleton;
    }

    private UserSingletonService() {
        System.out.println("实例化一次对象");
    }
}
