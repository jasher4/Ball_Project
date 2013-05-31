import java.util.*;
import java.awt.*;

public class Balls extends java.util.Observable {
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Background backg;
    private boolean gravity = false;

    public Balls() {
    }
    
    public void gravity() {
        if (gravity) {
            gravity = false;
        } else 
            gravity = true;
     }

    public void setBackground(Background backg) {
        this.backg = backg;
        int radius = (int)(Math.random()*10 + 45);
        add(new Ball(50, (int)(Math.random()*(backg.getWidth()-2*radius))+radius,
            (int)(Math.random()*(backg.getHeight()-2*radius))+radius));
    }
    
    public void mousePressed(Ball ball, int x, int y) {
        if (x >= ball.getXpos() - ball.getRadius() && x <= ball.getXpos() + ball.getRadius()
           && y >= ball.getYpos() - ball.getRadius() && y <= ball.getYpos() + ball.getRadius()) {
               ball.setStartX(x);
               ball.setStartY(y);
               ball.setClicked(true);
            }
    }
    
    public void mouseReleased(Ball ball, int x, int y) {
        if (ball.getClicked()) {
            ball.setClicked(false);
            ball.setStopX(x);
            ball.setStopY(y);
            ball.changeSpeed();
        }
    }

    public void add(Ball ball) {
        balls.add(ball);
    }

    public void remove() {
        balls.remove(0);
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void act() {
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            if (b != null) {
                if (gravity) {
                    b.setIncrYpos(b.getIncrYpos() + 2);
                }
                if (b.getXpos() + b.getRadius() > backg.getWidth()) {
                    if (b.getIncrXpos() > 0) {
                        b.setIncrXpos((int)(-1*b.getIncrXpos()));
                    }
                    b.setXpos(backg.getWidth()-b.getRadius());
                }
                if (b.getXpos() - b.getRadius() < 0) {
                    if (b.getIncrXpos() < 0) {
                    b.setIncrXpos(b.getIncrXpos() * -1);
                    }
                    b.setXpos(b.getRadius());
                }
                if (b.getYpos() + b.getRadius() > backg.getHeight()) {
                    if (b.getIncrYpos() > 0)
                    if (gravity)
                    b.setIncrYpos((int)(-0.8*b.getIncrYpos()));
                    else
                    b.setIncrYpos(b.getIncrYpos() * -1);
                    b.setYpos(backg.getHeight()-b.getRadius());
                }
                if (b.getYpos() - b.getRadius() < 0) {
                    if (b.getIncrYpos() < 0)
                    b.setIncrYpos(b.getIncrYpos() * -1);
                    b.setYpos(b.getRadius());
                }
                for (Ball b2: balls) {
                    if (b2 != b) {
                        if (b.detectCollision(b2) != 0) {
                            b.collision(b2, b.detectCollision(b2));
                            b.setColliding();
                        }
                    }
                }
                b.setCollidingZero();
                b.move();
            }
        }
        setChanged();
        notifyObservers();
    }

    public void drawBalls(Graphics g) {
        for (Ball a: balls) {
            a.drawBall(g);
        }
    }
}