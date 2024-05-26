
public class Symulacja {
    Symulacja(){
        simulationEngine = new SimulationEngine(boardWidth, boardHeight);
        frame = new Board(boardHeight, boardWidth, "symulacja", simulationEngine);
    }
    //, frame.return_tick(), frame.return_number_of_ants(), frame.return_leader_angle(),frame.return_antHillX(),frame.return_antHillY(),frame.return_foodSourceX(),frame.return_foodSourceY()

    private int boardHeight = 500;
    private int boardWidth = 500;

    private Board frame;
    private SimulationEngine simulationEngine;

    public static void main(String[] args) throws Exception {
        new Symulacja();
    }
}
