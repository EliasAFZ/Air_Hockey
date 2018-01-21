package project.pkg3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * GameScreen controls everything to be displayed on screen
 *
 * @author Elias
 */
public class GameScreen extends JFrame implements KeyListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Ball gameBall, gameball1;
    private Paddle p1, p2;
    private Timer gameRefresh;
    private Renderer panel;
    private final int DELAY;
    private boolean w, s, up, down, paused;
    private String stringTime;
    private long startTime;
    private long timeElapsed;
    private long timePaused;
    private int p1CalScore, p2CalScore;

    /**
     * Constructor initializes all variables
     */
    public GameScreen() {
        //frame setup
        this.setSize(WIDTH + 6, HEIGHT + 29);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(GameScreen.EXIT_ON_CLOSE);
        //panel setup
        this.panel = new Renderer();
        panel.setLocation(0, 0);
        panel.setSize(WIDTH, HEIGHT);
        this.add(panel);
        //game refresh
        this.DELAY = 10;
        stringTime = "";
        startTime = System.currentTimeMillis();
        //player setup
        this.p1 = new Paddle(30, 275, 10, 50, HEIGHT);
        this.p2 = new Paddle(WIDTH - 40, 275, 10, 50, HEIGHT);
        this.gameBall = new Ball(390, 290, 20, 20, HEIGHT, WIDTH);
        //key inputs
        this.addKeyListener(this);
        this.w = false;
        this.s = false;
        this.up = false;
        this.down = false;
        this.paused = true;

    }

    /**
     * Renders everything on screen
     *
     * @param g used with graphics2D to call methods
     */
    public void render(Graphics2D g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        Line2D line = new Line2D.Float(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        g.draw(line);
        g.setStroke(new BasicStroke(10));
        Line2D goal1 = new Line2D.Float(5, 245, 5, 355);
        g.draw(goal1);
        Line2D goal2 = new Line2D.Float(WIDTH - 5, 245, WIDTH - 5, 355);
        g.draw(goal2);
        g.drawString("PRESS SPACE TO START/STOP", 10, 10);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(p1.toString(), (WIDTH / 2) - 23, 30);
        g.drawString(p2.toString(), (WIDTH / 2) + 7, 30);
        p1.render(g);
        p2.render(g);
        gameBall.render(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(stringTime, 700, 20);
        g.drawString("Time:", 640, 20);

    }

    /**
     * Sets the collision for the goal on the left and increments it
     */
    private void goalScore1() {
        if (245 < gameBall.getY() && 355 > gameBall.getY()) {
            if (5 > gameBall.getX()) {
                p1.scoreIncrement();
                gameBall = new Ball(390, 290, 20, 20, HEIGHT, WIDTH);
                //System.out.println("GOAL1");
            }
        }
    }

    /**
     * Sets the collision for the goal on the right and increments it
     */
    private void goalScore2() {
        if (245 < gameBall.getY() && 355 > gameBall.getY()) {
            if (WIDTH - 5 < gameBall.getX() + gameBall.getWidth()) {
                p2.scoreIncrement();
                gameBall = new Ball(390, 290, 20, 20, HEIGHT, WIDTH);
                //System.out.println("GOAL2");
            }
        }
    }

    /**
     * Controls the tickrate of the game updating it according to the delay
     */
    public void gameRefreshTimer() {
        gameRefresh = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GameScreen.this.update();
                GameScreen.this.panel.repaint();
                GameScreen.this.gameBall.moveBall(p1, p2);
                GameScreen.this.timeElapsed = (System.currentTimeMillis() - GameScreen.this.startTime) / 1000;
                GameScreen.this.stringTime = "" + (timeElapsed);
                GameScreen.this.restart();
                goalScore2();
                goalScore1();
                //System.out.println(paused);
            }
        });
    }

    /**
     * simply here to satisfy the constructor because of an abstract class
     *
     * @param ke
     */
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /**
     * Holds all values for keys Pressed
     *
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        int id = ke.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = true;
        }

        if (id == KeyEvent.VK_S) {
            s = true;
        }

        if (id == KeyEvent.VK_UP) {
            up = true;
        }

        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }

        if (id == KeyEvent.VK_SPACE) {
            pauseMethod();
        }
    }

    /**
     * Restarts when the score reaches its set amount
     */
    private void restart() {
        if (p1.getScore() == 7) {
            p1CalScore = 7 * (int) this.timeElapsed;
            intializeAll();
            pauseMethod();
            topTenScore();

        } else if (p2.getScore() == 7) {
            p2CalScore = 7 * (int) this.timeElapsed;
            intializeAll();
            pauseMethod();
            topTenScore();
        }

    }

    /**
     * Creates scoreboard
     */
    private void topTenScore() {
        ArrayList scoreList = new ArrayList();
        for (int i = 0; i < scoreList.size(); i++) {
            if (p1CalScore > (int) scoreList.get(i)) {
                scoreList.add(p1CalScore);
            } else if (p1CalScore > (int) scoreList.get(i)) {
                scoreList.add(p2CalScore);
            }
        }
        try {
            FileOutputStream file = new FileOutputStream("Scoreboard.ser");

        } catch (Exception e) {

        }

    }

    /**
     * Re-intializes all needed variables to restart the game
     */
    private void intializeAll() {
        this.setSize(WIDTH + 6, HEIGHT + 29);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(GameScreen.EXIT_ON_CLOSE);
        //panel setup
        this.panel = new Renderer();
        panel.setLocation(0, 0);
        panel.setSize(WIDTH, HEIGHT);
        this.add(panel);
        //game refresh
        stringTime = "";
        startTime = System.currentTimeMillis();
        //player setup
        this.p1 = new Paddle(25, 275, 10, 50, HEIGHT);
        this.p2 = new Paddle(WIDTH - 35, 275, 10, 50, HEIGHT);
        this.gameBall = new Ball(390, 290, 20, 20, HEIGHT, WIDTH);
        //key inputs
        this.w = false;
        this.s = false;
        this.up = false;
        this.down = false;
        this.paused = false;
    }

    /**
     * Used to pause the game
     */
    private void pauseMethod() {
        if (!paused) {
            timePaused = System.nanoTime();
            gameRefresh.stop();
            paused = !paused;
        } else if (paused) {
            timeElapsed = timePaused - timeElapsed;
            gameRefresh.start();
            paused = !paused;
        }
    }

    /**
     * Records all key released functions
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        int id = ke.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }

        if (id == KeyEvent.VK_S) {
            s = false;
        }

        if (id == KeyEvent.VK_UP) {
            up = false;
        }

        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }

    }

    /**
     * updates movements of paddles
     */
    private void update() {

        if (w == true) {
            p1.move(-9);
        }

        if (s == true) {
            p1.move(9);
        }

        if (up == true) {
            p2.move(-9);
        }

        if (down == true) {
            p2.move(9);
        }
    }

}
