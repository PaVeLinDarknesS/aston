package ru.aston.hometask.hw5.actionHandler;

import ru.aston.hometask.hw5.dictionary.Dictionary;
import ru.aston.hometask.hw5.dictionary.FileDictionary;
import ru.aston.hometask.hw5.dictionary.FileDictionaryProxy;
import ru.aston.hometask.hw5.game.*;
import ru.aston.hometask.hw5.nounGenerator.InternalWordApi;
import ru.aston.hometask.hw5.nounGenerator.NounGenerator;
import ru.aston.hometask.hw5.nounGenerator.NounGeneratorAdapter;
import ru.aston.hometask.hw5.validator.DictionaryValidator;
import ru.aston.hometask.hw5.validator.LengthValidator;
import ru.aston.hometask.hw5.validator.WordValidator;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class GameActionHandler {

    private final static int DEFAULT_ATTEMPTS = 6;
    private final static int WORD_LENGTH = 5;

    private final Map<ActionEnum, Handler> resolver;
    private final Dictionary dictionary;
    private final NounGenerator nounGenerator;
    private final WordValidator wordValidator;

    private GameRoundInfo gameRoundInfo;
    private Game game;

    public GameActionHandler(Path dictionaryPath) {
        dictionary = new FileDictionaryProxy(new FileDictionary(
                dictionaryPath, WORD_LENGTH));
        nounGenerator = new NounGeneratorAdapter(new InternalWordApi(15, WORD_LENGTH));

        wordValidator = new LengthValidator(WORD_LENGTH);
        wordValidator.setNext(new DictionaryValidator(dictionary));

        gameRoundInfo = getNewRound();
        game = new BasicGame();

        resolver = new EnumMap<>(ActionEnum.class);

        resolver.put(ActionEnum.FIRST, () -> {
            game = new FirstLetterDecorator(game);
            System.out.println("Подсказка 1 буквы включена");
        });
        resolver.put(ActionEnum.REPEAT, () -> {
            game = new RepeatedLettersDecorator(game);
            System.out.println("Подсказка наличия повторяющихся букв включена");
        });
        resolver.put(ActionEnum.ATTEMPTS, () -> {
            game = new ExtraAttemptsDecorator(game);
            System.out.println("Режим увеличения попыток включен");
        });
        resolver.put(ActionEnum.DEFAULT, () -> {
            game = new BasicGame();
            System.out.println("Все модификаторы удалены");
        });
        resolver.put(ActionEnum.START, () -> {
            game.play(gameRoundInfo);
            gameRoundInfo = getNewRound();
        });
        resolver.put(ActionEnum.EXIT, () -> System.exit(0));

    }

    public void handle(ActionEnum action) {
        resolver.get(action).doAction();
    }

    private GameRoundInfo getNewRound() {
        return new GameRoundInfo.GameRoundBuilder()
                .maxAttempts(GameActionHandler.DEFAULT_ATTEMPTS)
                .secretWord(nounGenerator.getNewWord())
                .validator(wordValidator)
                .build();
    }
}