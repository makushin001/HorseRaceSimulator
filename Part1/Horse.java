public class Horse{
    private String name;
    private char symbol;
    private int dist_travelled;
    private boolean has_fallen;
    private double conf_rating;
    private int horse_lane = 0;
    
    public Horse(char horseSymbol, String horseName, double horseConfidence){
        this.name = horseName;
        this.symbol = horseSymbol;
        this.conf_rating = ckeck_value_in_range(horseConfidence, 1.0, 0)==true ? horseConfidence: 0.1;
        this.has_fallen = false;
        this.dist_travelled = 0;
    }

    public int getHorseLane(){
        return this.horse_lane;
    }

    public void setHorseLane(int lane){
        this.horse_lane = lane;
    }

    private boolean ckeck_value_in_range(double value, double top, double bottom){
        return ((value <=top) && (value >= bottom));
    }

    public void decreaseConfidence(){
        if ((this.conf_rating - 0.1) < 0){
            this.conf_rating = 0;
        }else{
            this.conf_rating -= 0.1;
        }
    }
    public void increaseConfidence(){
        if ((this.conf_rating + 0.1) > 1){
            this.conf_rating = 1;
        }else{
            this.conf_rating += 0.1;
        }
    }

    public void reset(){
        this.has_fallen = false;
        this.dist_travelled = 0;
    }
    
    public void fall(){
        this.has_fallen = true;
    }
    
    public double getConfidence(){
        return this.conf_rating;
    }
    
    public int getDistanceTravelled(){
        return dist_travelled;
    }
    
    public String getName(){
        return this.name;
    }
    
    public char getSymbol(){
        return this.symbol;
    }
    
    public void goBackToStart(){
        dist_travelled = 0;
    }
    
    public boolean hasFallen(){
        return this.has_fallen;
    }

    public void moveForward(){
        this.dist_travelled ++;
    }

    public void setConfidence(double newConfidence){
        this.conf_rating = newConfidence;
    }
    
    public void setSymbol(char newSymbol){
        this.symbol = newSymbol;
    }
}

// //Test of the decreaseConfidence() method:
// Horse horse1 = new Horse('\u2654', "Pikachu", 0.1);
// System.out.println("Confidence: "+ horse1.getConfidence());
// horse1.decreaseConfidence();
// System.out.println("Confidence: "+ horse1.getConfidence());
// horse1.decreaseConfidence();
// System.out.println("Confidence " + horse1.getConfidence());
// System.out.println();


// //Test of the increaseConfidence() method:
// Horse horse2 = new Horse('\u2654', "Pikachu", 0.9);
// System.out.println("Confidence: "+ horse2.getConfidence());
// horse2.increaseConfidence();
// System.out.println("Confidence: "+ horse2.getConfidence());
// horse2.increaseConfidence();
// System.out.println("Confidence " + horse2.getConfidence());
// System.out.println();

// //Test of the reset() method:
// Horse horse3 = new Horse('\u2654', "Pikachu", 0.9);
// horse3.fall();
// horse3.moveForward();
// horse3.moveForward();
// horse3.moveForward();
// System.out.println("Has Fallen: "+ horse3.hasFallen());
// System.out.println("Distance " + horse3.getDistanceTravelled());
// horse3.reset();
// System.out.println("Has Fallen: "+ horse3.hasFallen());
// System.out.println("Distance " + horse3.getDistanceTravelled());
// System.out.println();


// //Test of the getHorseLane() and setHorseLane(int lane) methods:
// Horse horse4 = new Horse('\u2654', "Pikachu", 0.9);
// System.out.println("Horse Lane: "+ horse4.getHorseLane());
// horse4.setHorseLane(2);
// System.out.println("Horse Lane: "+ horse4.getHorseLane());
// System.out.println();



