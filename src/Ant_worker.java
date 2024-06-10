import java.awt.Graphics;
import java.lang.Math;
import java.util.Random;
public class Ant_worker extends Ant{

    public Ant_worker(Point start){
        super(start);
    }
    Point last_position;

    public boolean simulate(Point end_position, Point previouse_ant_position, Obstackle terrain){      //simulates ant movement, return true if ant has reached food source

        if(this.position.X_pos()  >= (end_position.X_pos()-2) &&
        this.position.X_pos()  <= (end_position.X_pos()+2) &&
        this.position.Y_pos()  >= (end_position.Y_pos()-2) &&
        this.position.Y_pos()  <= (end_position.Y_pos()+2)){ //checks if ant is at food source position
            setCanSimulate();                                               // if ant is at food source dont draw it
            return true;
        }                                

        int x = previouse_ant_position.X_pos() - this.position.X_pos();     //calculates x and y diference betwen its position and previos ant position
        int y = previouse_ant_position.Y_pos() - this.position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);                         //calculates distance betwen its position and previos ant position

        if(this.position.X_pos()  == end_position.X_pos() && this.position.Y_pos()  == end_position.Y_pos() && distance <= 2){        //checks if previous ant is at food source and if distance to it is less than 2
            this.position = end_position;                                   //if yes, return true
            setCanSimulate();                                               //if ant is at food source dont draw it
            return true;
        }

        x = Math.round(x * (8 / distance));                                 //scales x and y movement to move aproximetly 10 tiles
        y = Math.round(y * (8 / distance));

        while(!check_collision(x, y, terrain)){
            Random random = new Random();

            x = random.nextInt(40)+position.X_pos() - 20;
            y = random.nextInt(40)+position.Y_pos() - 20;
            
            x -= this.position.X_pos();
            y -= this.position.Y_pos();
    
            distance = (float)Math.sqrt(x*x+y*y);                                   //calculates distance etwen its position and previos ant position
    
            x = Math.round(x * (8 / distance));                                    //scales x and y movement to move aproximetly 10 tiles
            y = Math.round(y * (8 / distance));            
        }

        this.position.change_coordinates(x, y);               //changes ants position

        last_position = position;

        return false;
    }
    
    public void draw1(Graphics g){
    }
}
