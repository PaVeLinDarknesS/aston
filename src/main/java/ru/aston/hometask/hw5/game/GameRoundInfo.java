package ru.aston.hometask.hw5.game;

import ru.aston.hometask.hw5.nounGenerator.Pair;
import ru.aston.hometask.hw5.validator.WordValidator;

public class GameRoundInfo {

    private final Pair<String, String> secretWord;
    private final WordValidator validator;
    private int maxAttempts;

    public static GameRoundInfo.GameRoundBuilder builder() {
        return new GameRoundInfo.GameRoundBuilder();
    }

    private GameRoundInfo(GameRoundBuilder builder) {
        this.secretWord = builder.secretWord;
        this.maxAttempts = builder.maxAttempts;
        this.validator = builder.validator;
    }

    public static class GameRoundBuilder {
        private Pair<String, String> secretWord;
        private int maxAttempts;
        private WordValidator validator;

        public GameRoundBuilder secretWord(Pair<String, String> secretWord) {
            this.secretWord = secretWord;
            return this;
        }

        public GameRoundBuilder maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return this;
        }

        public GameRoundBuilder validator(WordValidator validator) {
            this.validator = validator;
            return this;
        }

        public GameRoundInfo build() {
            return new GameRoundInfo(this);
        }
    }

    public Pair<String, String> getSecretWord() {
        return secretWord;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public WordValidator getValidator() {
        return validator;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

}