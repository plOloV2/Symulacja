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
    int boardHeight;
    int boardWidth;

    Board(int boardHeight, int boardWidth,String nazwa){
        super(nazwa);
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        initComponents();
    }

    public void initComponents(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(boardWidth, boardHeight+150);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
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

        tick = getSliderValue(sliderTicks,text1);
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
                    System.out.println("Wartość int: " + antHillX);
                    System.out.println("Wartość int: " + antHillY);
                    System.out.println("Wartość int: " + foodSourceX);
                    System.out.println("Wartość int: " + foodSourceY);
                    button.setEnabled(false);
                    sliderNumOfAnts.setEnabled(false);
                    sliderLeadAngle.setEnabled(false);
                    coordinatesAntHillX.setEnabled(false);
                    coordinatesAntHillY.setEnabled(false);
                    coordinatesFoodSourceX.setEnabled(false);
                    coordinatesFoodSourceY.setEnabled(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Board.this, "Coordinates are incorect!");
                    
                    System.out.println("Niepoprawny format liczby "+coordinatesAntHillX.getText());
                    System.out.println("Niepoprawny format liczby "+coordinatesAntHillY.getText());
                    System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceX.getText());
                    System.out.println("Niepoprawny format liczby "+coordinatesFoodSourceY.getText());
                }

               
            }
        });

    }

    private Integer antHillX;
    private Integer antHillY;
    private Integer foodSourceX;
    private Integer foodSourceY;

    private int tick;
    private int number_of_ants;
    private int leader_angle;
    

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


    public int return_tick(){
        return tick;
    }
    public int return_number_of_ants(){
        return number_of_ants;
    }
    public int return_leader_angle(){
        return leader_angle;
    }


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
}
