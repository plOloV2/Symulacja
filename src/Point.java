public class Point {

    private short x_position;
    private short y_position;

    public Point(short x, short y){
        this.x_position = x;
        this.y_position = y;
    }

    public short X_pos(){
        return x_position;
    }

    public short Y_pos(){
        return y_position;
    }

    public void change_coordinates(short x, short y){
        this.x_position += x;
        this.y_position += y;
    }
}
