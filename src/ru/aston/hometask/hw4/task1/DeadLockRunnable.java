package ru.aston.hometask.hw4.task1;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockRunnable implements Runnable {

    private final ReentrantLock lock1;
    private final ReentrantLock lock2;
    private final String name;

    public DeadLockRunnable(String name, ReentrantLock lock1, ReentrantLock lock2) {
        this.name = name;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        System.out.printf("%s удерживает %s \n", name, lock1);
        try {
            Thread.sleep(300);
            lock2.lock();
            System.out.printf("%s удерживает %s и %s \n", name, lock1, lock2);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            lock1.unlock();
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }
}