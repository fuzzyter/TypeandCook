package gui;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.RandomWordMatch;

import javax.swing.*;

public class gameStart {
    public static CustomerListManager customerListManager = new CustomerListManager();
    private static CustomerGenerator customerGenerator;
    private static RandomWordMatch randomWordMatch;
    private static RecipeCheck recipeCheck;
    private static Customer customer;

    public gameStart(){
        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        this.randomWordMatch = randomWordMatch;
        this.recipeCheck = recipeCheck;
        this.customer = customer;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartGUI(customerListManager, customerGenerator, randomWordMatch, recipeCheck, customer));
    }
}
