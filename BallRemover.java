import javax.swing.event.ChangeEvent;
import java.util.*;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BallRemover extends JButton {
    private Balls balls;
    
    public BallRemover(Balls balls) {
        this.balls = balls;
        addActionListener(new BallListener());
    }
    
    public class BallListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            balls.remove();
        }
    }
}