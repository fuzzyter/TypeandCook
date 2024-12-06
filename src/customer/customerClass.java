package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerClass {

    private RecipeClass requestedRecipe;
    private static List<CustomerClass> customers = new ArrayList<>();
    private static Random random = new Random();
    private static int score = 0; // 플레이어 점수
    private static boolean gameRunning = true;

    public CustomerClass(RecipeClass requestedRecipe) {
        this.requestedRecipe = requestedRecipe;
    }

    public RecipeClass getRequestedRecipe() {
        return requestedRecipe;
    }

    // 점수 반환
    public static int getScore() {
        return score;
    }

    // 손님 목록 반환
    public static List<CustomerClass> getCustomers() {
        return customers;
    }

    // 랜덤 레시피 생성
    private static RecipeClass getRandomRecipe() {
        switch (random.nextInt(3)) { // 0: 햄버거, 1: 라면, 2: 타코야끼
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

    // 손님 추가
    public static void addCustomer() {
        if (customers.size() < 4) {
            RecipeClass randomRecipe = getRandomRecipe();
            CustomerClass newCustomer = new CustomerClass(randomRecipe);
            customers.add(newCustomer);
            System.out.println("새 손님이 입장했습니다! 요청한 음식: " + newCustomer.getRequestedRecipe().getName());
        }
    }

    // 게임 시작
    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        // 손님 등장 쓰레드
        Thread customerThread = new Thread(() -> {
            while (gameRunning) {
                try {
                    Thread.sleep(4000); // 4초마다 손님 등장
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (customers.size() < 4) {
                    addCustomer();
                }
            }
        });
        customerThread.start();

        //
        while (gameRunning) {
            if (!customers.isEmpty()) {
                CustomerClass currentCustomer = customers.get(0);
                System.out.println("손님 요청: " + currentCustomer.getRequestedRecipe().getName());
                String[] randomWords = currentCustomer.getRequestedRecipe().getIngredientWords();
                System.out.println("랜덤 단어를 입력하세요: " + String.join(", ", randomWords));

                long startTime = System.currentTimeMillis();
                boolean success = true;

                // 랜덤 단어와 플레이어의 입력값 확인
                String[] Inputs = {"example1", "example2", "example3", "example4", "example5"};

                for (int i = 0; i < randomWords.length; i++) {
                    String input = Inputs[i]; // 하드코딩된 입력값을 순서대로 가져옴
                    System.out.println("입력: " + input);

                    if (!input.equalsIgnoreCase(randomWords[i])) {
                        System.out.println("틀렸습니다! 손님이 화났습니다.");
                        success = false;
                        break;
                    }

                    if ((System.currentTimeMillis() - startTime) > 10000) { // 10초 초과 확인
                        System.out.println("시간 초과! 손님이 떠났습니다.");
                        success = false;
                        break;
                    }
                }
                }

                // 결과 처리
                if (success) {
                    System.out.println("모든 단어를 맞췄습니다! +100점!");
                    score += 100;
                } else {
                    System.out.println("-30점이 부과됩니다.");
                    score -= 30;
                }

                customers.remove(0); // 손님 퇴장
                System.out.println("현재 점수: " + score);
            }

        }

    }
}
