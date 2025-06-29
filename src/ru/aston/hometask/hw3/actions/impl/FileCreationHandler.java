package ru.aston.hometask.hw3.actions.impl;

import ru.aston.hometask.hw3.FileReaderWriter;
import ru.aston.hometask.hw3.actions.Handler;

import java.util.List;
import java.util.Scanner;

public class FileCreationHandler implements Handler {

    @Override
    public void doAction(Scanner scanner, List<String> info, FileReaderWriter file) {
        System.out.println("Введите Имя файла:");
        String fileName = scanner.nextLine();
        System.out.println("Введите Путь файла (по умолчанию корень проекта):");
        String directory = scanner.nextLine();
        file.changePath(new FileReaderWriter(directory, fileName));
        System.out.println("Текущий файл: " + file.getFilePath());
    }
}