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
    private final JLabel[] orderLabels = new JLabel[3];
    private final JProgressBar[] timeBars = new JProgressBar[3];
    private final JLabel[] customerImageLabels = new JLabel[3]; //손님 이미지 출력
    private int score = 0;
    private int remainingTime = 120; // 2분 제한 시간
    private List<String> currentWords = new ArrayList<>();
    //private int when = 0;

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


        // 점수 표시
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 35));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(20, 640, 200, 30);
        add(scoreLabel);

        // 타이머 표시
        timerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setBounds(590, 10, 200, 30);
        add(timerLabel);

        // 입력 필드
        inputField.setBounds(480, 650, 300, 40);
        //inputField.addActionListener(this);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //RecipeCheck recipeCheck = new RecipeCheck();
                // Enter 키가 눌렸을 때
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String inputText = inputField.getText().trim();

                    if (inputText.isEmpty()) { // 입력 필드가 비어 있는 경우에만 비교 실행
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
                    else{ //텍스트인풋창이 채워져있는데 엔터칠경우 텍스트를 비교 시작
                        String input = inputField.getText();
                        inputField.setText("");

                        // 입력값 확인 로직 추가 가능
                        System.out.println("Typed: " + input);

                        isWordMatch(input);
                    }

                    //일단 gameGUI에서 따로 배열이든 리스트든 ㅁ만들어서, 선택된 word랑 매칭되는 재료리스트를 거기에 담음
                    //그리고 그 리스트를 recipeCheck에 보내서, null이면 아무것도 안만들어진거고 요리이름 반환받으면 ... 해당요리를
                    //요구하는 손님이 있는지 for문돌려서..??? 손님123을 하나하나 검사하고
                    //손님...있으면... 그 손님 완료처리 없으면... 실패랑똑같이 -30처리...

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
        for (int i = 0; i < 3; i++) {
            customerImageLabels[i] = new JLabel();
            customerImageLabels[i].setBounds(100 + (i * 400), 0, 250, 400);
            add(customerImageLabels[i]);

            timeBars[i] = new JProgressBar(0, 100);
            timeBars[i].setBounds(100 + (i * 400), 310, 100, 20);
            timeBars[i].setValue(100);
            add(timeBars[i]);
        }

        add(background); // background를 다른 컴포넌트들보다 뒤에 추가
    }

    //타이머, 손님이 없을 경우 4초 뒤에 손님 추가
    private void startTimer() {
        Timer timer = new Timer(1000, e -> {
            remainingTime--;
            int minutes = remainingTime / 60;
            int seconds = remainingTime % 60;

            timerLabel.setText(String.format("%02d:%02d", minutes, seconds));

            if (remainingTime <= 0) { //시간이 끝나면 게임 종료
                ((Timer) e.getSource()).stop();
                endGame();
            }

            if (remainingTime % 4 == 0) { // 4초마다 손님 추가
                if (customerListManager.getCustomerSize() < 3) {
                    customerListManager.addCustomer(customerGenerator.genetator());
                    System.out.println("손님 생성: " + customerListManager.getCustomerSize() + "명");
                    updateCustomers(customerListManager.getCustomers());
                }
                System.out.println("현재 손님 수: " + customerListManager.getCustomerSize());
                System.out.println("손님 정보: " + customerListManager.getCustomers());
                System.out.println("손님 요구 레시피: " + customer.getRequestedRecipe());
            }

            /*
            if(customerListManager.getCustomerSize() < 3){
                when = remainingTime -3;

            }
            if(remainingTime == when){
                customerListManager.addCustomer(customerGenerator.genetator());
                updateCustomers(customerListManager.getCustomers()); // 고객 목록 갱신
                when = 0;
            }*/

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
            for (Map.Entry<String, String> entry : randomWordMatch.getPairs()) {
                if (index < wordLabels.length) {
                    wordLabels[index].setText(entry.getKey()/* + " - " + entry.getValue()*/);
                    index++;
                }}
        } catch (Exception e) {
            System.out.println("Error in displayRandomWords: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //점수 출력
    private void updateScoreDisplay() {
        scoreLabel.setText("Score: " + score);
    }

    //손님 이미지
    public void updateCustomers(List<Customer> customers) {
        Random random = new Random();

        // 각 손님에 대해 이미지를 설정
        for (int i = 0; i < customerImageLabels.length; i++) {
            if (i < customers.size()) {
                // 랜덤 손님 이미지 설정 (예시: customer1.png ~ customer6.png)
                int customerImageIndex = random.nextInt(6) + 1; // 1부터 6까지의 숫자
                customerImageLabels[i].setIcon(new ImageIcon("src/assets/customer" + customerImageIndex + ".png"));
                customerImageLabels[i].setVisible(true); // 손님 이미지 라벨을 화면에 보이도록 설정

                timeBars[i].setValue(100);
                timeBars[i].setVisible(true); // 손님마다 타이머도 표시
            } else {
                customerImageLabels[i].setVisible(false); // 손님이 없다면 해당 라벨을 숨김
                timeBars[i].setVisible(false); // 타이머도 숨김
            }

            //랜덤으로 이미지 계속 바뀌는 거 수정하기
        }
    }

    //입력한 단어와 화면에 표시되어 있는 단어 중 일치하는 것이 있는지 검사하고, 있다면 해당 재료를 리스트에 저장
    public void isWordMatch(String input) {
        for (int i = 0; i < 9; i++) {
            if(wordLabels[i].getText().equals(input)){
                System.out.println("단어 일치: " + input);
                wordLabels[i].setText("<html><font color='red'>" + wordLabels[i].getText() + "</font></html>");
                //Player.onkeydown
                return;
            }
            System.out.println("일치하는 단어가 없습니다: " + input);
        }
    }


    private void endGame() {
        JOptionPane.showMessageDialog(parentFrame, "게임 종료! 점수: " + score);
        parentFrame.dispose();
        new StartGUI(customerListManager, customerGenerator, recipeCheck, customer); // 메인 메뉴로 복귀
    }
}