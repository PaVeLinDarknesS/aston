package ru.aston.hometask.hw5.dictionary;

import ru.aston.hometask.hw5.nounGenerator.NounGenerator;
import ru.aston.hometask.hw5.nounGenerator.Pair;
import ru.aston.hometask.hw5.validator.LengthValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FileDictionary implements Dictionary, NounGenerator {

    private final Path filePath;
    private final LengthValidator lengthValidator;

    public FileDictionary(Path filePath, int lengthWord) {
        this.filePath = filePath;
        this.lengthValidator = new LengthValidator(lengthWord);
    }

    public Set<String> getDictionary() {
        try {
            List<String> list = Files.readAllLines(filePath)
                    .stream()
                    .filter(lengthValidator::validate)
                    .toList();
            return new HashSet<>(list);
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
                .orElse(new Pair<>("", ""));
    }
}