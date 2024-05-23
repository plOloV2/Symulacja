import java.util.ArrayList;
import java.util.Random;

public class Obstackle {

    private ArrayList<Point> occupied;
    private Random random = new Random();


    public Obstackle(Point anthill, Point food_source, int boardHeight, int boardWidth, int number_of_obstakles, int max_size_of_obstackle, int min_size_of_obstackle){
        
        for(int i = 0; i < number_of_obstakles; i++){

            int x = random.nextInt(boardWidth);
            int y = random.nextInt(boardHeight);

            if(distance(x, y, food_source) <= 5 || distance(x, y, anthill) <= 5 || proximity_check(x, y)){
                i--;
                continue;
            }

            occupied.add(new Point(x, y));
            int size = random.nextInt(max_size_of_obstackle - min_size_of_obstackle) + min_size_of_obstackle;
            
            for(int j = 0; j < size; j++){

                x = occupied.get(occupied.size()-1).X_pos();
                y = occupied.get(occupied.size()-1).Y_pos();

                x += random.nextInt(2) - 1;
                y += random.nextInt(2) - 1;

                if(x > boardWidth)
                    x = boardWidth;

                if(x < 0)
                    x = 0;
                
                if(y > boardHeight)
                    y = boardHeight;
                
                if(y > 0)
                    y = 0;

                if(check_position(new Point(x, y)))
                    occupied.add(new Point(x, y));

                else{
                    i--;
                    continue;
                }

            }
            
        }

    }

    private double distance(int x, int y, Point target){

        x -= target.X_pos();
        y -= target.Y_pos();

        return (double)Math.sqrt(x*x+y*y);
    }

    private boolean proximity_check(int x, int y){

        for(int i = 0; i < occupied.size(); i++)
            if(distance(x, y, occupied.get(i)) <= 10)
                return false;

        return true;
    }

    public boolean check_position(Point position){

        for(int i = 0; i < occupied.size(); i++)
            if(occupied.get(i) == position)
                return false;

        return true;
    }
}
