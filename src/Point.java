public class Point {

    private int x_position;                             //x and y coordinates of given point (or vector)
    private int y_position;

    public Point(int x, int y){
        this.x_position = x;
        this.y_position = y;
    }

    public int X_pos(){                                 //returns x value
        return x_position;
    }

    public int Y_pos(){                                 //returns y value
        return y_position;
    }

    public void change_coordinates(int x, int y){       //changes current coordinates by given values
        this.x_position += x;
        this.y_position += y;
    }

    public void new_coordinates(int x, int y){          //replaces current coordinates by given values
        this.x_position = x;
        this.y_position = y;
    }
}
