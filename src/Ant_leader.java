import java.util.Random;

public class Ant_leader extends Ant{

    // randomizer 
    private Random random = new Random();
    private int leader_angle;                                   //angle at whitch leader can change direction
    private Point last_position = new Point(0, 0);         //previouse leader direction
    private int boardHeight;                                    //board boudaries
    private int boardWidth;
    private int counter;                                        //every 3 move leader goes towards food_source
    

    
    public Ant_leader(Point start, int leader_angle_value, int boardHeight, int boardWidth){
        super(start);
        this.leader_angle = leader_angle_value;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        counter = 1;
    }
    
    private boolean angle_check(int x, int y){                      //this function checks if provided vector(x,y) makes smaller angle beetwen last_position vector than leader_angle
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
        
        if(d <= leader_angle)
            return true;
        
            
        return false;
    }

    public boolean simulate(Point end){                                     //simulates ant movement, return true if ant has reached food source
        
        if(this.position  == end)                                           //checks if ant is at food source position
            return true;
        
        float distance = (float)Math.sqrt((this.position.X_pos()-end.X_pos())*(this.position.X_pos()-end.X_pos())+(this.position.Y_pos()-end.Y_pos())*(this.position.Y_pos()-end.Y_pos()));
        
        if(distance <= 5){
            this.position.new_coordinates(end.X_pos(), end.Y_pos());
            return true;
        }
        counter++;

        int x, y;
        
        if(counter >= 5){                                                   //cheks if leader is yet to move

            x = end.X_pos() - this.position.X_pos();                        //if leader has not yet moved, first move will be in direction of food source
            y = end.Y_pos() - this.position.Y_pos();

            counter = 0;
        }
        else{
            do{                                                             //if leader has previosly moved, randomly picks x and y untill vector created by it and last lider move have smaller angle in beetwen than leader_angle

                x = random.nextInt(boardWidth-10);
                y = random.nextInt(boardHeight-10);

            }while(!angle_check(x, y));
        }
       
        x = this.position.X_pos() - x;
        y = this.position.Y_pos() - y;

        distance = (float)Math.sqrt(x*x+y*y);                               //calculates distance etwen its position and previos ant position

        x = Math.round(x * (10 / distance));                                 //scales x and y movement to move aproximetly 2 tiles
        y = Math.round(y * (10 / distance));   

        System.out.println("p: "+this.position.X_pos()+" "+this.position.Y_pos()+" zmiana: "+x+" "+y);     
        
        last_position.new_coordinates(this.position.X_pos(), this.position.X_pos());

        this.position.change_coordinates(x, y);                             //changes its position

        if(this.position.X_pos() < 5 )
            this.position.change_coordinates(5, last_position.Y_pos() + y);

        if(this.position.Y_pos() < 5 )
            this.position.change_coordinates(last_position.X_pos() + x, 5);

        if(this.position.X_pos() > 470 )
            this.position.change_coordinates(470, last_position.Y_pos() + y);

        if(this.position.Y_pos() > 470 )
            this.position.change_coordinates(last_position.X_pos() + x, 470);


        return false;
    }
}