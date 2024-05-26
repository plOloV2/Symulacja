public class Ant {
    protected Point position;               //current position of ant

    public Ant(Point start){
        this.position = start;
    }

    public Point current_position(){        //return currnet position
        return position;
    }

    
}
