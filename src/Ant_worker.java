import java.lang.Math;
public class Ant_worker extends Ant{

    public Ant_worker(Point start){
        super(start);
    }

    public boolean symulate(Point end_position, Point previouse_ant_position){

        if(this.position  == end_position)
            return true;


        int x = this.position.X_pos() - previouse_ant_position.X_pos();
        int y = this.position.Y_pos() - previouse_ant_position.Y_pos();
        
        float distance = (float)Math.sqrt(x*x+y*y);

        if(previouse_ant_position == end_position && distance <= 2){
            this.position = end_position;
            return true;
        }

        x = Math.round(x * (2 / distance));
        y = Math.round(y * (2 / distance));

        this.position.change_coordinates((short)x, (short)y);

        return false;
    }
}
