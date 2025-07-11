package ru.aston.hometask.hw4.task1;

import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final ReentrantLock LOCK_1 = new ReentrantLock();
    private static final ReentrantLock LOCK_2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new LiveLockRunnable("Thread1", LOCK_1, LOCK_2));
        Thread thread2 = new Thread(new LiveLockRunnable("Thread2", LOCK_2, LOCK_1));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}