package ru.aston.hometask.hw5.validator;

public class LengthValidator extends WordValidator {

    private final int wordLength;

    public LengthValidator(int wordLength) {
        this.wordLength = wordLength;
    }

    @Override
    public boolean validate(String word) {
        if (word.length() != wordLength) {
            System.out.printf("Слово дожно состоять из %d символов \n", wordLength);
            return false;
        }
        return hasNext(word);
    }
}