import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Board extends JFrame{

    Board(int boardHeight, int boardWidth, String nazwa, SimulationEngine simulationEngine){
        super(nazwa);
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.simulationEngine = simulationEngine;
        initComponents();
    }

    private void initComponents(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(boardWidth, boardHeight+150);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(simulationEngine, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.setVisible(true);

        panel.setLayout(new GridLayout(4,1));
        panel.add(panel0);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        panel.setSize(boardWidth, 200);

        panel0.setSize(boardWidth, 50);
        panel0.setLayout(new GridLayout()); 
        panel0.add(button);
        panel0.add(coordinatesAntHillX);
        panel0.add(coordinatesAntHillY);
        panel0.add(coordinatesFoodSourceX);
        panel0.add(coordinatesFoodSourceY);

        
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

        //this.pack();

        applyChanges();
    }


    private void applyChanges(){
        
        getSliderValueTick(sliderTicks,text1,simulationEngine);
        number_of_ants = getSliderValue(sliderNumOfAnts,text2);
        leader_angle = getSliderValue(sliderLeadAngle,text3);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        antHillX = Integer.parseInt(coordinatesAntHillX.getText().substring(12));
                        antHillY = Integer.parseInt(coordinatesAntHillY.getText().substring(12));
                        foodSourceX = Integer.parseInt(coordinatesFoodSourceX.getText().substring(9));
                        foodSourceY = Integer.parseInt(coordinatesFoodSourceY.getText().substring(9));

                        if( antHillX < 0 || antHillY < 0 || antHillX > 450 || antHillY > 450 || foodSourceX < 0 || foodSourceY < 0 || foodSourceX > 450 || foodSourceY > 450)
                            JOptionPane.showMessageDialog(Board.this, "Coordinates X and Y must be gater than 0 and lesser than 450");
                    
                        else{
                            simulationEngine.set_number_of_ants(number_of_ants);
                            simulationEngine.set_leader_angle(leader_angle);
                            simulationEngine.set_antHill(antHillX, antHillY);
                            simulationEngine.set_foodSource(foodSourceX, foodSourceY);
                            button.setEnabled(false);
                            sliderNumOfAnts.setEnabled(false);
                            sliderLeadAngle.setEnabled(false);
                            coordinatesAntHillX.setEnabled(false);
                            coordinatesAntHillY.setEnabled(false);
                            coordinatesFoodSourceX.setEnabled(false);
                            coordinatesFoodSourceY.setEnabled(false);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Board.this, "Coordinates are incorect!"); //nie wywołuje sie

                        System.out.println("Niepoprawny format liczby "+coordinatesAntHillX.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesAntHillY.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceX.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceY.getText());
                    }
                }     
        });
        
    }

    private SimulationEngine simulationEngine;

    private int boardHeight;
    private int boardWidth;

    private Integer antHillX = 100;
    private Integer antHillY = 400;
    private Integer foodSourceX = 50;
    private Integer foodSourceY = 400;

    private int number_of_ants = 0;
    private int leader_angle = 0;
    

    private JButton button = new JButton("Start");

    private JTextField coordinatesAntHillX = new JTextField("Anthill X = ");
    private JTextField coordinatesAntHillY = new JTextField("Anthill Y = ");
    private JTextField coordinatesFoodSourceX = new JTextField("Food X = ");
    private JTextField coordinatesFoodSourceY = new JTextField("Food Y = ");

    private JPanel panel = new JPanel();

    private JPanel panel0 = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();


    
    private JSlider sliderTicks = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
    private JSlider sliderNumOfAnts = new JSlider(JSlider.HORIZONTAL, 10, 100, 45);
    private JSlider sliderLeadAngle = new JSlider(JSlider.HORIZONTAL, 15, 90, 60);


    private JLabel text1 = new JLabel("500");
    private JLabel text2 = new JLabel("45");
    private JLabel text3 = new JLabel("60");

    private JLabel name1 = new JLabel("Ticks");
    private JLabel name2 = new JLabel("num. of ants");
    private JLabel name3 = new JLabel("Leader wiggle angle");




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
    public static void getSliderValueTick(JSlider slider, JLabel text, SimulationEngine simulationEngine){
        
        slider.addChangeListener(new ChangeListener() {
           
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                text.setText(String.valueOf(value));
                simulationEngine.set_tick(value);
            }
            
        });
    }

}
