package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerClass {

    private String name;
    private RecipeClass requestedRecipe;

    // 손님 목록을 여기서 관리
    private static List<CustomerClass> customers = new ArrayList<>();
    private static Random random = new Random();

    // 고객이 주문한 요리
    public CustomerClass(RecipeClass requestedRecipe) {
        this.requestedRecipe = requestedRecipe;
    }

    public RecipeClass getRequestedRecipe() {
        return requestedRecipe;
    }

    public boolean isCorrectOrder(RecipeClass providedRecipe) {
        return this.requestedRecipe.isCorrectOrder(providedRecipe);
    }


    Thread customerThread = new Thread(() -> {
        while (getCustomers().size() < 4) {
            //Thread.sleep(4000);  // 4초마다 손님 등장
            try {
                Thread.sleep(4000);  // 4초마다 손님 등장
            } catch (InterruptedException e) {
                e.printStackTrace(); // 예외 발생 시 예외 정보 출력
            }

            if (getCustomers().size() < 4) {
                // 손님을 랜덤으로 생성하여 추가
                addCustomer();
            }
        }
    });

    // 손님을 추가하는 메서드
    public static void addCustomer() {
        if (customers.size() < 4) {

            RecipeClass randomRecipe = getRandomRecipe(); // 요리 랜덤 선택 (햄버거, 라면, 타코야끼 중 하나)

            CustomerClass newCustomer = new CustomerClass(randomRecipe);
            customers.add(newCustomer);
            // 레시피 입력하면 손님 나가는 것 추가로 구현해야 함
        }
    }

    // 손님 목록 반환
    public static List<CustomerClass> getCustomers() {
        return customers;
    }

    // 새 손님 랜덤으로 음식 선택
    private static RecipeClass getRandomRecipe() {

        int index = random.nextInt(3);  // 0: 햄버거, 1: 라면, 2: 타코야끼
        switch (index) {
            case 0:
                return new HamburgerRecipe();
            case 1:
                return new RamenRecipe();
            case 2:
                return new TakoyakiRecipe();
            default:
                return null;
        }
    }
}
