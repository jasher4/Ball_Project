import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBall extends JTextField {
    Balls balls;
    Background backg;
    
    public AddBall(String str, int length, Balls balls, Background backg) {
        super(str, length);
        TextListener textListener = new TextListener();
        this.addActionListener(textListener);
        this.balls = balls;
        this.backg = backg;
    }
    
    public class TextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = getText();
            int radius = Integer.parseInt(str);
            boolean valid = false;
            int xpos = 0;
            int ypos = 0;
            int safety = 0;
            while (!valid && safety < 10) {
                xpos = (int)(Math.random()*(backg.getWidth()-2*radius))+radius;
                ypos = (int)(Math.random()*(backg.getHeight()-2*radius))+radius;
                valid = true;
                for (int i = 0; i < balls.getBalls().size() && valid; i++) {
                    Ball testBall = new Ball(radius, xpos, ypos);
                    Ball checkBall = balls.getBalls().get(i);
                    if (testBall.detectCollision(checkBall) != 0) {
                        valid = false;
                    }
                }
                safety++;
            }
            if (safety < 10)
            balls.add(new Ball(radius, xpos, ypos));
        }
    }
}