package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import gui.StartGUI;
import gui.EndingGUI;

public class GameGUI extends JPanel {

    private final JPanel mainPanel = new JPanel();
    private final JTextField inputField = new JTextField();
    private final JLabel backgroundLabel = new JLabel();
    private final JLabel countdownLabel = new JLabel("02:00");
    private final JLabel[] customerLabels = new JLabel[4];
    private final JLabel[] wordLabels = new JLabel[8];
    private final JProgressBar[] timeBars = new JProgressBar[4];
    private Timer countdownTimer;
    private int remainingTime = 120;
    private final JFrame parentFrame; // 부모 프레임 참조

    private final Random random = new Random();
    private final List<String> words = List.of(
            "particular", "investigate", "magnify", "conclusive", "conversely",
            "assure", "entire", "deliberate", "conjunction", "sleek"
    );

    public GameGUI(JFrame parentFrame) {
        this.parentFrame = parentFrame; // 부모 프레임 참조
        setLayout(null); // 전체 레이아웃 설정
        initComponents();
        startCountdownTimer();
    }

    private void moveToEndingScreen() {
        parentFrame.getContentPane().removeAll(); // 현재 내용을 제거
        parentFrame.add(new EndingGUI(parentFrame)); // 엔딩 화면 추가
        parentFrame.revalidate(); // 레이아웃 갱신
        parentFrame.repaint();
    }

    private void initComponents() {
        setLayout(null); // GameGUI 자체에 null 레이아웃 사용
        setBackground(Color.DARK_GRAY); // 배경 색 설정

        // Background Image
        backgroundLabel.setBounds(0, 0, 1280, 720);
        try {
            backgroundLabel.setIcon(new ImageIcon("background.jpg")); // 배경 이미지 파일 경로
        } catch (Exception e) {
            System.out.println("Background image not found. Skipping.");
        }
        add(backgroundLabel);

        // Countdown Timer
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 24));
        countdownLabel.setForeground(Color.WHITE);
        countdownLabel.setBounds(600, 20, 100, 30);
        backgroundLabel.add(countdownLabel);

        // Customer Images and Time Bars
        for (int i = 0; i < 4; i++) {
            customerLabels[i] = new JLabel();
            customerLabels[i].setBounds(50 + i * 300, 200, 100, 100);
            customerLabels[i].setVisible(false);
            try {
                customerLabels[i].setIcon(new ImageIcon("customer" + i + ".jpg")); // 고객 이미지 경로
            } catch (Exception e) {
                System.out.println("Customer image " + i + " not found. Skipping.");
            }
            backgroundLabel.add(customerLabels[i]);

            timeBars[i] = new JProgressBar(0, 100);
            timeBars[i].setBounds(50 + i * 300, 310, 100, 20);
            timeBars[i].setValue(100);
            timeBars[i].setVisible(false);
            backgroundLabel.add(timeBars[i]);
        }

        // Word Labels
        for (int i = 0; i < 8; i++) {
            wordLabels[i] = new JLabel(words.get(random.nextInt(words.size())));
            wordLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            wordLabels[i].setForeground(Color.WHITE);
            wordLabels[i].setBounds(50 + i * 150, 400, 100, 30);
            backgroundLabel.add(wordLabels[i]);
        }

        // Input Field
        inputField.setBounds(50, 650, 1180, 30);
        inputField.addActionListener(e -> checkInput());
        backgroundLabel.add(inputField);

        // Tab Key Listener for Center Image
        mainPanel.addKeyListener(new KeyAdapter() {
            private JLabel centerImage = new JLabel();

            {
                centerImage.setBounds(540, 260, 200, 200);
                try {
                    centerImage.setIcon(new ImageIcon("centerImage.jpg")); // 중앙 이미지 파일 경로
                } catch (Exception e) {
                    System.out.println("Center image not found. Skipping.");
                }
                centerImage.setVisible(false);
                backgroundLabel.add(centerImage);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    centerImage.setVisible(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    centerImage.setVisible(false);
                }
            }
        });
        setVisible(true);
    }


    private void startCountdownTimer() {
        countdownTimer = new Timer(1000, e -> {
            remainingTime--;
            int minutes = remainingTime / 60;
            int seconds = remainingTime % 60;
            countdownLabel.setText(String.format("%02d:%02d", minutes, seconds));

            if (remainingTime <= 0) {
                countdownTimer.stop();
                moveToEndingScreen(); // 엔딩 화면으로 이동
            }
        });
        countdownTimer.start();
    }

    private void checkInput() {
        String input = inputField.getText();
        inputField.setText("");

        // 입력값 확인 로직 추가 가능
        System.out.println("Typed: " + input);
    }

}
