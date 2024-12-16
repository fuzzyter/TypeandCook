package customer;

import java.util.Map;
import java.util.Set;

public class RecipeCheck {
    private final Map<String, RecipeClass> recipes;

    public RecipeCheck(Map<String, RecipeClass> recipes) {
        this.recipes = recipes;
    }

    public String getRecipeByIngredients(Set<String> ingredients) {
        for (Map.Entry<String, RecipeClass> pair : this.recipes.entrySet()) {
            RecipeClass recipeIngredients = pair.getValue();
            if (recipeIngredients.getIngredients().size() == ingredients.size() && ingredients.containsAll(recipeIngredients.getIngredients())) {
                return pair.getKey();
            }
        }
        return null;
    }
}