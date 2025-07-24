package ru.aston.hometask.hw5.dictionary;

import ru.aston.hometask.hw5.nounGenerator.NounGenerator;
import ru.aston.hometask.hw5.nounGenerator.Pair;
import ru.aston.hometask.hw5.validator.LengthValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class FileDictionary implements Dictionary, NounGenerator {

    private final Path filePath;
    private final LengthValidator lengthValidator;

    public FileDictionary(Path filePath, int lengthWord) {
        this.filePath = filePath;
        this.lengthValidator = new LengthValidator(lengthWord);
    }

    public Set<String> getDictionary() {
        try {
            return Files.readAllLines(filePath)
                    .stream()
                    .filter(lengthValidator::validate)
                    .collect(Collectors.toSet());

        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public boolean contain(String word) {
        return getDictionary().contains(word);
    }

    @Override
    public Pair<String, String> getNewWord() {
        Set<String> dictionary = getDictionary();
        return dictionary.stream()
                .skip(new Random().nextInt(dictionary.size()))
                .map(x -> new Pair<>(x, ""))
                .findFirst()
                .orElseGet(() -> new Pair<>("", ""));
    }
}