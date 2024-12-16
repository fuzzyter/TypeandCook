package player;

import java.util.List;

public class WordManager {
    private static final List<String> ingredients = List.of("빵", "양상추", "토마토", "양념소스", "치킨", "치즈", "패티", "새우", "머스타드");
    private static final List<String> words = List.of("particular","investigate","magnify","conclusive","conversely","assure",
            "entire","deliberate","conjunction","sleek","afford","justified","subdue","extant","invoke");

    public static void main(String[] args){
        RandomWordMatch randomWordMatch = new RandomWordMatch(ingredients, words);

    }
}
