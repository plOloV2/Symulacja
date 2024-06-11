import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationEngine extends JPanel implements ActionListener {
    //panel size
    private int boardHeight;
    private int boardWidth;

    private int max_number_of_ants;     //min 10
    private int number_of_lines;        //values: 1-5
    private boolean end = false;        //ends simulation
    private int tick = 0;               //counts how smany ticks passed since last ant was created
    private int leader_angle;           //describes max angle or leaders path change
    private Point anthill;              //for user to decide position
    private Point food_source;          //for user to decide position

    private Image ant_hillImage;        //image for anthill
    private Image food_sourceImage;     //image for food source

    private int colorChange;            //for changing lines colors
    

    //objects
    private Ant_leader antLeader;
    private Obstackle terrain;
    private ArrayList<Ant_worker> workers;

    // simulation logic
    private Timer simulationLoop;       //dimulation loop
    private int tickDistance = 0;       //for counting loops between adding ants



    SimulationEngine(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;                                                                 //panel properties
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Const.backgroundcolor);

        import_images();                                                                                

        simulationLoop = new Timer(0, this);                                                      //creating simulation life loop
        simulationLoop.start();
    }

    
    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        draw(g);
    }

    public void import_images(){                                                                        //method for import all necessary images
                        
            try {
                ant_hillImage = new ImageIcon("images/ant_hill.png").getImage();
                food_sourceImage = new ImageIcon("images/food_source.png").getImage();
            } catch (Exception ex) {
                System.out.println("image didnt load");
            }

    }

    public void draw(Graphics g){
        if(anthill != null){                                                                        //if anthill is not null draw it
            g.drawImage(ant_hillImage, anthill.X_pos()-Const.aHPhotoSpacing, anthill.Y_pos()-Const.aHPhotoSpacing, 100, 100, this);
        }
        if(food_source != null){                                                                    //if food source is not null draw it
            g.drawImage(food_sourceImage, food_source.X_pos()-Const.fSPhotoSpacing, food_source.Y_pos()-Const.fSPhotoSpacing, 50, 50, this);
        }
        if(antLeader != null){                                                                      //if ant leader is not null draw it and draw lines behind it
                antLeader.draw(g);
                antLeader.draw1(g);


        }
        if(workers != null){                                                                        //if workers are not null draw them and lines after chosen ones
            double lineNum = ((double)(max_number_of_ants)/(double)number_of_lines);                //value beetwen every ant that has to call draw1 method
            double tLineNum = lineNum;                                                              //temporary variable for adding values beetwen ants it is needed for finding closest ant to rounded value
            for(int i = 0; i < workers.size(); i++){
                workers.get(i).draw(g);
                if((i+1) == (int)Math.round(tLineNum) ){
                    workers.get(i).draw1(g);                                                        
                    tLineNum += lineNum;
                }
            }
        }
        if(terrain != null){                                                                        //if terrain is not null draw all obstaclkles                                                    
            terrain.draw(g);
        }


    }

    

    @Override
    public void actionPerformed(ActionEvent e) {

        if(antLeader != null){                                                                      // if ant leader was created and workers list is null, create workers list
            if(workers == null){
                workers = new ArrayList<Ant_worker>();
            }
            if(terrain == null){
                terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 30, 200, 50);    //creating obsticle map
            }
        }


        if(tickDistance>20){                                                             // counter, every 20 loops add new ant
            tickDistance = 0;
            if(workers != null){                                                                    //  only when workers list isnt empty
                if(workers.size() < max_number_of_ants){
                    int colorNum = Const.startColorNumber - (workers.size()+1)*colorChange;         //counts color line color phase
                    workers.add(new Ant_worker(anthill, new Color(colorNum,colorNum,colorNum)));    // add ant 
                }
                    
            }
        }
        tickDistance++;

        if(workers != null){                                                                        // if worker list is not null calls simulate method
            for(int i = 0; i < workers.size(); i++){
                if(i == 0){
                    workers.get(i).simulate(food_source, antLeader.current_position(), terrain);    // if its first ant, it follows leader
                }
                else{
                    end = workers.get(i).simulate(food_source, workers.get(i-1).current_position(), terrain);   //if it is not first ant, it follows previous ant
                }
            }
        }

        if(antLeader != null){
            antLeader.simulate(food_source, terrain);                                               //if leader exists calls method simulate 
        }

        int realTick = 1000-tick;                                                                   //for changing tick value to relevant variable for setDealy method
        simulationLoop.setDelay(realTick);                             
     
        repaint();

        if(end)                                                                                     // esimulations end activities
        {
            System.out.println("end");                                                         
            simulationLoop.stop();
            JOptionPane.showMessageDialog(this, "simulation has ended");                    // popup dialog window
        }

    }
    

                                                                                                
                                                                                                    // all of setters
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
        antLeader = new Ant_leader(anthill, leader_angle, Color.white);
    }
    public void set_foodSource(int x, int y){
        food_source = new Point(x,y);
    }

}
