package ru.aston.hometask.hw3.actions.impl;

import ru.aston.hometask.hw3.FileReaderWriter;
import ru.aston.hometask.hw3.actions.Handler;

import java.util.List;
import java.util.Scanner;

public class FillingInformationHandler implements Handler {

    @Override
    public void doAction(Scanner scanner, List<String> info, FileReaderWriter file) {
        info.clear();
        System.out.println("\nВведите текст для записи. \nВведите пустую стоку для окончания");
        while (true) {
            String str = scanner.nextLine();
            if (str.isEmpty()) {
                break;
            }
            info.add(str);
        }
        System.out.println("Сохраненная информация: " + info);
    }
}