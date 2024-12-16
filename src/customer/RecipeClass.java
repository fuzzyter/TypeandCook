package customer;

import java.util.Random;

import java.util.Arrays;


public abstract class RecipeClass {
    protected String name;
    protected String[] ingredients;

    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getIngredientWords() {
        Random random = new Random();
        String[] result = new String[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            result[i] = "Word" + random.nextInt(100);
        }
        return result;
    }

    public boolean matchesIngredient(String input) {
        return Arrays.asList(ingredients).contains(input);
    }
}

class HamburgerRecipe extends RecipeClass {
    public HamburgerRecipe() {
        name = "햄버거";
        ingredients = new String[]{"참깨빵", "고기 패티", "특별한 소스", "양상추", "양파"};
    }
}

class RamenRecipe extends RecipeClass {
    public RamenRecipe() {
        name = "라면";
        ingredients = new String[]{"뜨거운 물", "소스", "면", "계란", "김치"};
    }
}

class TakoyakiRecipe extends RecipeClass {
    public TakoyakiRecipe() {
        name = "타코야끼";
        ingredients = new String[]{"반죽", "문어", "콘옥수수", "소스", "가쓰오부시"};
    }
}
