import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Observer;
import java.util.Observable;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Background extends JPanel implements Observer {
    private Balls balls;
    private int mx = 0;
    private int my = 0;
    
    public void update(Observable model, Object arg) {
        setBackground(Color.WHITE);
        balls = (Balls)model;
        MyMouseListener mouseListener = new MyMouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        repaint();
    }
     
    public class MyMouseListener implements MouseListener, MouseMotionListener {
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < balls.getBalls().size(); i++) {
                balls.mousePressed(balls.getBalls().get(i), e.getX(), e.getY());
            }
        }
        
        public void mouseReleased(MouseEvent e) {
            for (int i = 0; i < balls.getBalls().size(); i++) {
                balls.mouseReleased(balls.getBalls().get(i), e.getX(), e.getY());
            }
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
        
        public void mouseClicked(MouseEvent e) {
        }
        
        public void mouseDragged(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }
        
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < balls.getBalls().size(); i++) {
            if (balls.getBalls().get(i).getClicked()) {
                g.setColor(Color.BLUE);
                g.drawLine(balls.getBalls().get(i).getXpos(), balls.getBalls().get(i).getYpos(),
                            mx, my);
             }
         }
        balls.drawBalls(g);
    }
    
    public void drawBalls(Graphics g) {
        for (Ball b: balls.getBalls()) {
            b.drawBall(g);
        }
    }
}