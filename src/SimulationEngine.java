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
    private int number_of_lines;     //values: 1-5
    private boolean end = false;        //ends simulation
    private int tick = 0;               //counts how smany ticks passed since last ant was created
    private int leader_angle;           //describes max angle or leaders path change
    private Point anthill;              //for user to decide position          //for user to decide position
    private Point food_source;          //for user to decide position

    private Image ant_hillImage;
    private Image food_sourceImage;

    private int colorChange;            //for changing lines colors
    

    //objects
    private Ant_leader antLeader;
    private Obstackle terrain;
    // terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 5, 10, 5);
    private ArrayList<Ant_worker> workers;

    // simulation logic
    private Timer simulationLoop;
    private int tickDistance = 0;
    private int lastTick = 0;


    SimulationEngine(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Const.backgroundcolor);

        import_images();

        simulationLoop = new Timer(0, this);
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
            g.drawImage(ant_hillImage, anthill.X_pos()-Const.aHPhotoSpacing, anthill.Y_pos()-Const.aHPhotoSpacing, 100, 100, this);
        }
        if(food_source != null){
            g.drawImage(food_sourceImage, food_source.X_pos()-Const.fSPhotoSpacing, food_source.Y_pos()-Const.fSPhotoSpacing, 50, 50, this);
        }
        if(antLeader != null){
                antLeader.draw(g);
                antLeader.draw1(g);


        }
        if(workers != null){
            double lineNum = ((double)(max_number_of_ants)/(double)number_of_lines);
            double tLineNum = lineNum;
            for(int i = 0; i < workers.size(); i++){
                workers.get(i).draw(g);
                if((i+1) == (int)Math.round(tLineNum) ){
                    workers.get(i).draw1(g);
                    tLineNum += lineNum;
                }
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
                if(workers.size() < max_number_of_ants){
                    int colorNum = Const.startColorNumber - (workers.size()+1)*colorChange;
                    workers.add(new Ant_worker(anthill, new Color(colorNum,colorNum,colorNum))); // dodaje mrówke
                }
                    
            }
        }
        tickDistance++;

        if(workers != null){
            for(int i = 0; i < workers.size(); i++){
                if(i == 0){
                    workers.get(i).simulate(food_source, antLeader.current_position(), terrain);
                }
                else{
                    end = workers.get(i).simulate(food_source, workers.get(i-1).current_position(), terrain);
                }
            }
        }

        if(antLeader != null){
            antLeader.simulate(food_source, terrain);
        }

        int realTick = 1000-tick;                                   //for changing tick value to relevant variable for setDealy method
        simulationLoop.setDelay(realTick);                             

        if(end)
        {
            System.out.println("Koniec"); //ostatnia mrówka jest u celu
            simulationLoop.stop();
        }
            
        repaint();

    }
    


    public void set_tick(int tick){
        this.tick = tick;
    }
    public void set_number_of_ants(int number_of_ants){
        this.max_number_of_ants = number_of_ants;  
        this.colorChange = (int)Math.round(Const.colorRangeNumber/(number_of_ants+1));  
    }
    public void set_number_of_lines(int number_of_lines){
        this.number_of_lines = number_of_lines;    
    }
    public void set_leader_angle(int leader_angle){
        this.leader_angle = leader_angle;
    }
    public void set_antHill(int x, int y){
        anthill = new Point(x, y);
        antLeader = new Ant_leader(anthill, leader_angle, boardHeight, boardWidth, Color.white);
    }
    public void set_foodSource(int x, int y){
        food_source = new Point(x,y);
    }

}
