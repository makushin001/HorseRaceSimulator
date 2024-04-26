import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;


public class GUI extends JFrame {
    private int raceLength;
    private int num_horses = 2;
    private ArrayList<Horse> horse_list;
    final int track_height = 100;
    private int total_height;
    private int total_width;
    private Horse winner_horse;
    private JLabel footer_text;
    public boolean newRace = false; 

    public GUI(int distance, int number){
        super("Horse Race");
        this.raceLength = distance;
        this.num_horses = number;
        this.total_height = num_horses * track_height+300;
        this.total_width = distance;
        this.horse_list = new ArrayList<>();


        Font font = new Font("Futura", Font.BOLD, 16);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);

        Color darkBlue = new Color(0, 51, 102);
        Color lightBlue = new Color(173, 216, 230); 
        Color red = new Color(255, 0, 0);
        Color white = Color.WHITE;


        
        for (int i = 0; i < num_horses; i++) {
            Horse horse = new Horse("Horse"+(i+1), (i+1),0.7, new ImageIcon("img/horse-1.png"));
            horse.setHorseLane(i+1);
            this.horse_list.add(horse);
        }
        
        setLayout(new BorderLayout());
        this.setSize(total_width*2+400, total_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Race Track");
        this.setResizable(false);


        ArrayList<ImageIcon> img_horses_list = new ArrayList<>();
        img_horses_list.add(new ImageIcon("img/horse-1.png"));
        img_horses_list.add(new ImageIcon("img/horse-2.png"));
        img_horses_list.add(new ImageIcon("img/horse-3.png"));
        img_horses_list.add(new ImageIcon("img/horse-4.png"));
        img_horses_list.add(new ImageIcon("img/horse-5.png"));
        img_horses_list.add(new ImageIcon("img/horse-6.png"));

        Dimension preferredSize = new Dimension(200, 30);
        Border roundedBorder = new LineBorder(Color.BLACK, 1, true);
        Border marginBorder = new EmptyBorder(0, 0, 0, 0);





        // Header
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(this.getWidth(), 200)); 
        header.add(new JLabel("Header"));
        header.setLayout(new BorderLayout());
        header.setBackground(Color.LIGHT_GRAY);


        JPanel panel = new JPanel(new FlowLayout());
        JTextField nameField = new JTextField("Name", 10);
        JTextField confidenceField = new JTextField("0 to 1", 10);
        JTextField lineField = new JTextField(("1 to " +num_horses) , 10);
        JComboBox<Object> horseComboBox = new JComboBox<>(img_horses_list.toArray());
        horseComboBox.setEditable(false); // Set to non-editable
        horseComboBox.setFocusable(false); // Set to non-focusable
        horseComboBox.insertItemAt("Select horse", 0);
        horseComboBox.setSelectedIndex(0);
        JButton submitButton = new JButton("Submit");
        
        nameField.setPreferredSize(preferredSize);
        lineField.setPreferredSize(preferredSize);
        confidenceField.setPreferredSize(preferredSize);
        horseComboBox.setPreferredSize(preferredSize);
        submitButton.setPreferredSize(preferredSize);

        nameField.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        lineField.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        confidenceField.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        horseComboBox.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        submitButton.setBorder(new CompoundBorder(roundedBorder, marginBorder));

        panel.add(nameField);
        panel.add(lineField);
        panel.add(confidenceField);
        panel.add(horseComboBox);
        panel.add(submitButton);

        header.add(panel, BorderLayout.CENTER);
        this.add(header, BorderLayout.NORTH);


        
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton startRaceButton = new JButton("Start Race");
        JButton resetRaceButton = new JButton("Reset Race");


        startRaceButton.setPreferredSize(preferredSize);
        resetRaceButton.setPreferredSize(preferredSize);

        startRaceButton.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        resetRaceButton.setBorder(new CompoundBorder(roundedBorder, marginBorder));
        
        buttonPanel.add(startRaceButton);
        buttonPanel.add(resetRaceButton);

        header.add(buttonPanel, BorderLayout.SOUTH);


        




        // Middle
        JPanel middle = new JPanel(new GridLayout(1, 2, 5, 5));

        JPanel middle_track = new JPanel(new GridLayout(num_horses, 1, 5, 5));
    
        for (int i = 0; i < num_horses; i++) {         
            JPanel lane = new JPanel();
            lane.setBackground(new Color(39, 78, 19));
            lane.setMinimumSize(new Dimension(100, 100)); // Set minimum height to 100px
            lane.setPreferredSize(new Dimension(100, 100));
            lane.setMaximumSize(new Dimension(100, 100));
            lane.setLayout(null);
            lane.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
            lane.add(this.horse_list.get(i).getHorseActor());

            JLabel finish = new JLabel();
            finish.setSize(100, 100);
            finish.setLocation(raceLength, 10);
            finish.setIcon(new ImageIcon("img/finish.png"));
            lane.add(finish);

            lane.repaint();
            middle_track.add(lane);
        }
        middle.add(middle_track);

        JPanel middle_print_box = new JPanel(new GridLayout(num_horses, 1, 5, 5));
        for (int i = 0; i < num_horses; i++) {
            JPanel box = new JPanel();
            box.setBackground(Color.green);
            box.setMinimumSize(new Dimension(100, 100));
            box.setPreferredSize(new Dimension(100, 100));
            box.setMaximumSize(new Dimension(100, 100));
            box.setLayout(new BorderLayout());
            box.setBorder(new MatteBorder(2, 0, 2, 0, Color.BLACK));
            box.setBackground(Color.lightGray);
        
            JLabel text = new JLabel();
            text.setText(this.horse_list.get(i).getName() + " is in lane " + this.horse_list.get(i).getHorseLane() + " and has a confidence of (" + this.horse_list.get(i).getConfidence() + ")");
            box.add(text, BorderLayout.CENTER);
            box.repaint();
            middle_print_box.add(box);
        }
        middle.setBackground(Color.WHITE);
        middle.add(middle_print_box);
        this.add(middle, BorderLayout.CENTER);




        




