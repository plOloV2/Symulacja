import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class LastWorker extends Ant_worker{

    public LastWorker(Point start) {
        super(start);
        line = new ArrayList<>();
        last_position = new Point(start.X_pos(), start.Y_pos());
    }

    private ArrayList<Point> line;                      //lista przechowująca linie
    
    public void draw1(Graphics g){  //metoda do rysowania lini mozna ja pozniej wrzucic w ant
        g.setColor(Color.BLUE);  
        line.add(new Point(position.X_pos(),position.Y_pos()));
        for(int i = 1; i < line.size(); i++){
            g.drawLine(line.get(i).X_pos(), line.get(i).Y_pos(), line.get(i-1).X_pos(), line.get(i-1).Y_pos());
        }
        
    }
 
}
