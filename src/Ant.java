import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ant {
    protected Point position;               //current position of ant
    private BufferedImage antImage;

    public Ant(Point start){
        this.position = start;
        try {
            antImage = ImageIO.read(new File("images/ant.png"));
        } catch (IOException ex) {
            System.out.println("Brak ant.png");
        }
    }

    public Point current_position(){        //return currnet position
        return position;
    }
}
