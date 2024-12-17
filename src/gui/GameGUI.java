package gui;

import customer.Customer;
import customer.CustomerGenerator;
import customer.CustomerListManager;
import customer.RecipeCheck;
import player.Player;
import player.RandomWordMatch;

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
    private ImageIcon recipeImage;
    private boolean isTabPressed = false;  // Tab 키가 눌렸는지 확인하는 변수

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
    private final Player player;


    private final JFrame parentFrame;

    public GameGUI(JFrame parentFrame, CustomerListManager customerListManager, CustomerGenerator customerGenerator, RecipeCheck recipeCheck, Customer customer, Player player) {
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

        this.randomWordMatch = player.getRandomWordMatch();

        displayRandomWords(); // 초기 단어 출력

        System.out.println("After displayRandomWords");

        this.customerListManager = customerListManager;
        this.customerGenerator = customerGenerator;

        //this.randomWordMatch = new RandomWordMatch(ingredients, words);
        //this.randomWordMatch = WordManager.getRandomWordMatch();


        //System.out.println(ingredients);
        this.recipeCheck = recipeCheck;
        this.customer = customer;
        this.player = player;

        randomWordMatch.shuffle();

    }



    private void initComponents() {
        BGImage = new ImageIcon("src/assets/background.png"); //배경 이미지 출력
        recipeImage = new ImageIcon("src/assets/recipe.png");
        CustomGlassPane glassPane = new CustomGlassPane("src/assets/Recipe.png");
        parentFrame.setGlassPane(glassPane);
        glassPane.setVisible(false); // 초기에는 숨김

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
/*
// Tab 키 눌렀을 때 동작
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("TAB"), "tabPressed");
        getActionMap().put("tabPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTabPressed = true; // Tab 키가 눌리면 true 설정
                repaint();
            }
        });

// Tab 키 뗐을 때 동작
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released TAB"), "tabReleased");
        getActionMap().put("tabReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isTabPressed = false; // Tab 키를 떼면 false 설정
                repaint();
            }
        });*/

        this.setFocusable(true); // GameGUI 패널에 포커스 설정
        this.requestFocusInWindow(); // 키 입력 감지를 위해 포커스 요청


        // 입력 필드
        inputField.setBounds(480, 650, 300, 40);
        //inputField.addActionListener(this);
        inputField.setFocusTraversalKeysEnabled(false);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String inputText = inputField.getText().trim();

                    if (inputText.isEmpty()) { // 입력 필드가 비어 있는 경우에만 비교 실행
                        // 사용자가 입력한 재료를 콤마(,)로 분리하여 Set으로 변환
                        /*
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
                        }*/

                        int result = player.onKeyDown2();
                        if(result == 1){
                            score += 100;
                            System.out.println("불고기버거 조리 완료. 점수가 +100 되었습니다. 현재 점수: " + score);
                            /*add(orderLabels[1]);
                            orderLabels[1].setBounds(500, 350, 150, 150);*/
                        }
                        else if(result == 2){
                            score += 100;
                            System.out.println("치킨버거 조리 완료. 점수가 +100 되었습니다. 현재 점수: " + score);
                        }
                        else if(result == 3){
                            score += 100;
                            System.out.println("새우버거 조리 완료. 점수가 +100 되었습니다. 현재 점수: " + score);
                        }
                        else{
                            score -= 30; // 오답
                            System.out.println("조리 실패. 점수가 -30 되었습니다. 현재 점수: " + score);
                        }

                        // 점수 디스플레이 업데이트
                        updateScoreDisplay();

                        // 입력창 초기화
                        inputField.setText("");

                        for (int i = 0; i < 9; i++) {
                            wordLabels[i].setForeground(Color.WHITE);
                        }

                    }
                    else{ //텍스트인풋창이 채워져있는데 엔터칠경우 텍스트를 비교 시작
                        String input = inputField.getText();
                        inputField.setText("");

                        // 입력값 확인 로직 추가 가능
                        System.out.println("Typed: " + input);

                        isWordMatch(input);
                    }



                    //일단 gameGUI에서 따로 배열이든 리스트든 ㅁ만들어서, 선택된 word랑 매칭되는 재료리스트를 거기에 담음(onkeydown이 수행)
                    //그리고 그 리스트를 recipeCheck에 보내서(onkeydown2호출해서 수행), null이면 아무것도 안만들어진거고 요리이름 반환받으면 ... 해당요리를
                    //요구하는 손님이 있는지 for문돌려서..??? 손님123을 하나하나 검사하고
                    //손님...있으면... 그 손님 완료처리 없으면... 실패랑똑같이 -30처리...

                }
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_TAB) {
                    /*isTabPressed = true;
                    repaint();*/
                    glassPane.setShowImage(true); // 이미지 보이기
                    glassPane.setVisible(true);
                    return true; // Tab 키 이벤트 소비
                }
                if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_TAB) {
                    /*isTabPressed = false;
                    repaint();*/
                    glassPane.setShowImage(false); // 이미지 숨기기
                    glassPane.setVisible(false);
                    return true; // Tab 키 이벤트 소비
                }
                return false;
            }


        });
        this.setFocusable(true);
        add(inputField);

        // 단어 출력 라벨 초기화
        for (int i = 0; i < 9; i++) {
            wordLabels[i] = new JLabel();
            wordLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            wordLabels[i].setForeground(Color.WHITE);
            wordLabels[i].setOpaque(true);
            //wordLabels[i].setBackground(new Color(0, 0, 0, 70)); //검은색, 투명도 70의 배경
            wordLabels[i].setBackground(Color.BLACK);
            if(i<4){
                wordLabels[i].setBounds(200 + (i * 260), 400, 120, 30);
            }
            else{
                wordLabels[i].setBounds(130 + ((i-4) * 240), 530, 120, 30);
            }
            //wordLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            add(wordLabels[i]);
        }

        for(int i = 0 ; i < 3 ; i ++){
            orderLabels[i] = new JLabel();
            orderLabels[i].setBounds(20 + (i * 400), 230, 150, 150);
            add(orderLabels[i]);
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
    class CustomGlassPane extends JComponent {
        private boolean showImage = false;
        private Image image;

        public CustomGlassPane(String imagePath) {
            this.image = new ImageIcon("src/assets/Recipe.png").getImage();
        }

        public void setShowImage(boolean showImage) {
            this.showImage = showImage;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (showImage) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
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
            List<String> words = randomWordMatch.getWords();
            randomWordMatch.getPairs(); // Shuffle the words
            int index = 0;
            System.out.println("Random words are being displayed...");
            for (Map.Entry<String, String> entry : randomWordMatch.getPairs()) {
                if (index < wordLabels.length) {
                    wordLabels[index].setText(words.get(index)/*entry.getKey() + " - " + entry.getValue()*/);
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
                orderLabels[i].setIcon(new ImageIcon("src/assets/order" + (i+1) + ".png"));
                orderLabels[i].setVisible(true);/*
                if(customer.getRequestedRecipe().getName().equals("불고기버거")){
                    orderLabels[i].setIcon(new ImageIcon("src/assets/order1.png"));
                    orderLabels[i].setVisible(true);
                }
                else if (customer.getRequestedRecipe().getName().equals("치킨버거")) {
                    orderLabels[i].setIcon(new ImageIcon("src/assets/order2.png"));
                    orderLabels[i].setVisible(true);
                }
                else{
                    orderLabels[i].setIcon(new ImageIcon("src/assets/order3.png"));
                    orderLabels[i].setVisible(true);
                }*/
                timeBars[i].setValue(100);
                timeBars[i].setVisible(true); // 손님마다 타이머도 표시


            } else {
                customerImageLabels[i].setVisible(false); // 손님이 없다면 해당 라벨을 숨김
                timeBars[i].setVisible(false); // 타이머도 숨김
                orderLabels[i].setVisible(false);
            }

            //랜덤으로 이미지 계속 바뀌는 거 수정하기
        }
    }

    //입력한 단어와 화면에 표시되어 있는 단어 중 일치하는 것이 있는지 검사하고, 있다면 해당 재료를 리스트에 저장
    public void isWordMatch(String input) {
        for (int i = 0; i < 9; i++) {
            if(wordLabels[i].getText().equals(input)){
                System.out.println("단어 일치: " + input);
                player.onKeyDown(wordLabels[i].getText());
                wordLabels[i].setForeground(Color.RED);

                return;
            }
            System.out.println("일치하는 단어가 없습니다: " + input);
        }
    }


    private void endGame() {
        JOptionPane.showMessageDialog(parentFrame, "게임 종료! 점수: " + score);
        parentFrame.dispose();
        new StartGUI(customerListManager, customerGenerator, recipeCheck, customer, player); // 메인 메뉴로 복귀
    }
}