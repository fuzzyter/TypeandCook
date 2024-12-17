package gui;

import customer.*;
import player.Player;
import player.RandomWordMatch;
import player.WordManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

public class gameStart {
    //public static RecipeClass recipeClass = new recipeClass();
    static HashMap<String, RecipeClass> map = new HashMap<String, RecipeClass>();

    public static CustomerListManager customerListManager = new CustomerListManager();
    private static CustomerGenerator customerGenerator = new CustomerGenerator();
    private static RandomWordMatch randomWordMatch;
    private static RecipeCheck recipeCheck = new RecipeCheck(map);
    private static Customer customer = customerGenerator.genetator();
    //private static Player player;

    public gameStart(){
        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        //this.randomWordMatch = WordManager.getRandomWordMatch();
        this.recipeCheck = recipeCheck;
        this.customer = customer;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartGUI(customerListManager, customerGenerator, recipeCheck, customer));
    }
}