import java.util.Random;

public class Ant_lider extends Ant{

    // randomizer 
    Random random = new Random();
    int leader_angle;
    Point last_direction = new Point(0, 0);
    

    
    public Ant_lider(Point start, int leader_angle_value){
        super(start);
        this.leader_angle = leader_angle_value;
    }
    
    private boolean angle_check(int x, int y){
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

    public boolean symulate(Point end){
        
        if(this.position  == end)
            return true;

        int x, y;
        
        if(last_direction.X_pos() == 0 && last_direction.Y_pos() == 0){

            x = end.X_pos() - this.position.X_pos();
            y = end.Y_pos() - this.position.Y_pos();

            last_direction.new_coordinates(x, y);
        }
        else{
            do{

                x = random.nextInt(500) - this.position.X_pos();
                y = random.nextInt(500) - this.position.Y_pos();

            }while(angle_check(x, y));
        }
       
        float distance = (float)Math.sqrt(x*x+y*y);

        x = Math.round(x * (2 / distance));
        y = Math.round(y * (2 / distance));

        this.position.change_coordinates((short)x, (short)y);

        return false;

    }
}