package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import gui.GameGUI;
import gui.EndingGUI;

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

        // "시작하기" 버튼
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(this::startGame);
        mainPanel.add(startButton, gbc);

        // "나가기" 버튼
        gbc.gridy++;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(e -> System.exit(0));
        mainPanel.add(exitButton, gbc);

        add(mainPanel);
    }

    private void startGame(ActionEvent e) {
        getContentPane().removeAll(); // 기존 내용 제거
        GameGUI gamePanel = new GameGUI(this); // GameGUI 생성
        add(gamePanel); // 새로운 JPanel 추가
        revalidate(); // 레이아웃 갱신
        repaint(); // 화면 갱신
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartGUI());
    }
}
