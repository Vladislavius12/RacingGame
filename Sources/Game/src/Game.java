import javax.swing.*;

public class Game {
    public static void main(String[] args){
        JFrame f = new JFrame("Java Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Выход из окна(фрэйма) по нажатию крестика
        f.setSize(1100, 900); //размеры окна
        f.add(new Road());
        f.setVisible(true); //делаем фрэйм видимым

    }
}