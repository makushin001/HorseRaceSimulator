import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class Race {
    private int raceLength;
    private int num_horses;
    private ArrayList<Horse> horse_list;
    private Horse winner_horse;

    public Race(int distance, int num_horses) {
        this.raceLength = distance;
        this.num_horses = num_horses;
        horse_list = new ArrayList<>(Collections.nCopies(num_horses, null));
    }

    public boolean addHorse(ArrayList<Horse> horses_array, LinkedHashSet<Integer> corresponding_lane_set) {
        if (are_same_size(horses_array, corresponding_lane_set) && (horses_array.size() == num_horses)){
            ArrayList<Integer> laneList = new ArrayList<>(corresponding_lane_set);
            for (int i = 0; i < horses_array.size(); i++) {
                int laneIndex = laneList.get(i) - 1;
                if (laneIndex >= 0 && laneIndex < horses_array.size()) {
                    horse_list.set(laneIndex, horses_array.get(i));
                    horses_array.get(i).setHorseLane((laneIndex+1));
                } else {
                    System.out.println("Invalid lane number: " + laneList.get(i));
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("Cannot add all horses as the number of lanes and the number of horses do not match! The lanes should be unique and cannot be the same.");
            return false;
        }
    }
    
    private boolean are_same_size(ArrayList<Horse> horses_array, LinkedHashSet<Integer> corresponding_lane_set){
        return horses_array.size() == corresponding_lane_set.size();
    }

    private void resetHorsesBackToStart(){
        for (Horse horse: this.horse_list){
            horse.reset();
        }
    }

    private boolean haveAllHorsesFallen(){
        for (Horse horse: this.horse_list){
            if (!horse.hasFallen()){
                return false;
            }
        }
        return true;
    }

    public void moveHorses(){
        for (Horse horse: horse_list){
            moveHorse(horse);
        }
    }

    public void printHorces(){
        for (Horse horse : horse_list){
            System.out.println(horse.getName() + " is in lane " + horse.getHorseLane()+ " and has a confidence of (" + horse.getConfidence()+ ")" );
        }
    }

    public void alterHorseConfidence(){
        for (Horse horse: horse_list){
            if (horse.hasFallen()){
                horse.decreaseConfidence();
            }else if(horse.equals(winner_horse)){
                horse.increaseConfidence();
            }
        }
    }
    
    public void startRace(){
        //declare a local variable to tell us when the race is finished
        boolean finished = false;

        //reset all the lanes (all horses not fallen and back to 0). 
        resetHorsesBackToStart();

        printHorces();
                      
        while (!finished){
            //move each horse
            moveHorses();
                        
            //print the race positions
            printRace();
            
            //if any of the three horses has won the race is finished
            for (Horse horse: horse_list){
                if (raceWonBy(horse)){
                    finished = true;
                }
            }
            if (haveAllHorsesFallen()){
                finished = true;
            }

           
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }
        alterHorseConfidence();
        String finishText = (winner_horse == null) ? "The race is VOID, none of the horses came to finish." : "And the winner of the race is " + this.winner_horse.getName();
        System.out.println(finishText);
    }

    private void moveHorse(Horse theHorse)
    {
        if  (!theHorse.hasFallen())
        {
            if (Math.random() < theHorse.getConfidence()){
               theHorse.moveForward();
            }

            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse.getDistanceTravelled() >= raceLength){
            this.winner_horse = theHorse;
            return true;
        }else{
            return false;
        }
    }
    
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();


        for (Horse horse : horse_list){
            printLane(horse);
            System.out.println();
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
        System.out.println(theHorse.getName() + " is in lane " + theHorse.getHorseLane()+ " and has a confidence of (" + theHorse.getConfidence()+ ")" );
    }
    
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}




// Race race1 = new Race(15, 3);
// ArrayList<Horse> horsesToAdd1 = new ArrayList<>();
// horsesToAdd1.add(new Horse('\u2658', "Aleksei", 0.5));
// horsesToAdd1.add(new Horse('\u265E', "Saha", 0.6));
// horsesToAdd1.add(new Horse('\u2764', "Tema", 0.7));
// LinkedHashSet<Integer> laneNumbers1 = new LinkedHashSet<>(Arrays.asList(3, 2));
// System.out.println("Are same size ? " + race1.are_same_size(horsesToAdd1, laneNumbers1)); //false
// System.out.println("Can we add horces ?" + race1.addHorse(horsesToAdd1, laneNumbers1)); //false
// System.out.println();



// Race race2 = new Race(15, 3);
// ArrayList<Horse> horsesToAdd2 = new ArrayList<>();
// horsesToAdd2.add(new Horse('\u2658', "Aleksei", 0.5));
// horsesToAdd2.add(new Horse('\u265E', "Saha", 0.6));
// horsesToAdd2.add(new Horse('\u2764', "Tema", 0.7));
// LinkedHashSet<Integer> laneNumbers2 = new LinkedHashSet<>(Arrays.asList(3, 2, 1));
// System.out.println("Are same size ? " + race2.are_same_size(horsesToAdd2, laneNumbers2)); //true
// System.out.println("Can we add horce ? " + race2.addHorse(horsesToAdd2, laneNumbers2)); //true
// System.out.println();


// Race race3 = new Race(15, 2);
// ArrayList<Horse> horsesToAdd3 = new ArrayList<>();
// horsesToAdd3.add(new Horse('\u2658', "Aleksei", 0.5));
// horsesToAdd3.add(new Horse('\u265E', "Saha", 0.6));
// horsesToAdd3.add(new Horse('\u2764', "Tema", 0.7));
// LinkedHashSet<Integer> laneNumbers3 = new LinkedHashSet<>(Arrays.asList(3, 2, 1));
// System.out.println("Are same size ? " + race3.are_same_size(horsesToAdd3, laneNumbers3)); //true
// System.out.println("Can we add horce ? " +race3.addHorse(horsesToAdd3, laneNumbers3)); //true
// System.out.println();


// Are same size ? false
// Cannot add all horses as the number of lanes and the number of horses do not match! The lanes should be unique and cannot be the same.
// Can we add horces ?false

// Are same size ? true
// Can we add horce ? true

// Are same size ? true
// Cannot add all horses as the number of lanes and the number of horses do not match! The lanes should be unique and cannot be the same.
// Can we add horce ? false
