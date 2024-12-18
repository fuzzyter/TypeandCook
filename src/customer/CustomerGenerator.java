package customer;

import gui.GameGUI;
import java.util.Random;

public class CustomerGenerator {
    private static GameGUI game;
    public static int score = 0;
    Random random = new Random();

    CustomerListManager customerListManager = new CustomerListManager();

    public CustomerGenerator() {
        // 기본 생성자 (필요에 따라 초기화 로직 추가)
    }

    public Customer genetator(){
        Customer customer = new Customer(getRandomRecipe());
        return customer;
    }

    private RecipeClass getRandomRecipe() {
        switch (random.nextInt(3)) {
            case 0:
                return new Recipe1();
            case 1:
                return new Recipe2();
            case 2:
                return new Recipe3();
            default:
                throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        }
    }

}
