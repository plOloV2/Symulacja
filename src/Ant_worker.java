import java.lang.Math;
public class Ant_worker extends Ant{

    public Ant_worker(Point start){
        super(start);
    }

    public boolean simulate(Point end_position, Point previouse_ant_position){      //simulates ant movement, return true if ant has reached food source

        if(this.position.X_pos()  == end_position.X_pos() && this.position.Y_pos()  == end_position.Y_pos())                                  //checks if ant is at food source position
            return true;


        int x = previouse_ant_position.X_pos() - this.position.X_pos();     //calculates x and y diference betwen its position and previos ant position
        int y = previouse_ant_position.Y_pos() - this.position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);                         //calculates distance betwen its position and previos ant position

        if(this.position.X_pos()  == end_position.X_pos() && this.position.Y_pos()  == end_position.Y_pos() && distance <= 2){        //checks if previous ant is at food source and if distance to it is less than 2
            this.position = end_position;                                   //if yes, return true
            return true;
        }

        x = Math.round(x * (4 / distance));                                 //scales x and y movement to move aproximetly 2 tiles
        y = Math.round(y * (4 / distance));

        this.position.change_coordinates(x, y);               //changes its position

        return false;
    }
}
