package ru.aston.hometask.hw5.validator;

import ru.aston.hometask.hw5.dictionary.Dictionary;

public class DictionaryValidator extends WordValidator {

    private final Dictionary dictionary;

    public DictionaryValidator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean validate(String word) {
        if (!dictionary.contain(word)) {
            System.out.printf("Введенного слова '%s' нет в словаре \n", word);
            return false;
        }
        return hasNext(word);
    }
}