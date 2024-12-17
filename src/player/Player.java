package player;

import customer.CustomerListManager;
import customer.RecipeCheck;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final RandomWordMatch wordMatcher;
    private final RecipeCheck recipeMatcher;
    private final Set<String> ingredients;
    public int result = 0;

    public Player(RandomWordMatch wordMatcher, RecipeCheck recipeMatcher) {
        this.wordMatcher = wordMatcher;
        this.recipeMatcher = recipeMatcher;
        this.ingredients = new HashSet<>();
    }

    // 재료 획득
    public void onKeyDown(String value) {
        String ingredient = this.wordMatcher.getIngredientByWord(value.trim());
        if (ingredient != null) {
            this.ingredients.add(ingredient);
            System.out.println("현재 재료들: " + this.ingredients); // 현재 재료를 출력
        } else {
            System.out.println("재료가 일치하지 않습니다." + value);
        }
    }

    // 요리 조리
    public int onKeyDown2() {/*
        String recipe = this.recipeMatcher.getRecipeByIngredients(this.ingredients);

        if (recipe != null) {
            System.out.println("요리가 완성되었습니다! 레시피: " + recipe);
            this.ingredients.clear(); // 요리가 완성되었으니 재료 초기화
        } else {
            System.out.println("일치하는 레시피가 없습니다. 재료를 다시 확인해 주세요.");
        }*/
        Set<String> targetRecipe1 = Set.of("패티", "빵", "양념소스", "양상추", "치즈");
        Set<String> targetRecipe2 = Set.of("치킨", "빵", "머스타드", "양상추", "치즈");
        Set<String> targetRecipe3 = Set.of("새우", "빵", "머스타드", "양상추", "토마토");

        // 입력된 재료와 목표 재료 비교
        if (this.ingredients.size() == targetRecipe1.size() && this.ingredients.containsAll(targetRecipe1)) {
            System.out.println("요리가 완성되었습니다! 레시피: recipe1");
            this.ingredients.clear(); // 요리가 완성되었으니 재료 초기화
            return 1;
        } else if (this.ingredients.size() == targetRecipe2.size() && this.ingredients.containsAll(targetRecipe2)) {
            System.out.println("요리가 완성되었습니다! 레시피: recipe2");
            this.ingredients.clear(); // 요리가 완성되었으니 재료 초기화
            return 2;
        }
        else if (this.ingredients.size() == targetRecipe3.size() && this.ingredients.containsAll(targetRecipe3)) {
            System.out.println("요리가 완성되었습니다! 레시피: recipe3");
            this.ingredients.clear(); // 요리가 완성되었으니 재료 초기화
            return 3;
        }else {
            System.out.println("일치하는 레시피가 없습니다. 재료를 다시 확인해 주세요.");
            return 0;
        }
    }

    public Set<String> getIngredients() {
        return this.ingredients;
    }

    public RandomWordMatch getRandomWordMatch() {
        return wordMatcher;
    }
}