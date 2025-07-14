package ru.aston.hometask.hw5.game;

import java.util.Scanner;

public class ExtraAttemptsDecorator extends GameDecorator {

    public ExtraAttemptsDecorator(Game game) {
        super(game);
    }

    @Override
    public void play(GameRoundInfo gameInfo) {
        System.out.println("Введите количесво дополнительных попыток [1,16]");
        int addedAttempts;
        if ((addedAttempts = new Scanner(System.in).nextInt()) > 0 && addedAttempts < 17) {
            gameInfo.setMaxAttempts(gameInfo.getMaxAttempts() + addedAttempts);
        } else {
            System.out.println("Введено число не из диапазона [1,16], дополнительные попытки добавлены не будут");
        }
        super.play(gameInfo);
    }
}