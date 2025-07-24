package ru.aston.hometask.hw5.game;

public abstract class GameDecorator implements Game {

    protected Game game;

    public GameDecorator(Game game) {
        this.game = game;
    }

    @Override
    public void play(GameRoundInfo gameInfo) {
        game.play(gameInfo);
    }
}