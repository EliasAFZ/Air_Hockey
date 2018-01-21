package project.pkg3;

import java.awt.Graphics2D;

/**
 * Sets base functions for any movable object
 *
 * @author Elias
 */
public abstract class MovableObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    /**
     * Constructs a movable object
     *
     * @param _x the position it will start at
     * @param _y the position it will start at
     * @param _width the objects width
     * @param _height the objects height
     */
    public MovableObject(int _x, int _y, int _width, int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
    }

    /**
     * Moves the object via the y axis
     */
    public void move(int dy) {
        y += dy;
    }

    public abstract void render(Graphics2D g);

    /**
     * gets x value for a movable object
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * gets y value for a movable object
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * gets width value for a movable object
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * gets height value for a movable object
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
