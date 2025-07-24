package ru.aston.hometask.hw5.game;

public class FirstLetterDecorator extends GameDecorator {

    public FirstLetterDecorator(Game game) {
        super(game);
    }

    @Override
    public void play(GameRoundInfo gameInfo) {
        System.out.printf("Выбрана подсказка 1 буквы:\n%c____\n",
                gameInfo.getSecretWord().key().charAt(0));
        super.play(gameInfo);
    }
}