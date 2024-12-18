package player;

import java.util.*;

public class RandomWordMatch {
    private final Map<String, String> wordLookup;
    private final List<String> ingredients;
    private final List<String> words;

    public RandomWordMatch(List<String> ingredients, List<String> words) {
        if (ingredients == null || words == null) {
            throw new IllegalArgumentException("ingredients 또는 words 리스트가 null입니다.");
        }
        this.ingredients = ingredients;
        this.words = words;
        this.wordLookup = new HashMap<>();
/*
        if (ingredients.size() == words.size()) {
            for (int i = 0; i < ingredients.size(); i++) {
                wordLookup.put(ingredients.get(i), words.get(i));
            }
        }*/
        for (int i = 0; i < ingredients.size(); i++) {
            wordLookup.put(ingredients.get(i), words.get(i));
        }

        System.out.println("wordlookup: " + wordLookup);
    }

    public void shuffle() {
        if (this.ingredients == null || this.words == null) {
            throw new IllegalArgumentException("ingredients 또는 words 리스트가 null입니다.");
        }

        if (ingredients.size() > words.size()) {
            throw new IllegalArgumentException("words 리스트가 ingredients 리스트보다 작을 수 없습니다.");
        }

        //List<String> wordListClone = new ArrayList<>(this.words);
        //Collections.shuffle(wordListClone);
        for (int i = 0; i < this.ingredients.size(); i++) {
            String ingredient = this.ingredients.get(i);
            String word = words.get(i);
            this.wordLookup.put(word, ingredient);
        }
    }

    public String getIngredientByWord(String word) {
        System.out.println("입력된 단어: " + word);
        System.out.println("리턴되는 것: " + this.wordLookup.get(word));
        return this.wordLookup.get(word);
    }

    public Set<Map.Entry<String, String>> getPairs() {
        if (wordLookup == null || wordLookup.isEmpty()) {
            throw new IllegalStateException("wordLookup is null or empty!");
        }
        return this.wordLookup.entrySet();
    }

    public List<String> getWords() {
        return this.words; // 단어 리스트 반환
    }
}