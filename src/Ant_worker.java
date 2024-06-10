import java.awt.Color;
import java.lang.Math;
import java.util.Random;
public class Ant_worker extends Ant{

    public Ant_worker(Point start, Color color){
        super(start, color);
    }

    Point last_position;

    public boolean simulate(Point end_position, Point previouse_ant_position, Obstackle terrain){      //simulates ant movement, return true if ant has reached food source                              

        int x = previouse_ant_position.X_pos() - this.position.X_pos();             //calculates x and y diference betwen its position and previos ant position
        int y = previouse_ant_position.Y_pos() - this.position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);                                 //calculates distance betwen its position and previos ant position

        if(previouse_ant_position.X_pos()  == end_position.X_pos() && previouse_ant_position.Y_pos()  == end_position.Y_pos() && distance <= 4){        //checks if previous ant is at food source and if distance to it is less than 2
            this.position = end_position;                                           //if yes, return true
            setCanSimulate();                                                       //if ant is at food source dont draw it
            return true;
        }

        if(distance > 30){
            x = Math.round(x * (8 / distance));                                     //if distance to previouse ant is bigger than 30 units, scales x and y movement to move aproximetly 8 tiles
            y = Math.round(y * (8 / distance));
        }
        else{
            x = Math.round(x * (4 / distance));                                     //if distance to previouse ant is smaller than 30 units, scales x and y movement to move aproximetly 4 tiles
            y = Math.round(y * (4 / distance));
        }

        int iter = 0;

        while(!check_collision(x, y, terrain)){
            Random random = new Random();
            iter += 2;

            x = random.nextInt(iter*2)+previouse_ant_position.X_pos() - iter;
            y = random.nextInt(iter*2)+previouse_ant_position.Y_pos() - iter;
            
            x -= this.position.X_pos();
            y -= this.position.Y_pos();
    
            distance = (float)Math.sqrt(x*x+y*y);                                   //calculates distance etwen its position and previos ant position
    
            if(distance > 30){
                x = Math.round(x * (8 / distance));                                 //if distance to previouse ant is bigger than 30 units, scales x and y movement to move aproximetly 8 tiles
                y = Math.round(y * (8 / distance));
            }
            else{
                x = Math.round(x * (4 / distance));                                 //if distance to previouse ant is smaller than 30 units, scales x and y movement to move aproximetly 4 tiles
                y = Math.round(y * (4 / distance));
            }         
        }

        this.position.change_coordinates(x, y);                                     //changes ants position

        last_position = position;

        return false;
    }
    



}
