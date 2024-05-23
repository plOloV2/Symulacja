import java.util.ArrayList;
import java.util.Random;

public class Obstackle {

    private ArrayList<Point> occupied;
    private Random random = new Random();


    public Obstackle(Point anthill, Point food_source, int boardHeight, int boardWidth, int number_of_obstakles, int max_size_of_obstackle, int min_size_of_obstackle){
        
        for(int i = 0; i < number_of_obstakles; i++){

            int x = random.nextInt(boardWidth);
            int y = random.nextInt(boardHeight);

            if(distance(x, y, food_source) <= 5 || distance(x, y, anthill) <= 5 || check_if_already_occupied(x, y)){
                i--;
                continue;
            }


        }

    }

    private double distance(int x, int y, Point target){

        x -= target.X_pos();
        y -= target.Y_pos();

        return (double)Math.sqrt(x*x+y*y);
    }

    private boolean check_if_already_occupied(int x, int y){
        return true;
    }
}
