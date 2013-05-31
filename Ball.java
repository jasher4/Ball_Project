import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ball {
    private int xpos;
    private int ypos;
    private int radius;
    private int incrXpos;
    private int incrYpos;
    private int colliding = 0;
    private int startx = 0;
    private int starty = 0;
    private int stopx = 0;
    private int stopy = 0;
    private boolean clicked = false;
    public Ball (int r, int xpos, int ypos) {
        radius = r;
        this.xpos = xpos;
        this.ypos = ypos;
        this.incrXpos = (int)(Math.random()*10);
        this.incrYpos = (int)(Math.random()*10);
    }
    
    public void setStartX(int x) {
        startx = x;
    }
    
    public void setStartY(int y) {
        starty = y;
    }
    
    public void setStopX(int x) {
        stopx = x;
    }
    
    public void setStopY(int y) {
        stopy = y;
    }
    
    public void setClicked(boolean x) {
        clicked = x;
    }
    
    public boolean getClicked() {
        return clicked;
    }
    
    public void changeSpeed() {
        int dx = (int)((stopx - startx)/7.);
        int dy = (int)((stopy - starty)/7.);
        setIncrXpos(dx);
        setIncrYpos(dy);
    }

    public void setXpos(int x) {
        xpos = x;
    }

    public void setYpos(int y) {
        ypos = y;
    }

    public int getIncrXpos() {
        return incrXpos;
    }

    public int getIncrYpos() {
        return incrYpos;
    }

    public void setIncrXpos(int incrXpos) {
        this.incrXpos = incrXpos;
    }

    public void setIncrYpos(int incrYpos) {
        this.incrYpos = incrYpos;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public int getRadius() {
        return radius;
    }

    public void incrXpos() {
        xpos += incrXpos;
    }

    public void incrYpos() {
        ypos += incrYpos;
    }
    
    public void setColliding() {
        colliding++;
    }
    
    public void setCollidingZero() {
        colliding = 0;
    }

    public void collision(Ball ball, int side) {
        if (colliding < 2) {
            if (side == 1) {
                setIncrYpos(ball.getIncrYpos());
                ball.setIncrYpos(-getIncrYpos());
            } else if (side == 2) {
                setIncrXpos(ball.getIncrXpos());
                ball.setIncrXpos(-getIncrXpos());
            } else {
                setIncrYpos(ball.getIncrYpos());
                ball.setIncrYpos(-getIncrYpos());
                setIncrXpos(ball.getIncrXpos());
                ball.setIncrXpos(-getIncrXpos());
            }
        }
    }

    public int detectCollision(Ball ball) {
        if (getRadius() + ball.getRadius() >= Math.pow(Math.pow(getXpos() - ball.getXpos(), 2) + Math.pow(getYpos() - ball.getYpos(), 2), 0.5)) {
            if (((getXpos() + getRadius() > ball.getXpos() - ball.getRadius() && getXpos() + getRadius() < ball.getXpos() + ball.getRadius())
            || (getXpos() - getRadius() > ball.getXpos() - ball.getRadius() && getXpos() - getRadius() < ball.getXpos() + ball.getRadius()))
            && ((getYpos() + getRadius() > ball.getYpos() - ball.getRadius() && getYpos() + getRadius() < ball.getYpos() + ball.getRadius())
            || (getYpos() - getRadius() > ball.getYpos() - ball.getRadius() && getYpos() - getRadius() < ball.getYpos() + ball.getRadius()))) {
                return 3;
            } else if (((getXpos() + getRadius() > ball.getXpos() - ball.getRadius() && getXpos() + getRadius() < ball.getXpos() + ball.getRadius())
            || (getXpos() - getRadius() > ball.getXpos() - ball.getRadius() && getXpos() - getRadius() < ball.getXpos() + ball.getRadius()))) {
                return 1;
            } else {
                return 2;
            }
        }
        return 0;
    }

    public void move() {
        xpos += incrXpos;
        ypos += incrYpos;
    }

    public void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(xpos - radius, ypos - radius, radius*2, radius*2);
    }
}