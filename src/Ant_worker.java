import java.lang.Math;
public class Ant_worker extends Ant{

    public Ant_worker(Point start){
        super(start);
    }

    public boolean symulate(Point end_position, Point previouse_ant_position){  //true -> ant reached destination, false -> still way to go

        if(this.position  == end_position)                                      //returns true if ant has reached food source
            return true;


        int x = this.position.X_pos() - previouse_ant_position.X_pos();         //calculates x and y distanse to previose ant
        int y = this.position.Y_pos() - previouse_ant_position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);                             //calculates distanse in straight line to previose ant

        if(previouse_ant_position == end_position && distance <= 2){            //if previose ant is already in food source and distance to food surce is smaller than 2, ant goes to food source
            this.position = end_position;
            return true;
        }

        x = Math.round(x * (2 / distance));                                     //clamps distance to travel to 2 tiles
        y = Math.round(y * (2 / distance));

        this.position.change_coordinates((short)x, (short)y);                   //changes ant position

        return false;
    }
}
