package ru.aston.hometask.hw5.nounGenerator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class InternalWordApi {

    private static final String RANDOM_WORD_URL = "https://sanstv.ru/randomWord/lang-ru/strong-2/count-%d/word-%s";
    private static final String LETTER_PATTERN = "%3F";

    private final String url;

    public InternalWordApi(int countGenericWords, int wordLength) {
        if (countGenericWords > 100 || countGenericWords < 0) {
            throw new IllegalArgumentException("Нельзя сгенерировать введенное число слов");
        }
        this.url = String.format(RANDOM_WORD_URL, countGenericWords,
                new StringBuilder().repeat(LETTER_PATTERN, wordLength));
    }

    public Map<String, String> getNounDictionary() {
        try {
            Document docCustomConn = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("http://google.com")
                    .get();

            return Objects.requireNonNull(docCustomConn.selectFirst(".list,ellipsis"))
                    .getElementsByTag("li").stream()
                    .collect(Collectors.toMap(x -> x.getElementsByTag("a").text(),
                            x -> x.getElementsByTag("li").eachAttr("title").stream()
                                    .filter(l -> !l.isEmpty()).findFirst().orElse(""))
                    );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}