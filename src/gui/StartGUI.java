package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.Player;

public class StartGUI extends JFrame {
    private final CustomerListManager customerListManager;
    private final CustomerGenerator customerGenerator;
    //private final RandomWordMatch randomWordMatch;
    private final RecipeCheck recipeCheck;
    private final Customer customer;
    private final Player player;
    private ImageIcon backgroundImage;

    public StartGUI(CustomerListManager customerListManager, CustomerGenerator customerGenerator, RecipeCheck recipeCheck, Customer customer, Player player) {
        setTitle("Type&Cook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        //this.randomWordMatch = WordManager.getRandomWordMatch();
        this.recipeCheck = recipeCheck;
        this.customer = customer;
        this.player = player;

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
        howToPlayButton.addActionListener(e -> showHowToPlay());
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
        // ESC 키 이벤트 리스너 추가
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    hideHelpImage();  // ESC 눌렀을 때 이미지 숨기기
                }
            }
        });
        this.setFocusable(true);  // 키 이벤트를 받기 위해 JFrame에 포커스를 주어야 함
    }

    //HowtoPlay버튼을 눌렀을 때
    private void showHowToPlay() {
        // 기존에 이미지를 띄운 패널이 있다면 새로 띄우지 않도록 제거
        hideHelpImage();

        // Help 이미지를 표시할 새로운 JLabel 생성
        JLabel helpImageLabel = new JLabel();
        ImageIcon helpImage = new ImageIcon("src/assets/help.png");
        helpImageLabel.setIcon(helpImage);

        // Help 이미지를 화면에 표시
        //helpImageLabel.setBounds(0, 0, getWidth(), getHeight());  // 화면 크기 전체에 표시
        add(helpImageLabel);

        // 이미지를 닫는 동작을 위해 저장
        setHelpImageLabel(helpImageLabel);
        revalidate();
        repaint();
    }

    // "Help" 이미지를 숨기기
    private JLabel helpImageLabel = null;

    private void setHelpImageLabel(JLabel label) {
        this.helpImageLabel = label;
    }

    private void hideHelpImage() {
        if (helpImageLabel != null) {
            remove(helpImageLabel);  // 화면에서 이미지 제거
            helpImageLabel = null;
            revalidate();
            repaint();
        }}

    private void startGame(ActionEvent e) {
        getContentPane().removeAll(); // 기존 내용 제거

        GameGUI gamePanel = new GameGUI(this, customerListManager, customerGenerator, recipeCheck, customer, player); // GameGUI 생성
        add(gamePanel); // 새로운 JPanel 추가
        revalidate(); // 레이아웃 갱신
        repaint(); // 화면 갱신
    }
}
