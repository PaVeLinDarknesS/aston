package ru.aston.hometask.hw4.task1;

import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final ReentrantLock LOCK1 = new ReentrantLock();
    private static final ReentrantLock LOCK2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LOCK1.lock();
                    System.out.println("Thread1 взял lock1, пытается получить lock2");
                    Thread.sleep(100);
                    if (LOCK2.tryLock()) {
                        System.out.println("Thread1 взял lock1 и lock2");
                        break;
                    } else {
                        System.out.println("Thread1 не смог взять lock2, освобождает lock1");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    LOCK1.unlock();
                    if (LOCK2.isHeldByCurrentThread()) {
                        LOCK2.unlock();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LOCK2.lock();
                    System.out.println("Thread2 взял lock2, пытается получить lock1");
                    Thread.sleep(100);
                    if (LOCK1.tryLock()) {
                        System.out.println("Thread2 взял lock2 и lock1");
                        break;
                    } else {
                        System.out.println("Thread2 не смог взять lock1, освобождает lock2");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    LOCK2.unlock();
                    if (LOCK1.isHeldByCurrentThread()) {
                        LOCK1.unlock();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}