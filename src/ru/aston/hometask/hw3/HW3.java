package ru.aston.hometask.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> info = new ArrayList<>();
        FileReaderWriter file = new FileReaderWriter("new.txt");
        while (true) {
            try {
                System.out.println("""
                        ________________________________________________
                        Введите file для выбора файла
                        Введите info для ввода информации для сохранения
                        Введите work для работы с файлом
                        Введите exit для выхода из программы
                        """);

                String str = scanner.nextLine();

                switch (str) {
                    case "file" -> {
                        file = chooseFile(scanner);
                        System.out.println("Текущий файл: " + file.getFilePath());
                    }
                    case "info" -> {
                        info = getListString(scanner);
                        System.out.println("Сохраненная информация: " + info);
                    }
                    case "work" -> workWithFile(scanner, file, info);
                    case "exit" -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void workWithFile(Scanner scanner, FileReaderWriter file, List<String> info) {
        System.out.println("""
                Введите read чтобы прочитать из файла
                Введите write чтобы записать в файл
                Введите back для выхода в предыдущее меню
                """);
        while (true) {
            try {
                switch (scanner.nextLine()) {
                    case "back" -> {
                        return;
                    }
                    case "read" -> System.out.println(file.readFile());
                    case "write" -> {
                        if (file.writeInFile(info)) {
                            System.out.println("Information was wrote");
                        }
                    }
                }
            } catch (MyIOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static FileReaderWriter chooseFile(Scanner scanner) {
        System.out.println("Введите Имя файла:");
        String file = scanner.nextLine();
        System.out.println("Введите Путь файла (по умолчанию корень проекта):");
        String directory = scanner.nextLine();
        return new FileReaderWriter(directory, file);
    }

    private static List<String> getListString(Scanner scanner) {
        List<String> info = new ArrayList<>();
        System.out.println("\nВведите текст для записи. \nВведите пустую стоку для окончания");
        while (true) {
            String str = scanner.nextLine();
            if (str.isEmpty()) {
                break;
            }
            info.add(str);
        }
        return info;
    }
}
