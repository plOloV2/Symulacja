public class Point {

    private int x_position;
    private int y_position;

    public Point(int x, int y){
        this.x_position = x;
        this.y_position = y;
    }

    public int X_pos(){
        return x_position;
    }

    public int Y_pos(){
        return y_position;
    }

    public void change_coordinates(int x, int y){
        this.x_position += x;
        this.y_position += y;
    }
}
