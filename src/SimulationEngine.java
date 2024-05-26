import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
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
    private Point food_source;          //for user to decide position
    private Random random = new Random();
    private int tX;
    private int tY;
    

    //objects
    private Ant_leader antLeader;
    // antLeader = new Ant_leader(anthill, leader_angle, boardHeight, boardHeight);
    private Obstackle terrain;
    // terrain = new Obstackle(anthill, food_source, boardHeight, boardWidth, 5, 10, 5);
    private ArrayList<Ant_worker> workers;

    // simulation logic
    Timer gameloop;


    SimulationEngine(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.BLACK);
        anthill= new Point(40,400);   
        food_source = new Point(400, 400);
        //this.max_number_of_ants = number_of_ants;
        //this.tick = tick;
        //this.leader_angle = leader_angle;

        gameloop = new Timer(1000-tick, this);
        gameloop.start();
    }

    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private BufferedImage ant_hillImage;
    private BufferedImage food_sourceImage;

    public void import_images(){
        try {
            ant_hillImage = ImageIO.read(new File("images/ant_hill.png"));
        } catch (IOException ez) {
            System.out.println("Brak ant_hill.png");
        }
        try {
            food_sourceImage = ImageIO.read(new File("images/food_source.png"));
        } catch (IOException ew) {
            System.out.println("Brak food_source.png");
        }
    }

    int i = 0;

    public void draw(Graphics g){
        if(i%2==0){
            if(ant_hillImage != null)
                g.drawImage(ant_hillImage, anthill.X_pos(), anthill.Y_pos(), null);
            else{
                g.setColor(new Color(102,51,0));
                g.fillOval(anthill.X_pos(), anthill.Y_pos(), 40, 40);
            }
            if(food_sourceImage != null)
                g.drawImage(food_sourceImage, food_source.X_pos(), food_source.Y_pos(), null);
            else{
                g.setColor(new Color(0,204,0));
                g.fillOval(food_source.X_pos(), food_source.Y_pos(), 40, 40);
            }
            i++;
        }
        i++;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
        if(tick == 3){
            if(workers.size() < max_number_of_ants)
                workers.add(new Ant_worker(anthill));
            
            tick = 1;
        }
        else 
            tick ++;

        end = antLeader.simulate(food_source);

        if(workers.size() > 0)
            end = workers.get(0).simulate(food_source, antLeader.current_position());

        for(int i = 1; i < workers.size(); i++)
            end = workers.get(i).simulate(food_source, workers.get(i-1).current_position());
        
            
        //if (!end) koniec symulacji
    }



    public void get_tick(int tick){
        this.tick = tick;
    }
    public void get_number_of_ants(int number_of_ants){
        this.max_number_of_ants = number_of_ants;
    }
    public void get_leader_angle(int leader_angle){
        this.leader_angle = leader_angle;
    }
    public void get_antHillX(int x){
        anthill.give_X_pos(x);
    }
    public void get_antHillY(int y){
        anthill.give_X_pos(y);
    }
    
    public void get_foodSourceX(int x){
        food_source.give_X_pos(x);
    }
    public void get_foodSourceY(int y){
        food_source.give_Y_pos(y);
    }

}