        // Footer
        JPanel footer = new JPanel();
        footer.setMinimumSize(new Dimension(this.getWidth(), 200)); // Set minimum height to 200px
        footer_text = new JLabel("   ");
        footer.add(footer_text);
        footer.setBackground(Color.LIGHT_GRAY);

        JButton setupButton = new JButton("Setup New Horse Race");
        setupButton.setPreferredSize(new Dimension(300, 35)); // Set preferred button size
        
        footer.add(setupButton);
        this.add(footer, BorderLayout.SOUTH);


        this.add(footer, BorderLayout.SOUTH);
        this.setMinimumSize(new Dimension(raceLength, track_height * num_horses));
        this.setVisible(true);


        header.setBackground(darkBlue);
        middle.setBackground(lightBlue);
        footer.setBackground(darkBlue);
        header.setForeground(white);
        footer_text.setForeground(white);
        startRaceButton.setBackground(red);
        resetRaceButton.setBackground(red);
        Color borderColor = Color.BLACK;
        startRaceButton.setBorder(new CompoundBorder(new LineBorder(borderColor), new EmptyBorder(5, 15, 5, 15)));
        resetRaceButton.setBorder(new CompoundBorder(new LineBorder(borderColor), new EmptyBorder(5, 15, 5, 15)));





        //Event Listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit button clicked" + nameField.getText() + " " + confidenceField.getText() + " " + lineField.getText());
                if (horseComboBox.getSelectedIndex() != 0 && !nameField.getText().equals("Name") && !confidenceField.getText().equals("0 to 1") && !lineField.getText().equals("1 to " + num_horses)){
                    try{
                        LinkedHashSet<Integer> line_set = new LinkedHashSet<>();
                        String name = nameField.getText();
                        int lane = Integer.parseInt(lineField.getText());
                        double confidence = Double.parseDouble(confidenceField.getText());
                        int horseImageIndex = horseComboBox.getSelectedIndex() - 1;
                        ImageIcon horseImage = img_horses_list.get(horseImageIndex);
        
                        if ((confidence >= 0 && confidence <= 1) && (lane >= 1 && lane <= (num_horses+1)) && !line_set.contains(lane)){
                            line_set.add(lane);
                            horse_list.get(lane-1).setName(name);
                            horse_list.get(lane-1).setConfidence(confidence);
                            horse_list.get(lane-1).setIcon(horseImage);
                            horse_list.get(lane-1).setHorseLane(lane);
                            updatePrintBox(middle_print_box);
                        }else{
                            String error_message = "Please enter valid values for the fields:\n Confidence should be between 0 and 1.\n Lane should be between 1 ans " + num_horses + ".\n Lane should be unique.";
                            JOptionPane.showMessageDialog(null, error_message, "Error", JOptionPane.OK_OPTION);
                        }
                        System.out.println("Name: " + name + " Lane: " + lane + " Confidence: " + confidence + " Image: " + horseImageIndex);
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Invalid format, in the 2nd field please eneter values between 1-"+num_horses+" and in the 3rd field enter values between 0-1.", "Error", JOptionPane.OK_OPTION);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter name, line, confidence_level and choose horseImage.", "Error", JOptionPane.OK_OPTION);
                }
            }
        });

        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Race button clicked");
                newRace = true;
                for (Horse horse: horse_list){
                    horse.reset();
                }
                startRace();
                updatePrintBox(middle_print_box);

            }
        });

        resetRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reset Race button clicked");
                nameField.setText("Name");
                confidenceField.setText("0 to 1");
                lineField.setText("1 to " + num_horses);
                horseComboBox.setSelectedIndex(0);
            }
        });


        setupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StartRaceGUI();
            }
        });
    }
    
    private void updatePrintBox(JPanel middle_print_box) {
        DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places
        for (int i = 0; i < num_horses; i++) {
            JPanel box = (JPanel) middle_print_box.getComponent(i);
            JLabel textLabel = (JLabel) box.getComponent(0);
            double confidence = horse_list.get(i).getConfidence();
            String formattedConfidence = df.format(confidence); // Round confidence to 2 decimal places
            textLabel.setText(horse_list.get(i).getName() + " is in lane " +
                    horse_list.get(i).getHorseLane() + " and has a confidence of (" +
                    formattedConfidence + ")");
        }
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

    public void setFallSymbol(){
        for (Horse horse: horse_list){
            if (horse.hasFallen()){
                horse.getHorseActor().setIcon(new ImageIcon("img/horse-fallen.png"));
            }else{
                horse.getHorseActor().setIcon(horse.getHorseActor().getIcon());
            }
        }
    }

    public void startRace() {
        SwingWorker<Void, Void> raceWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                boolean finished = false;
                resetHorsesBackToStart();

                while (!finished) {
                    moveHorses();
                    setFallSymbol();

                    for (Horse horse : horse_list) {
                        if (raceWonBy(horse)) {
                            finished = true;
                        }
                    }
                    if (haveAllHorsesFallen()) {
                        finished = true;
                    }

                    // Wait for 100 milliseconds
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                alterHorseConfidence();
                String finishText = (winner_horse == null) ? "  The race is VOID, none of the horses came to finish." : "   And the winner of the race is " + winner_horse.getName();
                System.out.println(finishText);
                footer_text.setText(finishText);

                return null;
            }
        };

        raceWorker.execute();
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
}
