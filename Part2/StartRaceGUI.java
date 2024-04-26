import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartRaceGUI extends JFrame {

    public StartRaceGUI() {
        super("Race Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        Font font = new Font("Futura", Font.BOLD, 16);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);

        Color darkBlue = new Color(0, 51, 102);
        Color lightBlue = new Color(173, 216, 230);
        Color red = new Color(255, 0, 0);
        Color white = Color.WHITE;
        Color borderColor = Color.BLACK;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(darkBlue);

        JLabel trackLabel = new JLabel("Race Track Distance:");
        trackLabel.setForeground(white);
        JTextField trackField = new JTextField();
        trackField.setBackground(lightBlue); 
        trackField.setForeground(Color.black); 
        panel.add(trackLabel);
        panel.add(trackField);

        JLabel horsesLabel = new JLabel("Number of Horses:");
        horsesLabel.setForeground(white);
        JTextField horsesField = new JTextField();
        horsesField.setBackground(lightBlue); 
        horsesField.setForeground(Color.black); 
        panel.add(horsesLabel);
        panel.add(horsesField);

        JButton startButton = new JButton("Start Race");
        startButton.setBackground(red);
        startButton.setForeground(white);
        startButton.setBorder(new CompoundBorder(new LineBorder(borderColor), new EmptyBorder(5, 15, 5, 15)));
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int raceTrackDistance = Integer.parseInt(trackField.getText());
                    int numHorses = Integer.parseInt(horsesField.getText());
                    if (raceTrackDistance >= 250 && raceTrackDistance <= 700 && numHorses >= 1 && numHorses <= 6) {
                        dispose(); // Close input window
                        new GUI(raceTrackDistance, numHorses);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid values.\nHorses could be between 1-6. \nRace distance could be between 250-750.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
        setVisible(true);
    }


    public static void main(String[] args) {
        new StartRaceGUI();
    }
}
