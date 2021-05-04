import javax.swing.*;
import java.awt.*;

public class Enemy {

    int x;
    int y;
    int v;
    Image img_c = new ImageIcon("res/enemy.png").getImage();
    Image img_crash = new ImageIcon("res/enemy_crash.png").getImage();

    Image img = img_c;

    Road road;

    public Rectangle getRect() {
        return new Rectangle(x, y, 190,90); // Размеры врага
    }

    public Enemy(int x, int y, int v, Road road){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move(){
        x = x - road.p.v + v;
    }

}
