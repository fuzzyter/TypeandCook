package player;

import customer.RecipeCheck;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final RandomWordMatch wordMatcher;
    private final RecipeCheck recipeMatcher;
    private final Set<String> ingredients;

    public Player(RandomWordMatch wordMatcher, RecipeCheck recipeMatcher) {
        this.wordMatcher = wordMatcher;
        this.recipeMatcher = recipeMatcher;
        this.ingredients = new HashSet<>();
    }

    // 재료 획득
    public void onKeyDown(String value) {
        String ingredient = this.wordMatcher.getIngredientByWord(value);
        if (ingredient != null) {
            this.ingredients.add(ingredient);
            System.out.println("현재 재료들: " + this.ingredients); // 현재 재료를 출력
        } else {
            System.out.println("재료가 일치하지 않습니다." + value);
        }
    }

    // 요리 조리
    public void onKeyDown2(String value) {
        String recipe = this.recipeMatcher.getRecipeByIngredients(this.ingredients);
        if (recipe != null) {
            // 손님 대기열에서 재료에 해당하는 손님 삭제
        }
    }

    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public RandomWordMatch getRandomWordMatch() {
        return wordMatcher;
    }
}