package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartGUI extends JFrame {

    public StartGUI() {
        setTitle("메인 메뉴");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(this::startGame);
        mainPanel.add(startButton, gbc);

        gbc.gridy++;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(e -> System.exit(0));
        mainPanel.add(exitButton, gbc);

        add(mainPanel);
    }

    private void startGame(ActionEvent e) {
        dispose(); // 현재 창 종료
        JFrame gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setSize(1280, 720);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setContentPane(new GameGUI(gameFrame));
        gameFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartGUI::new);
    }
}
