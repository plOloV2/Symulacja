import java.util.Random;

public class Ant_leader extends Ant{

    // randomizer 
    private Random random = new Random();
    private int leader_angle;                                   //angle at whitch leader can change direction
    private Point last_direction = new Point(0, 0);         //previouse leader direction
    private int boardHeight;                                    //board boudaries
    private int boardWidth;
    

    
    public Ant_leader(Point start, int leader_angle_value, int boardHeight, int boardWidth){
        super(new Point(start.X_pos(), start.Y_pos()));
        this.leader_angle = leader_angle_value;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
    }
    
    private boolean angle_check(int x, int y){                      //this function checks if provided vector(x,y) makes smaller angle beetwen last_direction vector than leader_angle
        double a, b, c, d, result;

        a = (double)x * (double)last_direction.X_pos();
        b = (double)y * (double)last_direction.Y_pos();

        c = Math.sqrt(x*x + y*y);
        d = Math.sqrt(last_direction.X_pos()*last_direction.X_pos() + last_direction.Y_pos()*last_direction.Y_pos());

        result = (a+b)/(c*d);

        if(Math.acos(result) <= ((double)leader_angle/180.0))
            return true;

        return false;
    }

    public boolean simulate(Point end){                                     //simulates ant movement, return true if ant has reached food source
        
        if(this.position.X_pos()  == end.X_pos() && this.position.Y_pos()  == end.Y_pos())   //checks if ant is at food source position
            return true;

        int x, y;
        
        if(last_direction.X_pos() == 0 && last_direction.Y_pos() == 0){     //cheks if leader is yet to move

            x = end.X_pos() - this.position.X_pos();                        //if leader has not yet moved, first move will be in direction of food source
            y = end.Y_pos() - this.position.Y_pos();

            last_direction.new_coordinates(x, y);
        }
        else{
            do{                                                             //if leader has previosly moved, randomly picks x and y untill vector created by it and last lider move have smaller angle in beetwen than leader_angle

                x = random.nextInt(boardWidth) - this.position.X_pos();
                y = random.nextInt(boardHeight) - this.position.Y_pos();

            }while(angle_check(x, y));
        }
       
        float distance = (float)Math.sqrt(x*x+y*y);                         //calculates distance etwen its position and previos ant position

        x = Math.round(x * (2 / distance));                                 //scales x and y movement to move aproximetly 2 tiles
        y = Math.round(y * (2 / distance));

        this.position.change_coordinates(x, y);               //changes its position

        return false;
    }
}