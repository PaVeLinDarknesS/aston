package ru.aston.hometask.hw5.validator;

public abstract class WordValidator {

    private WordValidator next;

    public WordValidator setNext(WordValidator next) {
        this.next = next;
        return next;
    }

    public abstract boolean validate(String word);

    protected boolean hasNext(String word) {
        return next == null || next.validate(word);
    }
}