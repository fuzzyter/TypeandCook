package player;

import java.util.*;

public class RandomWordMatch {
    private final Map<String, String> wordLookup;
    private final List<String> ingredients;
    private final List<String> words;

    public RandomWordMatch(List<String> ingredients, List<String> words) {
        this.ingredients = ingredients;
        this.words = words;
        this.wordLookup = new HashMap<>();
    }

    public void shuffle() {
        List<String> wordListClone = new ArrayList<>(this.words);
        Collections.shuffle(wordListClone);
        for (int i = 0; i < this.ingredients.size(); i++) {
            String ingredient = this.ingredients.get(i);
            String word = wordListClone.get(i);
            this.wordLookup.put(word, ingredient);
        }
    }

    public String getIngredientByWord(String word) {
        return this.wordLookup.get(word);
    }

    public Set<Map.Entry<String, String>> getPairs() {
        return this.wordLookup.entrySet();
    }
}