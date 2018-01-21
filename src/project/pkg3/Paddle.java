package project.pkg3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Creates a movable paddle object
 *
 * @author Elias
 */
public class Paddle extends MovableObject {

    Rectangle rectangle;
    private int score;
    private int frameHeight;

    /**
     * Constructor for paddle object
     */
    public Paddle(int _x, int _y, int _width, int _height, int _frameHeight) {
        super(_x, _y, _width, _height);
        rectangle = new Rectangle(_x, _y, _width, _height);
        frameHeight = _frameHeight;
        this.score = 0;
    }

    /**
     * moves paddle along a y-axis
     */
    @Override
    public void move(int dy) {
        /* Was attempting to limit the movement of the paddle
         * to only be within the frame but ran into issues  
         */
        //if (y >= 0 && y + height <= frameHeight) {
        y += dy;
        //}
        //else{
        // y =- dy;
        //}
    }

    /**
     * increments player score associated with paddle
     */
    public void scoreIncrement() {
        score++;
    }

    /**
     * returns a string representation of score
     *
     * @return score score in string form
     */
    @Override
    public String toString() {
        return score + "";
    }

    /**
     * Renders paddle object
     *
     * @param g used with graphics2D to render
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Gets score
     *
     * @return score returns the score value in int form
     */
    int getScore() {
        return this.score;
    }
}
