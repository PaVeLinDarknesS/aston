package ru.aston.hometask.hw4.task1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private static final ReentrantLock LOCK_1 = new ReentrantLock();
    private static final ReentrantLock LOCK_2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new DeadLockRunnable("Thread1", LOCK_1, LOCK_2));
        Thread thread2 = new Thread(new DeadLockRunnable("Thread2", LOCK_2, LOCK_1));

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

        thread1.join();
        thread2.join();
    }
}