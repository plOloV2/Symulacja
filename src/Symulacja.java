import java.awt.BorderLayout;

public class Symulacja {
    Symulacja(){
        frame = new Board(boardHeight, boardWidth, "symulacja");
        simulationEngine = new SimulationEngine(boardWidth, boardHeight, frame.tick, frame.number_of_ants, frame.leader_angle);
        frame.add(simulationEngine, BorderLayout.NORTH);
    }

    int boardHeight = 500;
    int boardWidth = 500;

    Board frame;
    SimulationEngine simulationEngine;

    public static void main(String[] args) throws Exception {
        new Symulacja();
    }
}
