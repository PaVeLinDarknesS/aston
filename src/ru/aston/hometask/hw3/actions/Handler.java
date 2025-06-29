package ru.aston.hometask.hw3.actions;

import ru.aston.hometask.hw3.FileReaderWriter;
import ru.aston.hometask.hw3.MyIOException;

import java.util.List;
import java.util.Scanner;

public interface Handler {

    void doAction(Scanner scanner,
                  List<String> info,
                  FileReaderWriter file) throws MyIOException;

}