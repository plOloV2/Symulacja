import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Symulacja {
    int boardHeight = 500;
    int boardWidth = 500;
    Symulacja(){
        
        initComponents();
    }

    public void initComponents(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight+100);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(simulationEngine, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);


        panel.setLayout(new BorderLayout());
        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel0, BorderLayout.SOUTH);

        panel.setSize(boardWidth, 150);
        panel0.setLayout(new BorderLayout());
        panel0.add(panel2,BorderLayout.NORTH);
        panel0.add(panel3,BorderLayout.SOUTH);

        
        panel1.add(name1);
        panel1.add(name2);
        panel1.add(name3);

        name1.setHorizontalAlignment(SwingConstants.CENTER);
        name2.setHorizontalAlignment(SwingConstants.CENTER);
        name3.setHorizontalAlignment(SwingConstants.CENTER);

        panel1.setSize(boardWidth, 50);
        panel1.setLayout(new GridLayout());   


        panel2.add(sliderTicks);
        panel2.add(sliderNumOfAnts);
        panel2.add(sliderLeadAngle);
        
        panel2.setSize(boardWidth, 50);
        panel2.setLayout(new GridLayout());


        panel3.add(text1);
        panel3.add(text2);
        panel3.add(text3);

        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text3.setHorizontalAlignment(SwingConstants.CENTER);

        panel3.setSize(boardWidth, 50);
        panel3.setLayout(new GridLayout());

        //frame.pack();

        tick = getSliderValue(sliderTicks,text1);
        number_of_ants = getSliderValue(sliderNumOfAnts,text2);
        leader_angle = getSliderValue(sliderLeadAngle,text3);

    }

    int tick;
    int number_of_ants;
    int leader_angle;

    JFrame frame = new JFrame("symulacja");


    JPanel panel = new JPanel();

    JPanel panel0 = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();


    
    JSlider sliderTicks = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
    JSlider sliderNumOfAnts = new JSlider(JSlider.HORIZONTAL, 10, 100, 45);
    JSlider sliderLeadAngle = new JSlider(JSlider.HORIZONTAL, 15, 90, 60);


    JLabel text1 = new JLabel("500");
    JLabel text2 = new JLabel("45");
    JLabel text3 = new JLabel("60");

    JLabel name1 = new JLabel("Ticks");
    JLabel name2 = new JLabel("num. of ants");
    JLabel name3 = new JLabel("Leader wiggle angle");


    SimulationEngine simulationEngine = new SimulationEngine(boardWidth, boardHeight, tick, number_of_ants, leader_angle);


    public static int getSliderValue(JSlider slider, JLabel text){
        
        slider.addChangeListener(new ChangeListener() {
           
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                text.setText(String.valueOf(value));
            }
            
        });
        return slider.getValue();
    }

    public static void main(String[] args) throws Exception {
        new Symulacja();
    }
}
