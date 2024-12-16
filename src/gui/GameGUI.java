package gui;

import customer.CustomerClass;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends JPanel {

    private final JLabel scoreLabel = new JLabel("Score: 0");
    private final JLabel timerLabel = new JLabel("Time: 02:00");
    private final JTextField inputField = new JTextField();
    private final JLabel[] wordLabels = new JLabel[9];
    private final JLabel[] customerLabels = new JLabel[3];
    private final JProgressBar[] timeBars = new JProgressBar[3];
    private int score = 0;
    private int remainingTime = 120; // 2분 제한 시간
    private List<String> currentWords = new ArrayList<>();

    private final JFrame parentFrame;

    public GameGUI(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        initComponents();
        CustomerClass.startGame(this); // 손님 관련 로직 시작
        startTimer(); // 제한 시간 타이머 시작
        updateRandomWords(); // 초기 단어 출력
    }

    private void initComponents() {
        setBackground(Color.DARK_GRAY);

        // 점수 표시
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 20, 200, 30);
        add(scoreLabel);

        // 타이머 표시
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBounds(1080, 20, 200, 30);
        add(timerLabel);

        // 입력 필드
        inputField.setBounds(50, 650, 1180, 30);
        inputField.addActionListener(this::processInput);
        add(inputField);

        // 단어 출력 라벨 초기화
        for (int i = 0; i < 9; i++) {
            wordLabels[i] = new JLabel();
            wordLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            wordLabels[i].setForeground(Color.WHITE);
            wordLabels[i].setBounds(50 + (i * 120), 400, 100, 30);
            add(wordLabels[i]);
        }

        // 손님 표시 라벨 초기화
        for (int i = 0; i < 3; i++) {
            customerLabels[i] = new JLabel("손님 " + (i + 1));
            customerLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            customerLabels[i].setForeground(Color.WHITE);
            customerLabels[i].setBounds(100 + (i * 400), 200, 100, 100);
            add(customerLabels[i]);

            timeBars[i] = new JProgressBar(0, 100);
            timeBars[i].setBounds(100 + (i * 400), 310, 100, 20);
            timeBars[i].setValue(100);
            add(timeBars[i]);
        }
    }

    private void startTimer() {
        Timer timer = new Timer(1000, e -> {
            remainingTime--;
            int minutes = remainingTime / 60;
            int seconds = remainingTime % 60;
            timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));

            if (remainingTime <= 0) {
                ((Timer) e.getSource()).stop();
                endGame();
            }
        });
        timer.start();
    }

    private void updateRandomWords() {
        currentWords = Player.generateRandomWords();
        for (int i = 0; i < wordLabels.length; i++) {
            if (i < currentWords.size()) {
                wordLabels[i].setText(currentWords.get(i));
                wordLabels[i].setVisible(true);
            } else {
                wordLabels[i].setVisible(false);
            }
        }
    }

    private void processInput(ActionEvent e) {
        String input = inputField.getText().trim();
        inputField.setText("");

        boolean match = CustomerClass.verifyInput(input);
        if (match) {
            score += 100;
        } else {
            score -= 30;
        }
        updateScoreDisplay();
    }

    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + score);
    }

    public void updateCustomers(List<CustomerClass> customers) {
        for (int i = 0; i < customerLabels.length; i++) {
            if (i < customers.size()) {
                customerLabels[i].setText(customers.get(i).getRequestedRecipe().getName());
                customerLabels[i].setVisible(true);
                timeBars[i].setVisible(true);
            } else {
                customerLabels[i].setVisible(false);
                timeBars[i].setVisible(false);
            }
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(parentFrame, "게임 종료! 점수: " + score);
        parentFrame.dispose();
        new StartGUI(); // 메인 메뉴로 복귀
    }
}
