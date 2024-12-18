package gui;

import customer.*;
import player.Player;
import player.RandomWordMatch;

import java.util.*;

import javax.swing.*;

public class gameStart {
    static HashMap<String, RecipeClass> map = new HashMap<String, RecipeClass>();
    static List<String> ingredients = List.of("빵", "양상추", "토마토", "양념소스", "치킨", "치즈", "패티", "새우", "머스타드");
    static List<String> words = List.of("bread","lettuce","tomato","sauce","chicken","cheese",
            "patty","shrimp","mustard"/*,"sleek","afford","justified","subdue","extant","invoke"*/);


    public static CustomerListManager customerListManager = new CustomerListManager();
    private static CustomerGenerator customerGenerator = new CustomerGenerator();
    private static RandomWordMatch randomWordMatch = new RandomWordMatch(ingredients, words);
    private static RecipeCheck recipeCheck = new RecipeCheck(map);
    private static Customer customer = customerGenerator.genetator();
    private static Player player = new Player(randomWordMatch, recipeCheck);

    public gameStart(){
        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        //this.randomWordMatch = WordManager.getRandomWordMatch();
        this.recipeCheck = recipeCheck;
        this.customer = customer;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartGUI(customerListManager, customerGenerator, recipeCheck, customer, player));
    }
}