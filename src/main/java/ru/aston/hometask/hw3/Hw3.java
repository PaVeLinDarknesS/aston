package ru.aston.hometask.hw3;

import java.util.Scanner;

public class Hw3 {

    public static void main(String[] args) {

        FileActionHandler handler = new FileActionHandler("new.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("""
                        ________________________________________________
                        Введите 'INFO для ввода информации для сохранения
                        Введите 'CHOOSE' для выбора файла
                        Введите 'READ' чтобы прочитать из файла
                        Введите 'WRITE' чтобы записать в файл
                        Введите 'EXIT' для выхода из программы
                        """);

                handler.handle(ActionEnum.valueOf(scanner.nextLine().trim().toUpperCase()));

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}