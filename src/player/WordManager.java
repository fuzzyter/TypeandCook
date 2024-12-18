package player;

import java.util.List;

public class WordManager {
    private static final List<String> ingredients = List.of("빵", "양상추", "토마토", "양념소스", "치킨", "치즈", "패티", "새우", "머스타드");
    private static final List<String> words = List.of("bread","investigate","magnify","conclusive","conversely","assure",
            "entire","deliberate","conjunction","sleek","afford","justified","subdue","extant","invoke");

    //private static final RandomWordMatch randomWordMatch = new RandomWordMatch(ingredients, words);
    private static final RandomWordMatch randomWordMatch;

    static {
        randomWordMatch = new RandomWordMatch(ingredients, words);
        randomWordMatch.shuffle(); // 초기화 시 한 번만 셔플
    }

    public static RandomWordMatch getRandomWordMatch() {
        return randomWordMatch;
    }
}
