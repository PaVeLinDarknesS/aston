package ru.aston.hometask.hw5.game;

public class RepeatedLettersDecorator extends GameDecorator {

    public RepeatedLettersDecorator(Game game) {
        super(game);
    }

    @Override
    public void play(GameRoundInfo gameInfo) {
        System.out.printf("Выбрана подсказка повторяющихся букв: %s\n",
                gameInfo.getSecretWord().key()
                        .chars()
                        .distinct()
                        .count() == gameInfo.getSecretWord().key().length() ? "их нет" : "они есть");
        super.play(gameInfo);
    }
}