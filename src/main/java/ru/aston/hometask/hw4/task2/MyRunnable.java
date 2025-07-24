package ru.aston.hometask.hw4.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable {

    private static final Lock LOCK = new ReentrantLock();
    private static final Condition WRONG_THREAD_QUEUE = LOCK.newCondition();
    private static final int DEFAULT_COUNT_THREAD = 2;

    private static int countThreads = DEFAULT_COUNT_THREAD;
    private static volatile int turn = 1;

    private final int number;

    public MyRunnable(int number) {
        this(number, DEFAULT_COUNT_THREAD);
    }

    public MyRunnable(int number, int countThreads) {
        this.number = number;
        MyRunnable.countThreads = countThreads;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                LOCK.lock();
                while (number != turn) {
                    WRONG_THREAD_QUEUE.await();
                }
                System.out.println(number == countThreads ? countThreads + "\n" : number);
                Thread.sleep(100);
                turn = turn % countThreads + 1;
                WRONG_THREAD_QUEUE.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                LOCK.unlock();
            }
        }
    }
}