package gui;
import javax.swing.*;
import java.awt.*;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import gui.StartGUI;
import gui.GameGUI;
import player.RandomWordMatch;
import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.RandomWordMatch;

public class EndingGUI extends JPanel {
    public static CustomerListManager customerListManager = new CustomerListManager();
    private static CustomerGenerator customerGenerator;
    private static RandomWordMatch randomWordMatch;
    private static RecipeCheck recipeCheck;
    private static Customer customer;

    public EndingGUI(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        JLabel messageLabel = new JLabel("게임 종료!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 40));
        messageLabel.setForeground(Color.WHITE);
        add(messageLabel, BorderLayout.CENTER);

        JButton backToMenuButton = new JButton("메인 메뉴로");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 20));
        backToMenuButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll(); // 현재 내용 제거
            parentFrame.setContentPane(new StartGUI(customerListManager, customerGenerator, randomWordMatch, recipeCheck, customer).getContentPane()); // 메인 메뉴로 이동
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(backToMenuButton, BorderLayout.SOUTH);
    }
}


