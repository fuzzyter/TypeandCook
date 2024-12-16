package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private static final String[] WORDS = {
            "particular", "investigate", "magnify", "conclusive", "conversely",
            "assure", "entire", "deliberate", "conjunction", "sleek",
            "afford", "justified", "subdue", "extant", "invoke"
    };

    public static List<String> generateRandomWords() {
        Random random = new Random();
        List<String> randomWords = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            randomWords.add(WORDS[random.nextInt(WORDS.length)]);
        }
        return randomWords;
    }
}
