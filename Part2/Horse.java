import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Horse{
    private String name;
    private int dist_travelled;
    private boolean has_fallen;
    private double conf_rating;
    private int horse_lane = 0;
    private JLabel horse_actor = new JLabel();
    private ImageIcon horse_icon;
    
    public Horse(String horseName, int horse_lane, double horseConfidence){
        this.name = horseName;
        this.conf_rating = ckeck_value_in_range(horseConfidence, 1.0, 0)==true ? horseConfidence: 0.1;
        this.has_fallen = false;
        this.dist_travelled = 0;
        setHorseActor(new ImageIcon("img/horse-1.png"));
        this.horse_icon = new ImageIcon("img/horse-1.png");
    }

    public Horse(String horseName, int horse_lane, double horseConfidence, ImageIcon icon){
        this.name = horseName;
        this.conf_rating = ckeck_value_in_range(horseConfidence, 1.0, 0)==true ? horseConfidence: 0.5;
        this.has_fallen = false;
        this.dist_travelled = 0;
        setHorseActor(icon);
        this.horse_icon = icon;
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
        this.horse_actor.setLocation(0, this.horse_actor.getLocation().y);
        this.setDefaultIcon();
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public void goBackToStart(){
        dist_travelled = 0;
        Point current_pos = this.horse_actor.getLocation();
        this.horse_actor.setLocation(0, current_pos.y);
    }
    
    public boolean hasFallen(){
        return this.has_fallen;
    }

    public void moveForward(){
        Point current_pos = this.horse_actor.getLocation();
        this.horse_actor.setLocation(current_pos.x + 5, current_pos.y);
        this.dist_travelled += 5;
    }

    public void setConfidence(double newConfidence){
        this.conf_rating = newConfidence;
    }

    public void setHorseActor(ImageIcon icon){
        this.horse_actor.setIcon(icon);
        this.horse_actor.setSize(100, 100);
    }
  
    public JLabel getHorseActor(){
        return this.horse_actor;
    }

    public void setDefaultIcon(){
        this.horse_actor.setIcon(this.horse_icon);
    }

    public void setIcon(ImageIcon icon){
        this.horse_actor.setIcon(icon);
        this.horse_icon = icon;
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



