import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ant {
    class Line{     //klasa do przechowywania obiektu linia
        int x1, y1, x2, y2; //koordynaty oby punktów
        public Line(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    protected Point position;               //current position of ant
    private BufferedImage antImage;

    public Ant(Point start){
        this.position = new Point(start.X_pos(), start.Y_pos());
        try {
            antImage = ImageIO.read(new File("images/ant.png"));
        } catch (IOException ex) {
            System.out.println("Brak ant.png");
        }
    }

    public Point current_position(){        //return currnet position
        return position;
    }

    public void draw(Graphics g){
            g.drawImage(antImage, position.X_pos()-16, position.Y_pos()-16, null);
    }

}
