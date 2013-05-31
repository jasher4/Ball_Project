import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Stats extends JPanel implements Observer {
    private JLabel kineticEnergy;
    private JLabel numberBalls;
    
    public Stats() {
        setBackground(Color.WHITE);
        Font bigFont = new Font("Monospaced", Font.BOLD, 20);
        
        JLabel ballLabel = new JLabel("Number of Balls: ");
        ballLabel.setFont(bigFont);
        ballLabel.setForeground(Color.BLACK);
        add(ballLabel);

        numberBalls = new JLabel("0");
        numberBalls.setFont(bigFont);
        numberBalls.setForeground(Color.BLACK);
        add(numberBalls);
    }
    
    public void update(Observable model, Object arg) {
        int numBalls = ((Balls)model).getBalls().size();
        numberBalls.setText(String.format("%5d  ", numBalls));
    }
}