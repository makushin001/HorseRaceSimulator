import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class StartRace {
    public static void main(String[] args) {
        int raceTrackDistance = input_int_in_bounds("Please enter distance of the Race Track: ", 1, 100);
        int numHorses = input_int_in_bounds("Please enter number of horses: ", 0, 10);

        Race race = new Race(raceTrackDistance, numHorses);
        ArrayList<Horse> horsesToAdd = new ArrayList<>();
        LinkedHashSet<Integer> laneNumbers = new LinkedHashSet<>();

        setUpRace(numHorses, horsesToAdd, laneNumbers);

        System.out.println(laneNumbers);

        if (race.addHorse(horsesToAdd, laneNumbers)){
            boolean areStillRacing = true;
            while(areStillRacing){
                race.startRace();
                areStillRacing = has_finished("Do you want to continue racing? (y/n)");
            }
        }else{
            System.out.println("CANNOT RACE");
        }
    }

    private static void setUpRace(int numHorses, ArrayList<Horse> horsesToAdd, LinkedHashSet<Integer> laneNumbers){
        for (int i=0; i<numHorses; i++){
            String name = input_str("Please enter horse name");
            double confidence = input_double_in_bounds("Please enter horse confidence level", 0, 1.0);

            char symbol = chooseHorse();
            horsesToAdd.add(new Horse(symbol, name, confidence));
            int lane;
            do{
                lane = input_int_in_bounds("Please enter lane number", 1, numHorses);
            }while(laneNumbers.contains(lane));
            laneNumbers.add(lane);
            System.out.println();
        }
    }

    public static char chooseHorse(){
        int horseNum = input_int_in_bounds("Please choose horse 1) '\u2659' 2) '\u265A' 3) '\u2764' 4) '\u2658' 5) '\u265E'", 1, 5);
        if (horseNum == 1){
            return '\u2659';
        }else if (horseNum == 2){
            return '\u265A';
        }else if (horseNum == 3){
            return '\u2764';
        }else if (horseNum == 4){
            return '\u2658';
        }else{
            return '\u265E';
        }
    }

    private static String input_str(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String response = scanner.nextLine();
        return response;
    }
    
    private static boolean has_finished(String message){
        boolean is_correct_input = false;
        while (!is_correct_input){
            String response = input_str(message);
            if (response.equals("y") || response.equals("Y")){
                is_correct_input = true;
                return true;
            }else if (response.equals("n") || response.equals("N")){
                is_correct_input = true;
                return false;
            }else{
                is_correct_input = false;
                System.out.println("Please try again, enter (y/n)...");
            }
        }
        return false;
    }

    public static int input_int(String message){
        boolean is_int = false; 
        int answer = 0;
        while (!is_int){
            try{
                String str_answer = input_str(message);
                answer = Integer.parseInt(str_answer);
                is_int = true;
            }catch(Exception e){
                System.out.println("Please try again... enter integer values only(E.g 1, 60, 320)");
                is_int = false;
            }
        }
        return answer;
    }

    public static int input_int_in_bounds(String message, int lower_bound, int upper_bound){
        boolean is_in_bounds = false;
        int answer = 0;
        while (!is_in_bounds){
            int value = input_int(message);
            if ((value >= lower_bound)&&(value <= upper_bound)){
                is_in_bounds = true;
                answer = value;
            }else {
                System.out.println("Please try again... The value should be between " + lower_bound + " to " +upper_bound);
                is_in_bounds = false;
            }
        }
        return answer; 
    }


    public static double input_double(String message){
        boolean is_double = false; 
        double answer = 0;
        while (!is_double){
            try{
                String str_answer = input_str(message);
                answer = Double.parseDouble(str_answer);
                is_double = true;
            }catch(Exception e){
                System.out.println("Please try again... enter double values only(E.g 1.0, 60.5, 320.2)");
                is_double = false;
            }
        }
        return answer;
    }
    
    public static double input_double_in_bounds(String message, double lower_bound, double upper_bound){
        boolean is_in_bounds = false;
        double answer = 0;
        while (!is_in_bounds){
            double value = input_double(message);
            if ((value >= lower_bound)&&(value <= upper_bound)){
                is_in_bounds = true;
                answer = value;
            }else {
                System.out.println("Please try again... The value should be between " + lower_bound + " to " +upper_bound);
                is_in_bounds = false;
            }
        }
        return answer; 
    }
}


















       // horsesToAdd.add(new Horse('\u2658', "Aleksei", 0.5));
        // horsesToAdd.add(new Horse('\u265E', "Saha", 0.6));
        // horsesToAdd.add(new Horse('\u2764', "Tema", 0.7));
        // horsesToAdd.add(new Horse('\u2658', "Aleksei", 0.5));
        // horsesToAdd.add(new Horse('\u265E', "Saha", 0.6));
        // Arrays.asList(3, 2, 1, 4, 5)



        // Horse horse = new Horse('\u2658', "Pikachu", 0.5);

        // System.out.println("Name: " + horse.getName());
        // System.out.println("Confidence Level: " + horse.getConfidence());
        // System.out.println("Distence Travelled: " + horse.getDistanceTravelled());
        // System.out.println("Symbol: " + horse.getSymbol());
        // System.out.println("Has horse fallen: " + horse.hasFallen());
        // System.out.println();

        // System.out.println("Perform some actions:");
        // System.out.println();

        
        // horse.fall();
        // System.out.println("Has horse fallen: " + horse.hasFallen());
        // System.out.println();

        // horse.moveForward();
        // horse.moveForward();
        // horse.moveForward();
        // System.out.println("Distence Travelled: " + horse.getDistanceTravelled());
        // System.out.println();

        // horse.goBackToStart();
        // System.out.println("Distence Travelled: " + horse.getDistanceTravelled());
        // System.out.println();

        // horse.setConfidence(0.7);
        // System.out.println("Confidence Level: " + horse.getConfidence());
        // System.out.println();

        // horse.setSymbol('\u2764');
        // System.out.println("Symbol: " + horse.getSymbol());