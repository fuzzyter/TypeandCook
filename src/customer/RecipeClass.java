package customer;
import java.util.Random;
// 추상 클래스: 레시피
public abstract class RecipeClass {
    protected String name;
    protected String[] ingredients;

    // 음식 이름과 재료 반환
    public String getName() {
        return name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getIngredientWords() { // 레시피의 재료당 랜덤 단어 배치하여 배열로 반환

        String[] randomWords = {
                "말", "획득하다", "싸우다 ", "찾다", "기다리다", "기억하다",
                "지다", "달리다", "떠나다", "꿈", "이상"
        };

        Random random = new Random();
        String[] result = new String[ingredients.length];

        for (int i = 0; i < ingredients.length; i++) {
            result[i] = randomWords[random.nextInt(randomWords.length)];
        }

        return result;
    }
}

// 햄버거 레시피 클래스 (RecipeClass 상속)
public class HamburgerRecipe extends RecipeClass {
    public HamburgerRecipe() {
        this.name = "햄버거";
        this.ingredients = new String[]{"참깨빵", "고기 패티", "특별한 소스", "양상추", "양파"};
    }
}

// 라면 레시피 클래스 (RecipeClass 상속)
public class RamenRecipe extends RecipeClass {
    public RamenRecipe() {
        this.name = "라면";
        this.ingredients = new String[]{"뜨거운 물", "소스", "면", "계란", "김치"};
    }
}

// 타코야끼 레시피 클래스 (RecipeClass 상속)
public class TakoyakiRecipe extends RecipeClass {
    public TakoyakiRecipe() {
        this.name = "타코야끼";
        this.ingredients = new String[]{"반죽", "문어", "콘옥수수", "소스", "가쓰오부시"};
    }
}
