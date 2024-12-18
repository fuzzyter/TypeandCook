package customer;

import java.util.Set;


public abstract class RecipeClass {
    protected String name;
    protected Set<String> ingredients;

    public String getName() {
        return name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}

class Recipe1 extends RecipeClass {
    public Recipe1() {
        name = "불고기버거";
        ingredients = Set.of("빵", "양상추", "치즈", "패티", "양념소스");
    }
}

class Recipe2 extends RecipeClass {
    public Recipe2() {
        name = "치킨버거";
        ingredients = Set.of("빵", "양상추", "치즈", "치킨", "머스타드");
    }
}

class Recipe3 extends RecipeClass {
    public Recipe3() {
        name = "새우버거";
        ingredients = Set.of("빵", "양상추", "새우", "토마토", "머스타드");
    }
}
