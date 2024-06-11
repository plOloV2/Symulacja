import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;


public class Ant {

    class Line{                                             //class for line object
        @SuppressWarnings("unused")
        private int x1, y1, x2, y2;                                 //koordinates of line points
        public Line(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public Ant(Point start,Color color){                    //ant creator
        this.position = new Point(start.X_pos(), start.Y_pos());
        line = new ArrayList<>();
        this.color = color;
        try {
            antImage = ImageIO.read(new File("images/ant.png"));
        } catch (IOException ex) {
            System.out.println("Brak ant.png");
        }
    }

    protected Point position;                               //current position of ant
    private BufferedImage antImage;
    private boolean canSimulate = true;
    private ArrayList<Point> line;                          //list, that holds the line

    private Color color;                                    //for line painting
    

    public Point current_position(){                        //return currnet position
        return position;
    }

    public void setCanSimulate(){                           //changes can simulate value to false
        this.canSimulate = false;
    }

    public void draw(Graphics g){                           //if ant can be simulated, it will be draw
        if(canSimulate){
            g.drawImage(antImage, position.X_pos()-Const.antPhotoSpacing, position.Y_pos()-Const.antPhotoSpacing, null);
        }
           
    }

    public void draw1(Graphics g){                          //draws line behind ant
        g.setColor(color);  
        line.add(new Point(position.X_pos(),position.Y_pos()));
        for(int i = 1; i < line.size(); i++){
            g.drawLine(line.get(i).X_pos(), line.get(i).Y_pos(), line.get(i-1).X_pos(), line.get(i-1).Y_pos());
        }
        
    }

    protected boolean check_collision(int x, int y, Obstackle terrain){             //checks if ant will not colide with terrain, if it is to move by <x,y> vector

        if(x > 0)                                                                   //extends x and y values by 1 for map drawnig
            x++;
        else
            x--;
        
        if(y > 0)
            y++;
        else
            y--;

        boolean[][]move_board = new boolean[Math.abs(x)][Math.abs(y)];              //creates map of tile that ant can go on

        for(int i = 0; i != x; i += x/Math.abs(x))                                  //checks if tiles are occupied, if they are marks them by setting to true
            for(int j = 0; j != y; j += y/Math.abs(y))
                move_board[Math.abs(i)][Math.abs(j)] = terrain.check_position(new Point(this.position.X_pos() + i, this.position.Y_pos() + j));

        x = Math.abs(x);                                                            //changes x and y to absolute values
        y = Math.abs(y);
        
        PriorityQueue<Cell> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.heuristic));       //priority queue based on tile distance from destnation for Greedy Best-First Search algorithm ( O(E) )
        openSet.add(new Cell(0, 0, heuristic(0, 0, x, y)));                 //ads starting cell to queue
        int[] x_num = {-1, 0, 0, 1};                                                //for movemt posibilities
        int[] y_num = {0, -1, 1, 0};

        while (!openSet.isEmpty()){                                                 //untill the queue is empty looks for posible passage
            Cell current = openSet.poll();                                          //sets current cell as one from top of the queue

            if (current.x == x-1 && current.y == y-1)                               //if current if our destination, we have reach end and we will return true
                return true;            // Path found

            if (move_board[Math.abs(current.x)][Math.abs(current.y)])               //if current cell is occupied/been there befour, we skip to next loop iteration
                continue;
            
            move_board[Math.abs(current.x)][Math.abs(current.y)] = true;            //else we mark our location as occupied/allready visited

            for (int i = 0; i < 4; i++) {                                           //calculates positions for new cells
                int new_x = current.x + x_num[i];
                int new_y = current.y + y_num[i];

                if (isValid(new_x, new_y, x, y) && !move_board[new_x][new_y]) {     //checks if they are not out of boundaries or on occupied/allready vidited tiles
                    Cell neighbor = new Cell(new_x, new_y, heuristic(new_x, new_y, x, y));
                    openSet.add(neighbor);                                          //ads new cells to queue
                }
            }
        }
        return false;                                                               //if there is no more cells left in queue, than no path was found
    }

    private int heuristic(int x, int y, int x_kon, int y_kon) {                     //calculates relativ distance from destination
        return Math.abs(x - x_kon) + Math.abs(y - y_kon);
    }

    private boolean isValid(int x, int y, int x_kon, int y_kon) {                   //checks if cell is not out of boundaries
        return (x >= 0) && (x < x_kon) && (y >= 0) && (y < y_kon);
    }


    private class Cell {                                                            //Cell class for Greedy Best-First Search algorithm
        int x, y;
        int heuristic;
    
        Cell(int x, int y, int heuristic) {
            this.x = x;
            this.y = y;
            this.heuristic = heuristic;
        }
    }
}
