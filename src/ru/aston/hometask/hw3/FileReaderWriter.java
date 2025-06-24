package ru.aston.hometask.hw3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileReaderWriter {

    private final Path filePath;

    public FileReaderWriter(String directory, String file) {

        if (directory == null || file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Directory must be NOT NULL and File be NOT EMPTY");
        }

        directory = directory.trim();
        file = file.trim();


        if (directory.isEmpty()) {
            filePath = Path.of(file).toAbsolutePath();
        } else {

            Path dir = Path.of(directory);
            if (Files.isDirectory(dir)) {
                filePath = dir.normalize().resolve(file);
            } else {
                throw new IllegalArgumentException("Directory not exist");
            }
        }
    }

    public FileReaderWriter(String file) {
        this("", file);
    }

    public List<String> readFile() throws MyIOException {
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (NoSuchFileException e) {
            throw new MyIOException(String.format("Can't find file - '%s' ", filePath), e);
        } catch (IOException e) {
            throw new MyIOException(String.format("Can't read from file - '%s' ", filePath), e);
        }
        return lines;
    }

    public boolean writeInFile(List<String> information) throws MyIOException {
        try {
            Files.write(filePath, information, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new MyIOException(String.format("Can't write in file - '%s' ", filePath), e);
        }
        return true;
    }

    public String getFilePath() {
        return filePath.toString();
    }

}
