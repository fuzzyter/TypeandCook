package gui;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.Player;
import player.RandomWordMatch;
import player.WordManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


public class GameGUI extends JPanel {

    private ImageIcon BGImage;

    private final JLabel scoreLabel = new JLabel("Score: 0");
    private final JLabel timerLabel = new JLabel("Time: 02:00");
    private final JTextField inputField = new JTextField();
    private final JLabel[] wordLabels = new JLabel[9]; //단어 출력 라벨
    private final JLabel[] customerLabels = new JLabel[3];
    private final JLabel[] orderLabels = new JLabel[3];
    private final JProgressBar[] timeBars = new JProgressBar[3];
    private final JLabel[] customerImageLabels = new JLabel[3]; //손님 이미지 출력
    private int score = 0;
    private int remainingTime = 120; // 2분 제한 시간
    private List<String> currentWords = new ArrayList<>();
    private int when = 0;

    private final CustomerListManager customerListManager;
    private final CustomerGenerator customerGenerator;
    private final RandomWordMatch randomWordMatch;
    private final RecipeCheck recipeCheck;
    private final Customer customer;


    private final JFrame parentFrame;


    private static final List<String> ingredients = List.of("빵", "양상추", "토마토", "양념소스", "치킨", "치즈", "패티", "새우", "머스타드");
    private static final List<String> words = List.of("particular","investigate","magnify","conclusive","conversely","assure",
            "entire","deliberate","conjunction","sleek","afford","justified","subdue","extant","invoke");



    public GameGUI(JFrame parentFrame, CustomerListManager customerListManager, CustomerGenerator customerGenerator, RecipeCheck recipeCheck, Customer customer) {
        System.out.println("GameGUI constructor start");

        this.parentFrame = parentFrame;
        setLayout(null);
        initComponents();
        startTimer(); // 제한 시간 타이머 시작

/*
        System.out.println("Before displayRandomWords");
        if (randomWordMatch == null) {
            System.out.println("randomWordMatch is null!");
        } else {
            System.out.println("randomWordMatch initialized!");
        }*/

        this.randomWordMatch = WordManager.getRandomWordMatch();

        displayRandomWords(); // 초기 단어 출력

        System.out.println("After displayRandomWords");

        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;
        //this.randomWordMatch = new RandomWordMatch(ingredients, words);
        //this.randomWordMatch = WordManager.getRandomWordMatch();


        System.out.println(ingredients);
        this.recipeCheck = recipeCheck;
        this.customer = customer;


    }

    private void initComponents() {
        BGImage = new ImageIcon("src/assets/background.png"); //배경 이미지 출력

        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(BGImage.getImage(), 0, 0, this);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        background.setBounds(0, 0, 1280, 720); // 배경 크기 설정
        background.setOpaque(true); // 배경을 불투명하게 설정 (이미지 표시 위해)

        setLayout(null); // 배치 관리자 제거
        add(background, Integer.valueOf(0)); // background를 다른 컴포넌트들보다 뒤에 추가

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
        inputField.setBounds(50, 650, 500, 30);
        //inputField.addActionListener(this);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //RecipeCheck recipeCheck = new RecipeCheck();
                // Enter 키가 눌렸을 때
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String inputText = inputField.getText().trim();

                    // 입력 필드가 비어 있는 경우에만 비교 실행
                    if (inputText.isEmpty()) {
                        // 사용자가 입력한 재료를 콤마(,)로 분리하여 Set으로 변환
                        Set<String> userIngredients = Set.of(inputText.split("\\s*,\\s*"));

                        // 레시피와 비교
                        String matchedRecipe = recipeCheck.getRecipeByIngredients(userIngredients);
                        if (matchedRecipe != null) {
                            // 레시피와 일치하는 경우
                            score += 100; // 정답
                            System.out.println("조리 완료. 점수가 +100 되었습니다. 현재 점수: " + score);
                        } else {
                            // 레시피와 일치하지 않는 경우
                            score -= 30; // 오답
                            System.out.println("조리 실패. 점수가 -30 되었습니다. 현재 점수: " + score);
                        }

                        // 점수 디스플레이 업데이트
                        updateScoreDisplay();

                        // 입력창 초기화
                        inputField.setText("");
                    }
                }
            }
        });
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
        for (int i = 0; i < 3; i++) {/*
            customerLabels[i] = new JLabel("손님 " + (i + 1));
            customerLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            customerLabels[i].setForeground(Color.WHITE);
            customerLabels[i].setBounds(100 + (i * 400), 200, 100, 100);
            add(customerLabels[i]);*/

            timeBars[i] = new JProgressBar(0, 100);
            timeBars[i].setBounds(100 + (i * 400), 310, 100, 20);
            timeBars[i].setValue(100);
            add(timeBars[i]);
        }
    }

    //타이머, 손님이 없을 경우 4초 뒤에 손님 추가
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
            if(customerListManager.getCustomerSize() < 3){
                when = remainingTime -3;

            }
            if(remainingTime == when){
                customerListManager.addCustomer(customerGenerator.genetator());
                when = 0;
            }

        });
        timer.start();
    }

//랜덤 단어 출력
    private void displayRandomWords() {
        /*
        randomWordMatch.getPairs(); // Shuffle the words
        int index = 0;
        for (Map.Entry<String, String> entry : randomWordMatch.getPairs()) {
            if (index < wordLabels.length) {
                wordLabels[index].setText(entry.getKey() + " - " + entry.getValue());
                index++;
            }
        }*/
        if (this.randomWordMatch == null) {
            System.out.println("randomWordMatch is null! Cannot display words.");
        }
        try {
            randomWordMatch.getPairs(); // Shuffle the words
            int index = 0;
            System.out.println("Random words are being displayed...");
            // 예를 들어, randomWordMatch.getPairs()가 사용된다면 그 전에 예외가 발생할 수 있음
            for (Map.Entry<String, String> entry : randomWordMatch.getPairs()) {
                if (index < wordLabels.length) {
                    wordLabels[index].setText(entry.getKey() + " - " + entry.getValue());
                    index++;
                }}
        } catch (Exception e) {
            System.out.println("Error in displayRandomWords: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + score);
    }

    public void updateCustomers(List<Customer> customers) {
        Random random = new Random();
        for (int i = 0; i < customerLabels.length; i++) {
            if (i < customers.size()) {

                // 랜덤 손님 이미지 설정
                int customerImageIndex = random.nextInt(6) + 1; // customer1.png ~ customer6.png
                customerImageLabels[i].setIcon(new ImageIcon("customer" + customerImageIndex + ".png"));
                customerImageLabels[i].setVisible(true);

                timeBars[i].setVisible(true);
            } else {
                timeBars[i].setVisible(false);
            }
        }
    }

    private void endGame() {
        JOptionPane.showMessageDialog(parentFrame, "게임 종료! 점수: " + score);
        parentFrame.dispose();
        new StartGUI(customerListManager, customerGenerator, recipeCheck, customer); // 메인 메뉴로 복귀
    }
}
