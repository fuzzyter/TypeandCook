package player;

import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Player {
    public static class TextField extends JFrame {
        public TextField(){
            setTitle("TYPE&COOK");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container c = getContentPane();
            c.setLayout(new FlowLayout());

            c.add(new JTextField(20));
            setSize(600,400);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        String customers[]={"신짱구","남도일","에렌 예거","리바이 헤쵸"};
        String foods[][]={
                {"햄버거","참깨빵","고기 패티","특별한 소스","양상추","양파"},
                {"라면","뜨거운 물","소스","면","계란","김치"},
                {"타코야끼","반죽","문어","콘옥수수","소스","가쓰오부시"}};
        String words[][]={
                {"particular","특정한"}, {"investigate","조사하다"},{"magnify","확대하다"},{"conclusive","결정적인"},{"conversely","반대로"},
                {"assure","보장하다"},{"entire","전체의"},{"deliberate","의도적인"},{"conjunction","결합"},{"sleek","매끄러운"},
                {"afford","제공하다"},{"justified","정당한"},{"subdue","정복하다"},{"extant","현존하는"},{"invoke","빌다"}};
        int c=random.nextInt(customers.length);
        int f=random.nextInt(foods.length);

        //메뉴판+랜덤 요리 요구
        System.out.println("Hello welcome to \"TYPE & COOK!\"");
        System.out.println("***************************");
        for(int i=0; i<foods.length; i++)
            System.out.println(foods[i][0]);
        System.out.println("***************************");
        System.out.println(customers[c]+"님이 "+foods[f][0]+"(을)를 주문했습니다!\n START!\n");

        //new TextField();

        System.out.println("<"+foods[f][0]+" 재료 및 레시피>------------");
        for(int i=1; i<foods[f].length; i++)
            System.out.println(foods[f][i]);
        System.out.println("---------------------------");

        int i=0;
        Scanner scanner = new Scanner(System.in);
        while(i < foods[f].length-1) {
            System.out.println(foods[f][i + 1] + "을 재료로 넣자!");
            System.out.println(words[f * 5 + i][1] + words[f * 5 + i][0]);
            String input = scanner.next();
            if (input.equals(words[f * 5 + i][0])) {
                System.out.println("맞음\n");
                i++;
            }
            else
                System.out.println("틀림\n");
        }
        System.out.println("저는 이 채소의 익힘정도를 굉장히 중요시 여기거덩여\n이 "+foods[f][0]+"은 이븐하게 만들어졌네요. 합격입니다\n");
    }
}