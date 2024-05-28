import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Ant_leader extends Ant{
    

    // randomizer 
    private Random random = new Random();
    private int leader_angle;                                   //angle at whitch leader can change direction
    private Point last_position;                                //previouse leader direction
    private int boardHeight;                                    //board boudaries
    private int boardWidth;
    private int counter;                                        //every 3 move leader goes towards food_source
    
    private ArrayList<Line> line;                      //lista przechowująca linie

    
    public Ant_leader(Point start, int leader_angle_value, int boardHeight, int boardWidth){
        super(start);
        this.leader_angle = leader_angle_value;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.last_position = new Point(start.X_pos(), start.Y_pos());
        counter = 1;

        line = new ArrayList<>();
    }
    
    private boolean angle_check(int x, int y){                      //this function checks if provided vector(x,y) makes smaller angle beetwen last_position vector than leader_angle

        if(last_position.X_pos() == this.position.X_pos() && last_position.Y_pos() == this.position.Y_pos())
            return true;

        

        double a, b, c, d;
        int x1, y1, x2, y2;

        x1 = this.position.X_pos() - last_position.X_pos();
        y1 = this.position.Y_pos() - last_position.Y_pos();
        x2 = x - this.position.X_pos();
        y2 = y - this.position.Y_pos();

        a = x1 * x2 + y1 * y2;
        b = Math.sqrt(x1*x1 + y1*y1);
        c = Math.sqrt(x2*x2 + y2*y2);

        d = a/(b*c);

        d = Math.toDegrees(Math.acos(d));
        
        if(d <= leader_angle || 360-d <= leader_angle)
            return true;
        
            
        return false;
    }

    public boolean simulate(Point end){                                     //simulates ant movement, return true if ant has reached food source
        
        if(this.position  == end)                                           //checks if ant is at food source position
            return true;
        
    
        int x = 0, y = 0;

        float distance = (float)Math.sqrt((this.position.X_pos()-end.X_pos())*(this.position.X_pos()-end.X_pos())+(this.position.Y_pos()-end.Y_pos())*(this.position.Y_pos()-end.Y_pos()));

        if(distance < 100){

            if(distance <= 10){
                this.position.new_coordinates(end.X_pos(), end.Y_pos());
                return true;
            }

            counter++;
            
            if(counter >= 3){
    
                x = end.X_pos();
                y = end.Y_pos();
    
                counter = 0;
            }
        }
        else{
            do{                                                             //if leader has previosly moved, randomly picks x and y untill vector created by it and last lider move have smaller angle in beetwen than leader_angle

                x = random.nextInt(40)+position.X_pos() - 20;
                y = random.nextInt(40)+position.Y_pos() - 20;

                if(x > boardWidth - 10)
                    x = boardWidth - 10;

                if(y > boardHeight - 10)
                    y = boardHeight - 10;

                if(x < 10)
                    x = 10;

                if(y < 10)
                    y = 10;

            }while(!angle_check(x, y));
        }
       
        x -= this.position.X_pos();
        y -= this.position.Y_pos();

        distance = (float)Math.sqrt(x*x+y*y);                               //calculates distance etwen its position and previos ant position

        x = Math.round(x * (5 / distance));                                 //scales x and y movement to move aproximetly 2 tiles
        y = Math.round(y * (5 / distance));   
        
        last_position.new_coordinates(this.position.X_pos(), this.position.Y_pos());

        this.position.change_coordinates(x, y);                             //changes its position

        return false;
    }

    public void draw1(Graphics g){  //metoda do rysowania lini mozna ja pozniej wrzucic w ant
        g.setColor(Color.RED);  
        line.add(new Line(position.X_pos(),position.Y_pos(),last_position.X_pos(),last_position.Y_pos()));
        for(int i = 0; i < line.size(); i++){
            g.drawLine(line.get(i).x1, line.get(i).y1, line.get(i).x2, line.get(i).y2);
        }
        
    }
}