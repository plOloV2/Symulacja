import java.awt.Color;
import java.util.Random;

public class Ant_leader extends Ant{
    
    private Random random = new Random();
    private int leader_angle;                                   //angle at whitch leader can change direction
    private Point last_position;                                //previouse leader direction
    private int boardHeight;                                    //board boudaries
    private int boardWidth;
    private int counter = 0;                                    //every 5 move, if closer than 100 units, leader goes towards food_source
    

    public Ant_leader(Point start, int leader_angle_value, int boardHeight, int boardWidth, Color color){
        super(start, color);
        this.leader_angle = leader_angle_value/2;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.last_position = new Point(start.X_pos(), start.Y_pos());
        
    }
    
    private boolean angle_check(int x, int y){                      //this function checks if provided vector(x,y) makes smaller angle beetwen last_position vector than leader_angle

        if(last_position.X_pos() == this.position.X_pos() && last_position.Y_pos() == this.position.Y_pos())    //if given same two points, retun true
            return true;

        double a, b, c, d;
        int x1, y1, x2, y2;

        x1 = this.position.X_pos() - last_position.X_pos();                                         //calculates two vectors, from previouse to cuurent position and from current to (x,y)
        y1 = this.position.Y_pos() - last_position.Y_pos();
        x2 = x - this.position.X_pos();
        y2 = y - this.position.Y_pos();

        a = x1 * x2 + y1 * y2;                                                                      //calculates cos value of angle betwen vectors
        b = Math.sqrt(x1*x1 + y1*y1);
        c = Math.sqrt(x2*x2 + y2*y2);

        d = a/(b*c);

        d = Math.toDegrees(Math.acos(d));                                                           //gets result in deegres from acos() function
        
        if(d <= leader_angle || 360-d <= leader_angle)                                              //if calculated angle is smaller than leader_angle returns true
            return true;
            
        return false;                                                                               //else returns false
    }

    public boolean simulate(Point end, Obstackle terrain){                                          //simulates ant movement, return true if ant has reached food source
        
        if(this.position.X_pos()  == end.X_pos() && this.position.Y_pos() == end.Y_pos())           //checks if ant is at food source position
            return true;
        
        
        int x = end.X_pos(), y = end.X_pos();

        if(counter == 0){                                                                           //if leader has not yet moved, it first move will be in food_source direction
            x = end.X_pos();
            y = end.Y_pos();
            counter ++;
        }
        else{
            boolean angle_change = false;
            do{                                                                                     //if leader has previosly moved, randomly picks x and y untill vector created by it and last lider move have smaller angle in beetwen than leader_angle

                x = random.nextInt(40)+position.X_pos() - 20;
                y = random.nextInt(40)+position.Y_pos() - 20;

                if(x > (boardWidth - 10)){                                                          //if x or y are out of board boundaries - 10 (minus 10 for extra safety), switch them to food_source respective value and end while loop
                    x = end.X_pos();
                    angle_change = true;
                }

                if(y > (boardHeight - 10)){
                    y = end.Y_pos();
                    angle_change = true;
                }
                    
                if(x < 10){
                    x = end.X_pos();
                    angle_change = true;
                }
                    
                if(y < 10){
                    y = end.Y_pos();
                    angle_change = true;
                }
                
            }while(!angle_check(x, y) && !angle_change);
        }

        float distance = (float)Math.sqrt((this.position.X_pos()-end.X_pos())*(this.position.X_pos()-end.X_pos())+(this.position.Y_pos()-end.Y_pos())*(this.position.Y_pos()-end.Y_pos()));     //leader distance from food

        if(distance < 100){                                                         //if distance is less than 100, leader can "smell" food, so every 5 move will be in food direction

            if(distance <= 10){
                this.position.new_coordinates(end.X_pos(), end.Y_pos());
                setCanSimulate();                                                   // if ant is at food source dont draw it
                return true;
            }
            
            if(counter >= 5){                                                       //every 5 move in food direction
    
                x = end.X_pos();
                y = end.Y_pos();
    
                counter = 1;
            }

            counter++;
        }
       
        x -= this.position.X_pos();                                                 //calculates vector from current position to (x,y)
        y -= this.position.Y_pos();

        distance = (float)Math.sqrt(x*x+y*y);                                       //calculates lenght of vector

        x = Math.round(x * (8 / distance));                                         //scales x and y movement to move aproximetly 8 tiles
        y = Math.round(y * (8 / distance));

        while(!check_collision(x, y, terrain)){                                     //if calculated movement results in collision with terrain, this while loop will be executed

            x = random.nextInt(40)+position.X_pos() - 20;                           //chosing random (x,y) point
            y = random.nextInt(40)+position.Y_pos() - 20;

            if(x > (boardWidth - Const.mapPadding))
                x = end.X_pos();

            if(y > (boardHeight - Const.mapPadding))
                y = end.Y_pos();
                
            if(x < Const.mapPadding)
                x = end.X_pos();
                
            if(y < Const.mapPadding)
                y = end.Y_pos();
            
            x -= this.position.X_pos();                                             //calculates vector from current position to (x,y)
            y -= this.position.Y_pos();
    
            distance = (float)Math.sqrt(x*x+y*y);                                   //calculates lenght of vector
    
            x = Math.round(x * (8 / distance));                                     //scales x and y movement to move aproximetly 8 tiles
            y = Math.round(y * (8 / distance));            
        }
        
        
        last_position.new_coordinates(this.position.X_pos(), this.position.Y_pos());    //previose position becomes current position

        this.position.change_coordinates(x, y);                                     //current position gets changed by <x,y> vector

        return false;
    }

}