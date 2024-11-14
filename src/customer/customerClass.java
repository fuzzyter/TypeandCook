package customer;

public class CustomerClass {

    private String name;
    private RecipeClass requestedRecipe;
    private long requestTime;

    // 생성자

    public CustomerClass(String name, RecipeClass requestedRecipe) {
        this.name = name;
        this.requestedRecipe = requestedRecipe;
        this.requestTime = System.currentTimeMillis(); // 고객이 등장한 시간

    }

    // 요청한 요리 반환
    public RecipeClass getRequestedRecipe() {
        return requestedRecipe;
    }

    // 고객의 이름 반환
    public String getName() {
        return name;
    }

    // 고객이 요청한 요리 확인
    public boolean isCorrectOrder(RecipeClass providedRecipe) {
        return this.requestedRecipe.equals(providedRecipe);
    }


}
