import java.lang.Math;
public class Ant_worker extends Ant{

    public Ant_worker(Point start){
        super(start);
    }

    public boolean simulate(Point end_position, Point previouse_ant_position){      //simulates ant movement, return true if ant has reached food source

        if(this.position  == end_position)                                  //checks if ant is at food source position
            return true;


        int x = this.position.X_pos() - previouse_ant_position.X_pos();     //calculates x and y diference betwen its position and previos ant position
        int y = this.position.Y_pos() - previouse_ant_position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);                         //calculates distance etwen its position and previos ant position

        if(previouse_ant_position == end_position && distance <= 2){        //checks if previous ant is at food source and if distance to it is less than 2
            this.position = end_position;                                   //if yes, return true
            return true;
        }

        x = Math.round(x * (2 / distance));                                 //scales x and y movement to move aproximetly 2 tiles
        y = Math.round(y * (2 / distance));

        this.position.change_coordinates((short)x, (short)y);               //changes its position

        return false;
    }
}
