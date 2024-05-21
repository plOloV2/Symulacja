import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationEngine extends JPanel implements ActionListener {
    //panel size
    private int boardHeight;
    private int boardWidth;

    short max_number_of_ants;   //min 10
    int number_of_ants;         //holds current number of ants
    short board_size;           //min 100, max 1000
    int tick = 0;               //counts how smany ticks passed since last ant was created
    int leader_angle;           //describes max angle or leaders path change
    boolean end = false;        //ends simulation
    Point anthill;              //for user to decide position
    Point food_source;          //for user to decide position
    

    //objects
    Ant_lider antLeader = new Ant_lider(anthill, leader_angle);

    // simulation logic
    Timer gameloop;
    


    SimulationEngine(int boardWidth, int boardHeight, int tick, int number_of_ants, int leader_angle){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.BLACK);
        anthill= new Point(20,250);   
        food_source = new Point(420, 250);
        this.number_of_ants = number_of_ants;
        this.tick = tick;
        this.leader_angle = leader_angle;

        gameloop = new Timer(1000-tick, this);
    }

    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    int i = 0;

    public void draw(Graphics g){
        if(i%2==0){
            g.setColor(new Color(102,51,0));
            g.fillOval(anthill.X_pos(), anthill.Y_pos(), 40, 40);
            g.setColor(new Color(0,204,0));
            g.fillOval(food_source.X_pos(), food_source.Y_pos(), 40, 40);
            i++;
        }
        i++;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        antLeader.symulate(food_source);
    }
    


            // while(!end){
        //     if(tick == 5 && number_of_ants < max_number_of_ants){
        //         //create new ant_worker
        //         tick = 1;
        //     }
        //     else
        //         tick++;

        //     for(int i = number_of_ants; i > 0; i++){
        //         //runs simulation of every ant, from last to first
        //     }
        //     //if every ant simulation return true -> simulation ends
            
        // }
    
}
