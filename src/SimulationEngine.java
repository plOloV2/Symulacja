import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationEngine extends JPanel implements ActionListener {
    //panel size
    private int boardHeight;
    private int boardWidth;

    private int max_number_of_ants;     //min 10
    private boolean end = false;        //ends simulation
    private int tick = 0;               //counts how smany ticks passed since last ant was created
    private int leader_angle;           //describes max angle or leaders path change
    private Point anthill;              //for user to decide position          //for user to decide position
    private Point food_source;          //for user to decide position

    private Image ant_hillImage;
    private Image food_sourceImage;
    

    //objects
    private Ant_leader antLeader;
    private Obstackle terrain;
    // terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 5, 10, 5);
    private ArrayList<Ant_worker> workers;

    // simulation logic
    Timer simulationLoop;
    int tickDistance = 0;
    int lastTick = 0;
    int time = 0; // for "time" measurement


    SimulationEngine(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(new Color(0,102,0));

        import_images();

        simulationLoop = new Timer(100, this);
        simulationLoop.start();
    }

    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void import_images(){
                        
            try {
                ant_hillImage = new ImageIcon("images/ant_hill.png").getImage();
                food_sourceImage = new ImageIcon("images/food_source.png").getImage();
            } catch (Exception ex) {
                System.out.println("image didnt load");
            }

    }

    public void draw(Graphics g){
        if(anthill != null){
            g.drawImage(ant_hillImage, anthill.X_pos()-32, anthill.Y_pos()-32, 100, 100, this);
        }
        if(food_source != null){
            g.drawImage(food_sourceImage, food_source.X_pos()-10, food_source.Y_pos()-10, 50, 50, this);
        }
        if(antLeader != null){
            antLeader.draw(g);
            antLeader.draw1(g);
        }
        if(workers != null){
            for(int i = 0; i < workers.size(); i++){
                workers.get(i).draw(g);
            }
        }
        if(terrain != null){
            terrain.draw(g);
        }


    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(antLeader != null){
            if(workers == null){
                workers = new ArrayList<Ant_worker>();
            }
            if(terrain == null){
                terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 30, 200, 50);
            }
        }


        if((lastTick+20)<tickDistance){ // licznik tak aby co 10 powtórzen ruchu pojawiała sie nowa mrówka
            lastTick = 0;
            tickDistance = 0;
            if(workers != null){    //gdy tablica mrówek nie jest pusta to dodaje chodzi o to ze inaczej nie mozna uzyc workers.size()
                if(workers.size() <= max_number_of_ants){
                    workers.add(new Ant_worker(anthill)); // dodaje mrówke
                }
            }
        }
        tickDistance++;

        if(workers != null){
            for(int i = 0; i < workers.size(); i++){
                if(i == 0){
                    workers.get(i).simulate(food_source, antLeader.current_position());
                }
                else{
                    end = workers.get(i).simulate(food_source, workers.get(i-1).current_position());
                }
            }
        }

        if(antLeader != null){
            antLeader.simulate(food_source);
        }
        
        simulationLoop.setDelay(1000-tick);

        if(end)
            System.out.println("Koniec"); //ostatnia mrówka jest u celu

        repaint();

    }



    public void set_tick(int tick){
        this.tick = tick;
    }
    public void set_number_of_ants(int number_of_ants){
        this.max_number_of_ants = number_of_ants;    
    }
    public void set_leader_angle(int leader_angle){
        this.leader_angle = leader_angle;
    }
    public void set_antHill(int x, int y){
        anthill = new Point(x, y);
        antLeader = new Ant_leader(anthill, leader_angle, boardHeight, boardWidth);
    }
    public void set_foodSource(int x, int y){
        food_source = new Point(x,y);
    }

}
