import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioThread implements Runnable{
    @Override
    public void run() {

        try {
            Player p = new Player(new FileInputStream("res/music.mp3"));
            p.play();
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
