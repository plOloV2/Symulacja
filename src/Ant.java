import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;


public class Ant {
    class Line{     //klasa do przechowywania obiektu linia
        int x1, y1, x2, y2; //koordynaty oby punktów
        public Line(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    protected Point position;               //current position of ant
    private BufferedImage antImage;
    private boolean canSimulate = true;

    public Ant(Point start){
        this.position = new Point(start.X_pos(), start.Y_pos());
        try {
            antImage = ImageIO.read(new File("images/ant.png"));
        } catch (IOException ex) {
            System.out.println("Brak ant.png");
        }
    }

    public Point current_position(){        //return currnet position
        return position;
    }

    public void setCanSimulate(){   
        this.canSimulate = false;
    }

    public void draw(Graphics g){
        if(canSimulate){
            g.drawImage(antImage, position.X_pos()-16, position.Y_pos()-16, null);
        }
           
    }

    protected boolean check_collision(int x, int y, Obstackle terrain){

        if(x > 0)
            x++;
        else
            x--;
        
        if(y > 0)
            y++;
        else
            y--;

        boolean[][]move_board = new boolean[Math.abs(x)][Math.abs(y)];

        for(int i = 0; i != x; i += x/Math.abs(x))
            for(int j = 0; j != y; j += y/Math.abs(y))
                move_board[Math.abs(i)][Math.abs(j)] = terrain.check_position(new Point(this.position.X_pos() + i, this.position.Y_pos() + j));

        x = Math.abs(x);
        y = Math.abs(y);
        
        PriorityQueue<Cell> openSet = new PriorityQueue<>(Comparator.comparingInt(a -> a.heuristic));
        openSet.add(new Cell(0, 0, heuristic(0, 0, x, y)));
        int[] x_num = {-1, 0, 0, 1};
        int[] y_num = {0, -1, 1, 0};

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();

            if (current.x == x-1 && current.y == y-1) 
                return true;            // Path found

            if (move_board[Math.abs(current.x)][Math.abs(current.y)])
                continue;
            
            move_board[Math.abs(current.x)][Math.abs(current.y)] = true;

            for (int i = 0; i < 4; i++) {
                int new_x = current.x + x_num[i];
                int new_y = current.y + y_num[i];

                if (isValid(new_x, new_y, x, y) && !move_board[new_x][new_y]) {
                    Cell neighbor = new Cell(new_x, new_y, heuristic(new_x, new_y, x, y));
                    openSet.add(neighbor);
                }
            }
        }
        return false;                   // No path found
    }

    private int heuristic(int x, int y, int x_kon, int y_kon) {
        return Math.abs(x - x_kon) + Math.abs(y - y_kon);
    }

    private boolean isValid(int x, int y, int x_kon, int y_kon) {
        return (x >= 0) && (x < x_kon) && (y >= 0) && (y < y_kon);
    }

    private class Cell {
        int x, y;
        int heuristic;
    
        Cell(int x, int y, int heuristic) {
            this.x = x;
            this.y = y;
            this.heuristic = heuristic;
        }
    }
}
