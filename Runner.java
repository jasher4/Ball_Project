//by: Jason Asher
//Balls may be thrown, will fall with gravity, added with a particular radius, or removed

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Runner {
    public static void main(String[] args) {
        Balls balls = new Balls();
                
        Background backg = new Background();
        balls.addObserver(backg);
        JFrame backgFrame = new JFrame();
        backgFrame.setBounds(600, 10, 700, 700);
        backgFrame.getContentPane().add(backg);
        backgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgFrame.setResizable(false);
        backgFrame.setVisible(true);
        balls.setBackground(backg);
        
        JFrame addBalls = new JFrame("Add Balls");
        AddBall numberEnter = new AddBall("Enter radius here", 20, balls, backg); 
        addBalls.setBounds(300, 150, 200, 100);
        addBalls.getContentPane().add(numberEnter);
        addBalls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addBalls.setResizable(false);
        addBalls.setVisible(true);
        
        JFrame removeBalls = new JFrame("Remove Balls");
        removeBalls.setBounds(50, 150, 220, 100);
        removeBalls.getContentPane().add(new BallRemover(balls));
        removeBalls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeBalls.setResizable(false);
        removeBalls.setVisible(true);
        
        JFrame gravity = new JFrame("Toggle Gravity");
        gravity.setBounds(50, 275, 220, 100);
        gravity.getContentPane().add(new GravityButton(balls));
        gravity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gravity.setResizable(false);
        gravity.setVisible(true);
        
        Clock clock = new Clock(balls, 30);
        
        Stats stats = new Stats();
        balls.addObserver(stats);
        JFrame statsHolder = new JFrame("Stats");
        statsHolder.setBounds(100, 10, 350, 120);
        statsHolder.getContentPane().add(stats);
        statsHolder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statsHolder.setResizable(false);
        statsHolder.setVisible(true);
        clock.start();
    }
}