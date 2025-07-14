package ru.aston.hometask.hw5.nounGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NounGeneratorAdapter implements NounGenerator {

    private final InternalWordApi wordApi;
    private List<Pair<String, String>> nounDictionary;

    public NounGeneratorAdapter(InternalWordApi wordApi) {
        this.wordApi = wordApi;
        nounDictionary = new ArrayList<>();
    }

    @Override
    public Pair<String, String> getNewWord() {
        if (nounDictionary.isEmpty()) {
            nounDictionary = convertMapToList(wordApi.getNounDictionary());
        }
        return nounDictionary.removeLast();
    }

    private List<Pair<String, String>> convertMapToList(Map<String, String> map) {
        System.out.println("Секрентые слова успешно получены");
        return map.entrySet().stream()
                .map(x -> new Pair<>(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }
}