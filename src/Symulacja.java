import java.awt.BorderLayout;

public class Symulacja {
    Symulacja(){
        frame = new Board(boardHeight, boardWidth, "symulacja");
        simulationEngine = new SimulationEngine(boardWidth, boardHeight, frame.return_tick(), frame.return_number_of_ants(), frame.return_leader_angle());
        frame.add(simulationEngine, BorderLayout.NORTH);
    }

    private int boardHeight = 500;
    private int boardWidth = 500;

    private Board frame;
    private SimulationEngine simulationEngine;

    public static void main(String[] args) throws Exception {
        new Symulacja();
    }
}
