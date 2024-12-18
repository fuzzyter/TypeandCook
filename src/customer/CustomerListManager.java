package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerListManager {

    private final List<Customer> customers = new ArrayList<>();
    private final Random random = new Random();

    public void addCustomer(Customer customer) {
        if (customers.size() < 3) { // 손님은 최대 3명
            //RecipeClass recipe = getRandomRecipe();
            customers.add(customer);
        }
    }

    public void removeCustomer(Customer customer){
        //손님 제거 코드
        customers.remove(customer);
    }

    public int getCustomerSize(){
        return customers.size();
    }

    public List<Customer> getCustomers() {
        return customers; // 현재 고객 목록을 반환합니다.
    }
/*
    public static boolean verifyInput(String input) {
        for (Customer customer : customers) {
            if (customer.getRequestedRecipe().matchesIngredient(input)) {
                customers.remove(customer); // 손님 제거
                score += 100;
                game.updateCustomers(customers);
                return true;
            }
        }
        score -= 30;
        return false;
    }*/
}