package customer;

import gui.GameGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerClass {

    private static final List<CustomerClass> customers = new ArrayList<>();
    private static final Random random = new Random();
    private static int score = 0;
    private static GameGUI game;

    private final RecipeClass requestedRecipe; // 손님이 요청한 레시피

    public CustomerClass(RecipeClass requestedRecipe) {
        this.requestedRecipe = requestedRecipe;
    }

    public RecipeClass getRequestedRecipe() {
        return requestedRecipe;
    }

    public static void startGame(GameGUI gameGUI) {
        game = gameGUI;

        // 손님 추가 스레드 시작
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(4000); // 4초마다 손님 추가
                    addCustomer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void addCustomer() {
        if (customers.size() < 3) { // 손님은 최대 3명
            RecipeClass recipe = getRandomRecipe();
            customers.add(new CustomerClass(recipe));
            game.updateCustomers(customers); // GUI에 업데이트
        }
    }

    private static RecipeClass getRandomRecipe() {
        switch (random.nextInt(3)) {
            case 0:
                return new HamburgerRecipe();
            case 1:
                return new RamenRecipe();
            case 2:
                return new TakoyakiRecipe();
            default:
                throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        }
    }

    public static boolean verifyInput(String input) {
        for (CustomerClass customer : customers) {
            if (customer.getRequestedRecipe().matchesIngredient(input)) {
                customers.remove(customer); // 손님 제거
                score += 100;
                game.updateCustomers(customers);
                return true;
            }
        }
        score -= 30;
        return false;
    }
}
