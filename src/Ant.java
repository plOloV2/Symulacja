public class Ant {
    //po niej bedą dziedziczyć robotnice i lider
    protected Point position;

    public Ant(Point start){
        this.position = start;
    }

    public Point current_position(){
        return position;
    }
}
