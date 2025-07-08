package ru.aston.hometask.hw4.task1;

import java.util.concurrent.locks.ReentrantLock;

public class LiveLockRunnable implements Runnable {

    private final ReentrantLock lock1;
    private final ReentrantLock lock2;
    private final String name;

    public LiveLockRunnable(String name, ReentrantLock lock1, ReentrantLock lock2) {
        this.name = name;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                lock1.lock();
                System.out.printf("%s взял %s, пытается получить %s \n", name, lock1, lock2);
                Thread.sleep(300);
                if (lock2.tryLock()) {
                    System.out.printf("%s взял %s и %s \n", name, lock1, lock2);
                    break;
                } else {
                    System.out.printf("%s не смог взять %s, освобождает %s \n", name, lock2, lock1);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                lock1.unlock();
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }
    }
}