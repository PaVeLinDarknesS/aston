package ru.aston.hometask.hw5.dictionary;

import ru.aston.hometask.hw5.nounGenerator.NounGenerator;
import ru.aston.hometask.hw5.nounGenerator.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FileDictionaryProxy implements Dictionary, NounGenerator {

    private final FileDictionary fileDictionary;
    private Set<String> cache;

    public FileDictionaryProxy(FileDictionary fileDictionary) {
        this.fileDictionary = fileDictionary;
        cache = new HashSet<>();
    }

    @Override
    public boolean contain(String word) {
        checkCache();
        return cache.contains(word);
    }

    @Override
    public Pair<String, String> getNewWord() {
        checkCache();
        return cache.stream()
                .skip(new Random().nextInt(cache.size()))
                .map(x -> new Pair<>(x, ""))
                .findFirst()
                .orElse(new Pair<>("", ""));
    }

    private void checkCache() {
        if (cache.isEmpty()) {
            cache = fileDictionary.getDictionary();
            System.out.printf("Словарь загружен. Кол-во элементов: %d\n", cache.size());
        }
    }

    public void clearCache() {
        cache.clear();
    }
}