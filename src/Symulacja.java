public class Symulacja {
    public static void main(String[] args) throws Exception {

        short max_number_of_ants;   //min 10
        short number_of_ants;       //holds current number of ants
        short board_size;           //min 100, max 1000
        byte tick = 0;              //counts how many ticks passed since last ant was created
        boolean end = false;        //ends simulation
        Point anthill;              //for user to decide position
        Point food_source;          //for user to decide position

        while(!end){
            if(tick == 5 && number_of_ants < max_number_of_ants){
                //create new ant_worker
                tick = 1;
            }
            else
                tick++;

            for(int i = number_of_ants; i > 0; i++){
                //runs simulation of every ant, from last to first
            }
            //if every ant simulation return true -> simulation ends
            
        }
    }
}
