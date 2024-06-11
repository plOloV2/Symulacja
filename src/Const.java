import java.awt.Color;

public class Const {
    //for Simulation class
        //board properties
            public static int boardHeight = 500;
            public static int boardWidth = 500;

    //for Board class
    public static int singlePanelHeight = 50;                               // single panel height for exaple for slider panel height or text panel height
    public static int lowestCoord = 30;                                     // lowest coordinate for anthill and food source
    public static int highestCoord = 450;                                   // highest coordinate for anthill and food source

    public static int numOfLettersToCut1 = 12;                              // number of letters to cut from text label "foodSource" when you type coordinates
    public static int numOfLettersToCut2 = 9;                               // number of letters to cut from text label "anthill" when you type coordinates

    //for SimulationEngine class
    public static Color backgroundcolor = new Color(0,102,0);               //color object for background color numbers matches green color
    public static int startColorNumber = 215;                               // number of first color witch represents grey-white color                                    
    public static int colorRangeNumber = 205;                               // color Range number  
    public static int fSPhotoSpacing = 25;                          
    public static int aHPhotoSpacing = 50;
    
    //for Ant class
    public static int antPhotoSpacing = 16;                                 // for drawing ant photo at its center
    public static int maxRandomDistance = 20;                               // for creating random directions
    
    //for Ant_Leader class
    public static int mapPadding = 10;                                      // for keping space away from border
}
