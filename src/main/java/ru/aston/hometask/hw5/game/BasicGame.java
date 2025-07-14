package ru.aston.hometask.hw5.game;

import ru.aston.hometask.hw5.nounGenerator.Pair;

import java.util.Scanner;

public class BasicGame implements Game {

    private static final char CORRECT = '✔';
    private static final char WRONG = '✘';
    private static final char ANOTHER_PLACE = '↺';

    @Override
    public void play(GameRoundInfo gameInfo) {
        int maxAttempts = gameInfo.getMaxAttempts();
        Pair<String, String> secretWord = gameInfo.getSecretWord();
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.printf("Угадайте слово из %d букв. У вас %d попыток. \n", secretWord.key().length(), maxAttempts);

        while (attempts < maxAttempts) {
            System.out.print("Попытка " + (attempts + 1) + ": ");
            String guessWord = scanner.nextLine().trim().toLowerCase();

            if (!gameInfo.getValidator().validate(guessWord)) {
                continue;
            }

            if (guessWord.equals(secretWord.key())) {
                System.out.println("Поздравляем! Вы угадали слово!");
                if (!secretWord.value().isBlank()) {
                    System.out.printf("Его значение: %s\n", secretWord.value());
                }
                return;
            }

            System.out.println(checkWord(secretWord.key(), guessWord));
            attempts++;
        }

        System.out.printf("Игра окончена. Загаданное слово: %s,\nа его значение: %s\n",
                secretWord.key(), secretWord.value().isBlank() ? "пока не найдено" : secretWord.value());
    }

    private String checkWord(String hiddenWord, String guessWord) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hiddenWord.length(); i++) {
            char c = guessWord.charAt(i);
            if (hiddenWord.charAt(i) == c) {
                result.append(CORRECT);
            } else if (hiddenWord.contains(String.valueOf(c))) {
                result.append(ANOTHER_PLACE);
            } else {
                result.append(WRONG);
            }
        }
        return result.toString();
    }
}