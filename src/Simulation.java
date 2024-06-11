
public class Simulation {
    Simulation(){
        simulationEngine = new SimulationEngine(Const.boardWidth, Const.boardHeight);                   //creating simulationEngine object
        frame = new Board(Const.boardHeight, Const.boardWidth, "symulacja", simulationEngine);    //creating board object and passing simulationEngine
    }

    @SuppressWarnings("unused")
    private Board frame;
    private SimulationEngine simulationEngine;

    public static void main(String[] args) throws Exception {
        new Simulation();
    }
}
