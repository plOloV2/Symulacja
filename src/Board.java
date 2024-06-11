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
        this.setSize(boardWidth, boardHeight+(Const.singlePanelHeight*3));
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

        panel.setSize(boardWidth, Const.singlePanelHeight*3);

        panel0.setSize(boardWidth, Const.singlePanelHeight);
        panel0.setLayout(new GridLayout()); 
        panel0.add(button);
        panel0.add(coordinatesAntHillX);
        panel0.add(coordinatesAntHillY);
        panel0.add(coordinatesFoodSourceX);
        panel0.add(coordinatesFoodSourceY);

        
        panel1.add(name1);
        panel1.add(name2);
        panel1.add(name3);
        panel1.add(name4);

        name1.setHorizontalAlignment(SwingConstants.CENTER);
        name2.setHorizontalAlignment(SwingConstants.CENTER);
        name3.setHorizontalAlignment(SwingConstants.CENTER);
        name4.setHorizontalAlignment(SwingConstants.CENTER);

        panel1.setSize(boardWidth, Const.singlePanelHeight);
        panel1.setLayout(new GridLayout());   


        panel2.add(sliderTicks);
        panel2.add(sliderNumOfAnts);
        panel2.add(sliderLeadAngle);
        panel2.add(sliderNumOfLines);
        
        panel2.setSize(boardWidth, Const.singlePanelHeight);
        panel2.setLayout(new GridLayout());


        panel3.add(text1);
        panel3.add(text2);
        panel3.add(text3);
        panel3.add(text4);

        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text3.setHorizontalAlignment(SwingConstants.CENTER);
        text4.setHorizontalAlignment(SwingConstants.CENTER);

        panel3.setSize(boardWidth, Const.singlePanelHeight);
        panel3.setLayout(new GridLayout());

        setSliders();
        applyChanges();
        //this.pack();
    }


    private void applyChanges(){

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        antHillX = Integer.parseInt(coordinatesAntHillX.getText().substring(Const.numOfLettersToCut1));
                        antHillY = Integer.parseInt(coordinatesAntHillY.getText().substring(Const.numOfLettersToCut1));
                        foodSourceX = Integer.parseInt(coordinatesFoodSourceX.getText().substring(Const.numOfLettersToCut2));
                        foodSourceY = Integer.parseInt(coordinatesFoodSourceY.getText().substring(Const.numOfLettersToCut2));

                        if( antHillX < Const.lowestCoord || antHillY < Const.lowestCoord || 
                            antHillX > Const.highestCoord || antHillY > Const.highestCoord || 
                            foodSourceX < Const.lowestCoord || foodSourceY < Const.lowestCoord || 
                            foodSourceX > Const.highestCoord || foodSourceY > Const.highestCoord)
                            JOptionPane.showMessageDialog(Board.this, "Coordinates X and Y must be gater than 30 and lesser than 450");
                    
                        else{
                            simulationEngine.set_number_of_ants(sliderNumOfAnts.getValue());
                            simulationEngine.set_leader_angle(sliderLeadAngle.getValue());
                            simulationEngine.set_antHill(antHillX, antHillY);
                            simulationEngine.set_number_of_lines(sliderNumOfLines.getValue());
                            simulationEngine.set_foodSource(foodSourceX, foodSourceY);
                            button.setEnabled(false);
                            sliderNumOfAnts.setEnabled(false);
                            sliderLeadAngle.setEnabled(false);
                            sliderNumOfLines.setEnabled(false);
                            coordinatesAntHillX.setEnabled(false);
                            coordinatesAntHillY.setEnabled(false);
                            coordinatesFoodSourceX.setEnabled(false);
                            coordinatesFoodSourceY.setEnabled(false);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Board.this, "Coordinates are incorect!");         // popup dialog window

                        System.out.println("Niepoprawny format liczby "+coordinatesAntHillX.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesAntHillY.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceX.getText());
                        System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceY.getText());
                    }
                }     
        });
        
    }

    private void setSliders(){
        getSliderValueTick(sliderTicks,text1,simulationEngine);
        getSliderValue(sliderNumOfAnts,text2);
        getSliderValue(sliderLeadAngle,text3);
        getSliderValue(sliderNumOfLines,text4);
    }

    private SimulationEngine simulationEngine;

    private int boardHeight;
    private int boardWidth;

    private Integer antHillX;
    private Integer antHillY;
    private Integer foodSourceX;
    private Integer foodSourceY;
    

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


    
    private JSlider sliderTicks = new JSlider(JSlider.HORIZONTAL, 600, 1000, 800);
    private JSlider sliderNumOfAnts = new JSlider(JSlider.HORIZONTAL, 10, 40, 25);
    private JSlider sliderLeadAngle = new JSlider(JSlider.HORIZONTAL, 15, 90, 60);
    private JSlider sliderNumOfLines = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);


    private JLabel text1 = new JLabel("800");
    private JLabel text2 = new JLabel("25");
    private JLabel text3 = new JLabel("60");
    private JLabel text4 = new JLabel("1");

    private JLabel name1 = new JLabel("Ticks");
    private JLabel name2 = new JLabel("num. of ants");
    private JLabel name3 = new JLabel("Leader wiggle angle");
    private JLabel name4 = new JLabel("num. of lines");




    public static void getSliderValue(JSlider slider, JLabel text){
        
        slider.addChangeListener(new ChangeListener() {
           
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                text.setText(String.valueOf(value));
            }
            
        });
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
