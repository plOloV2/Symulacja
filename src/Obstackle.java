import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Obstackle {

    private ArrayList<Point> occupied = new ArrayList<>();              //list of all spots on board that are covered by some obstackle
    private Random random = new Random();


    public Obstackle(Point anthill, Point food_source, int boardHeight, int boardWidth, int number_of_obstakles, int max_size_of_obstackle, int min_size_of_obstackle){
        
        for(int i = 0; i < number_of_obstakles; i++){

            int x = random.nextInt(boardWidth - 40) + 20;                                                                     //picks randome position for obstackle
            int y = random.nextInt(boardHeight - 40) + 20;

            double o1 = distance(x, y, food_source), o2 = distance(x, y, anthill);

            if(o1 <= 75 || o2 <= 75 || !proximity_check(x, y, 20)){                 //checks if this position is not too close to other obstackles, anthill or food source
                i--;
                continue;                                                                    //if it is, skips rest of loop and tries one more time
            }

            occupied.add(new Point(x, y));                                                                          //if its not, marks given point as blocked

            int size = random.nextInt(max_size_of_obstackle - min_size_of_obstackle) + min_size_of_obstackle;       //randomly picks how many neighboring points should be blocked
            
            for(int j = 0; j < size; j++){

                x = occupied.get(occupied.size()-1).X_pos();                                                        //takes last obstackle position
                y = occupied.get(occupied.size()-1).Y_pos();

                x += random.nextInt(3) - 1;                                                                         //changes its x and y values by -1, 0 or 1
                y += random.nextInt(3) - 1;

                if(x > boardWidth)                                                                                  //checks if x and y is not out of boundaries
                    x = boardWidth;

                if(x < 0)
                    x = 0;
                
                if(y > boardHeight)
                    y = boardHeight;
                
                if(y < 0)
                    y = 0;

                if(!check_position(new Point(x, y)))                                                                 //checks if given point is not yet occupied
                    occupied.add(new Point(x, y));

                else{                                                                                               //if poit is occupied, subtracks 1 from iterator and repeates cycle
                    continue;
                }

            }
            
        }

    }

    private double distance(int x, int y, Point target){                //returns distance beetwen given point and x,y coordinates

        x = target.X_pos() - x;
        y = target.Y_pos() - y;

        return (double)Math.sqrt(x*x+y*y);
    }

    private boolean proximity_check(int x, int y, int distance){       //checks distance beetwen given point and x,y coordinates
        if(occupied != null){
            for(int i = 0; i < occupied.size(); i++)
                if(distance(x, y, occupied.get(i)) <= distance)
                    return false;
        }

        return true;

    }

    public boolean check_position(Point position){                    //checks if given point is occupied or not
        if(occupied != null){
            for(int i = 0; i < occupied.size(); i++)
                if(occupied.get(i).X_pos() == position.X_pos() && occupied.get(i).Y_pos() == position.Y_pos())
                    return true;
        }
        
        return false;
 
    }

    public void draw(Graphics g){                                   //draws all obstackles to the board
        g.setColor(Color.gray);
        if(occupied != null){
            for(int i = 0; i < occupied.size(); i++){
                g.fillRect(occupied.get(i).X_pos(), occupied.get(i).Y_pos(), 1, 1);
            }
        }

    }
}
