package ru.aston.hometask.hw3;

import ru.aston.hometask.hw3.actions.Handler;
import ru.aston.hometask.hw3.actions.impl.FileCreationHandler;
import ru.aston.hometask.hw3.actions.impl.FillingInformationHandler;

import java.util.*;

public class FileActionHandler {

    private final Map<ActionEnum, Handler> resolver;
    private final Scanner scanner;
    private final FileReaderWriter file;
    private final List<String> info;

    public FileActionHandler(String fileName) {
        scanner = new Scanner(System.in);
        info = new ArrayList<>();
        file = new FileReaderWriter(fileName);

        resolver = new EnumMap<>(ActionEnum.class);
        resolver.put(ActionEnum.CHOOSE, new FileCreationHandler());
        resolver.put(ActionEnum.INFO, new FillingInformationHandler());
        resolver.put(ActionEnum.WRITE, (scanner, info, file) ->
                file.writeInFile(info));
        resolver.put(ActionEnum.READ, (scanner, info, file) ->
                System.out.println(file.readFile()));
        resolver.put(ActionEnum.EXIT, (scanner, info, file) ->
                System.exit(0));
    }

    public void handle(ActionEnum action) throws MyIOException {
        resolver.get(action).doAction(scanner, info, file);
    }
}