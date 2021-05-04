import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer mainTimer = new Timer(20, (ActionListener) this); //Обновляет экран каждые 20 милисек

    Image img = new ImageIcon("res/Road.jpg").getImage(); // Выделение памяти под изображ дороги

    Player p = new Player();

    Thread enemiesFactory = new Thread(this); //Новый поток

    Thread audioThread = new Thread(new AudioThread());

    java.util.List<Enemy> enemies = new ArrayList<Enemy>(); //Коллекция(список(Array лист)) врагов

    public Road() {                 //  Конструктор здесь все стартуют))
        mainTimer.start();
        enemiesFactory.start();     // Запуск потока с врагами
        //audioThread.start();        // Запуск потока с музыкой

        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {

        while(true) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1400, rand.nextInt(600), rand.nextInt(90), this ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

    public void paint (Graphics graphics){
        graphics = (Graphics2D) graphics;
        graphics.drawImage(img, p.layer1,-130,null);   //Рисование 1го слоя
        graphics.drawImage(img, p.layer2,-130,null);   //Рисование 1го слоя
        graphics.drawImage(p.img, p.x, p.y, null);      //Рисование игрока

        double v = (200/Player.MAX_V) * p.v;
        graphics.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 20);
        graphics.setFont(font);
        graphics.drawString("Скорость: " + v + "км/ч", 890, 690); //Отрисовка скорости


        Iterator<Enemy> i = enemies.iterator(); //Добавление врагов
        while (i.hasNext()){
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -1400){
                i.remove();
            } else {
                e.move();
                graphics.drawImage(e.img, e.x, e.y, null); //отрисовка врагов
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }

    private void testWin() {
        if (p.s >= 900000) {
            JOptionPane.showMessageDialog(null, "Вы выиграли");
            System.exit(0);
        }
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                if (p.y == e.y && p.x > e.x) {
                    p.img = p.img_crash_c;
                    e.img = e.img_crash;
                }
                if (p.y == e.y && p.x < e.x){
                    p.img = p.img_crash_left_c;
                    e.img = e.img_crash;
                }
                if (p.y < e.y && p.x > e.x){
                    p.img = p.img_crash_left_down;
                    e.img = e.img_crash;
                }
                if (p.y > e.y && p.x > e.x){
                    p.img = p.img_crash_left_up;
                    e.img = e.img_crash;
                }
                if (p.y < e.y && p.x < e.x){
                    p.img = p.img_crash_right_down;
                    e.img = e.img_crash;
                }
                if (p.y > e.y && p.x < e.x){
                    p.img = p.img_crash_right_up;
                    e.img = e.img_crash;
                }


                JOptionPane.showMessageDialog(null, "Вы проиграли (Снова)");
                System.exit(1);
            }

        }
    }
}
