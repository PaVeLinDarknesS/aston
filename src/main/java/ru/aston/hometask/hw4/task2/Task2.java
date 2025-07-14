package ru.aston.hometask.hw4.task2;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        System.out.println("Введите количество потоков:");
        int countThreads = new Scanner(System.in).nextInt();

        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread(new MyRunnable(i + 1, countThreads));
            thread.start();
        }
    }
}