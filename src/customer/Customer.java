package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer {

    private static final List<Customer> customers = new ArrayList<>();
    private static final Random random = new Random();

    private final RecipeClass requestedRecipe; // 손님이 요청한 레시피

    public Customer(RecipeClass requestedRecipe) {
        this.requestedRecipe = requestedRecipe;
    }

    public RecipeClass getRequestedRecipe() {
        return requestedRecipe;
    }

}
