
public class Symulacja {
    Symulacja(){
        simulationEngine = new SimulationEngine(boardWidth, boardHeight);
        frame = new Board(boardHeight, boardWidth, "symulacja", simulationEngine);
    }

    private int boardHeight = 500;
    private int boardWidth = 500;

    @SuppressWarnings("unused")
    private Board frame;
    private SimulationEngine simulationEngine;

    public static void main(String[] args) throws Exception {
        new Symulacja();
    }
}
