import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
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
    private Point anthill;              //for user to decide position
    private Point anthillCpy;              //for user to decide position
    private Point food_source;          //for user to decide position
    private Random random = new Random();
    private int tX;
    private int tY;

    private Image ant_hillImage;
    private Image food_sourceImage;
    

    //objects
    private Ant_leader antLeader;
    private Obstackle terrain;
    // terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 5, 10, 5);
    private ArrayList<Ant_worker> workers;

    // simulation logic
    Timer gameloop;
    int tickDistance = 0;
    int lastTick = 0;
    int time = 0; // for "time" measurement


    SimulationEngine(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(new Color(0,102,0));
        anthill= new Point(-100,0);   
        food_source = new Point(-100, 0);
        antLeader = new Ant_leader(anthillCpy, leader_angle, boardHeight, boardWidth);

        import_images();

        gameloop = new Timer(1000-tick, this);
        gameloop.start();
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
                System.out.println("Image didnt load");
            }

    }

    public void draw(Graphics g){
                g.drawImage(ant_hillImage, anthill.X_pos()-32, anthill.Y_pos()-32, 100, 100, this);
                g.drawImage(food_sourceImage, food_source.X_pos()-10, food_source.Y_pos()-10, 50, 50, this);
                antLeader.draw(g);
                if(workers != null){
                    for(int i = 0; i < workers.size(); i++){
                        workers.get(i).draw(g);
                    }
                }


    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        tickDistance++;
        if((lastTick+100)>tickDistance){
            lastTick = tickDistance;
            if(workers != null){
                if(workers.size()<max_number_of_ants){
                    workers.add(new Ant_worker(anthill));
                }
            }
        }

        antLeader.simulate(food_source);

        System.out.println(anthill.X_pos()+" "+" "+anthill.Y_pos());





        
        // if(tick%3 == 0){
        //     if(workers.size() < max_number_of_ants)
                
            
        //     tick = 1;
        // }
        // else 
        //     tick ++;

        // end = antLeader.simulate(food_source);

        // if(max_number_of_ants==0){}
        // else{
        //     time = tick;
        // }

        // if(workers.size() > 0)
        //     end = workers.get(0).simulate(food_source, antLeader.current_position());


        // for(int i = 1; i < workers.size(); i++)
        //     end = workers.get(i).simulate(food_source, workers.get(i-1).current_position());
        
            
        // //if (!end) koniec symulacji
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
    public void set_antHillX(int x){
        anthill.give_X_pos(x);
        anthillCpy.give_X_pos(x);
        
    }
    public void set_antHillY(int y){
        anthill.give_Y_pos(y);
        anthillCpy.give_Y_pos(y);
    }
    public void set_foodSourceX(int x){
        food_source.give_X_pos(x);
    }
    public void set_foodSourceY(int y){
        food_source.give_Y_pos(y);
    }

}
