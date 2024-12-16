package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.RandomWordMatch;

public class StartGUI extends JFrame {
    private final CustomerListManager customerListManager;
    private final CustomerGenerator customerGenerator;
    private final RandomWordMatch randomWordMatch;
    private final RecipeCheck recipeCheck;
    private final Customer customer;
    private ImageIcon backgroundImage;

    public StartGUI(CustomerListManager customerListManager, CustomerGenerator customerGenerator, RandomWordMatch randomWordMatch, RecipeCheck recipeCheck, Customer customer) {
        setTitle("Type&Cook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        this.randomWordMatch = randomWordMatch;
        this.recipeCheck = recipeCheck;
        this.customer = customer;

        try {
            backgroundImage = new ImageIcon("src/assets/title.png");  // 이미지 파일 경로 확인
        } catch (Exception e) {
            e.printStackTrace();  // 이미지 로드 실패 시 에러 출력
        }

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // 이미지가 존재하면 배경으로 설정
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 5.0; // 위쪽에 빈 공간을 두어서 버튼을 아래쪽으로 밀어냄
        mainPanel.add(new JLabel(), gbc); // 가상의 컴포넌트 추가 (위쪽 여백)

        gbc.gridy = 1;  // 첫 번째 버튼
        gbc.weighty = 0.0;
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.addActionListener(this::startGame);
        mainPanel.add(startButton, gbc);

        gbc.gridy = 2;
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 20));
        howToPlayButton.setPreferredSize(new Dimension(150, 40));
        //howToPlayButton.addActionListener(e -> showHowToPlay());
        mainPanel.add(howToPlayButton, gbc);

        gbc.gridy = 3;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.addActionListener(e -> System.exit(0));
        mainPanel.add(exitButton, gbc);

        gbc.gridy = 4;  // 마지막에 가상의 공간 추가하여 아래로 밀어냄
        gbc.weighty = 1.0;  // 하단 여백 추가
        mainPanel.add(new JLabel(), gbc);

        add(mainPanel);
    }

    private void startGame(ActionEvent e) {
        getContentPane().removeAll(); // 기존 내용 제거

        GameGUI gamePanel = new GameGUI(this, customerListManager, customerGenerator, randomWordMatch, recipeCheck, customer); // GameGUI 생성
        add(gamePanel); // 새로운 JPanel 추가
        revalidate(); // 레이아웃 갱신
        repaint(); // 화면 갱신
    }
}
