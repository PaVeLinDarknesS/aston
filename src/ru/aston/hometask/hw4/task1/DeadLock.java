package ru.aston.hometask.hw4.task1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private static final ReentrantLock LOCK1 = new ReentrantLock();
    private static final ReentrantLock LOCK2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            LOCK1.lock();
            System.out.println("Thread1 удерживает lock1");
            try {
                Thread.sleep(300);
                LOCK2.lock();
                System.out.println("Thread1 удерживает lock1 и lock2");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                LOCK1.unlock();
                if (LOCK2.isHeldByCurrentThread()) {
                    LOCK2.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            LOCK2.lock();
            System.out.println("Thread2 удерживает lock2");
            try {
                Thread.sleep(200);
                LOCK1.lock();
                System.out.println("Thread2 удерживает lock2 и lock1");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                LOCK2.unlock();
                if (LOCK1.isHeldByCurrentThread()) {
                    LOCK1.unlock();
                }
            }
        });

        thread2.start();
        thread1.start();

        Thread.sleep(2000);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (deadlockedThreads != null) {
            System.out.println("Программа находится в DeadLock:");
            Arrays.stream(threadMXBean.getThreadInfo(deadlockedThreads))
                    .forEach(System.out::println);
        }
    }
}