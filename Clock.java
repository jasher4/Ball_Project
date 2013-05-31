import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;

public class Clock implements ActionListener {
    private Balls balls;
    private Timer timer;
    private int timeIncr;
    
    public Clock(Balls balls, int dt) {
        this.balls = balls;
        timeIncr = dt;
        timer = new Timer(timeIncr, this);
    }
    
    public void start() {
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e) {
        balls.act();
    }
}