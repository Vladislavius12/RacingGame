import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 100;
    public static final int MAX_TOP = 30;
    public static final int MAX_BOTTOM = 550;


    Image img_c = new ImageIcon("res/right.png").getImage();
    Image img_d = new ImageIcon("res/down.png").getImage();
    Image img_u = new ImageIcon("res/up.png").getImage();
    Image img_crash_c = new ImageIcon("res/right_crash_c.png").getImage();
    Image img_crash_left_c = new ImageIcon("res/crash_left_c.png").getImage();
    Image img_crash_left_down = new ImageIcon("res/crash_left_down.png").getImage();
    Image img_crash_left_up = new ImageIcon("res/crash_left_up.png").getImage();
    Image img_crash_right_down = new ImageIcon("res/crash_right_down.png").getImage();
    Image img_crash_right_up = new ImageIcon("res/crash_right_up.png").getImage();

    Image img = img_c;



    public Rectangle getRect() {
        return new Rectangle(x, y, 190,90); // Размеры игрока для столкновений
    }

    int v = 0; //speed
    int dv = 0; //speedup
    //int mass = 10; // Weight
    int s = 0; //distance
    int x = 30; //user координаты
    int y = 100;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;



    public void move(){
        s += v;
        v += dv;
        if (v <= 0) v = 0;//!!! Не даёт ехать назад
        if (v >= MAX_V) v = MAX_V;
        y -=dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;

        if (layer2 - v <= 0){ //Если координата мегьше 0 то мы длбавляем первую картинку перед второй
            layer1 = 0;
            layer2 = 1400;
        } else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    public void keyPressed(KeyEvent e){ //реакция на клавиши
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            dv = 1;
        }
        if (key == KeyEvent.VK_LEFT){
            dv = -1;
        }
        if (key == KeyEvent.VK_UP){
            dy = 15;
            img = img_u;
        }
        if (key == KeyEvent.VK_DOWN){
            dy = -15;
            img = img_d;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            dy = 0;
            img = img_c;
        }

    }
}
