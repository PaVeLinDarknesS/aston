package ru.aston.hometask.hw5;

import ru.aston.hometask.hw5.actionHandler.ActionEnum;
import ru.aston.hometask.hw5.actionHandler.GameActionHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class WordleGame {
    public static void main(String[] args) {

        Path path;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру Вордл, для начала игры введите путь до словаря:");
        String stringPath = scanner.nextLine().trim();

        if (Files.isRegularFile(path = Path.of(stringPath))) {

            GameActionHandler handler = new GameActionHandler(path);

            while (true) {
                try {
                    System.out.println("""
                            ________________________________________________
                            Введите 'START' для начала игры
                            Введите 'FIRST' для подсказки 1 буквы
                            Введите 'REPEAT' для ответа есть ли повторяющиеся буквы в слове
                            Введите 'ATTEMPTS' для увеличения количество попыток
                            Введите 'DEFAULT' для удаления всех модификаций игры
                            Введите 'EXIT' для выхода из программы
                            """);

                    handler.handle(ActionEnum.valueOf(scanner.nextLine().trim().toUpperCase()));

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } else {
            System.err.printf("Указанный файл %s не существует \n", stringPath);
        }
    }
}