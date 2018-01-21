package project.pkg3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Creates a Ball movable object
 *
 * @author Elias
 */
public class Ball extends MovableObject {

    private int movingAngle;
    private int speed = 8;
    private int frameHeight, frameWidth;
    private Random rand;

    /**
     * Constructs a Ball
     */
    public Ball(int _x, int _y, int _width, int _height, int frameHeight, int frameWidth) {
        super(_x, _y, _width, _height);
        rand = new Random();
        movingAngle = rand.nextInt(100) + 30;
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
    }

    /**
     * Moves the ball
     *
     * @param paddleObject1 checks collision via a method
     * @param paddleObject2 checks collision via a method
     */
    public void moveBall(Paddle paddleObject1, Paddle paddleObject2) {
        x += speed * Math.sin(Math.toRadians(movingAngle));
        y += speed * Math.cos(Math.toRadians(movingAngle));
        checkTopFrameCollision();
        checkBotFrameCollision();
        checkLeftFrameCollision();
        checkRightFrameCollision();
        rightPaddleCollision(paddleObject1);
        leftPaddleCollision(paddleObject1);
        rightPaddleCollision(paddleObject2);
        leftPaddleCollision(paddleObject2);
        topPaddleCollision(paddleObject1);
        topPaddleCollision(paddleObject2);
        botPaddleCollision(paddleObject1);
        botPaddleCollision(paddleObject2);

    }

    /**
     * checks top paddle collision
     */
    private void topPaddleCollision(Paddle paddleObject) {
        if (paddleObject.getX() < this.getX() && paddleObject.getX() + paddleObject.getWidth() > this.getX()) {
            if (paddleObject.getX() < this.getX() + this.getWidth() && paddleObject.getX() + paddleObject.getWidth() > this.getX() + this.getWidth()) {
                if (paddleObject.getY() < this.getY() + paddleObject.getHeight()) {
                    topBotBounce();
                }
            }
        }

    }

    /**
     * checks bottom paddle collision
     */
    private void botPaddleCollision(Paddle paddleObject) {
        if (paddleObject.getX() < this.getX() && paddleObject.getX() + paddleObject.getWidth() > this.getX()) {
            if (paddleObject.getX() < this.getX() + this.getWidth() && paddleObject.getX() + paddleObject.getWidth() > this.getX() + this.getWidth()) {
                if (paddleObject.getY() + paddleObject.getHeight() > this.getY()) {
                    topBotBounce();
                }
            }
        }
    }

    /**
     * checks right side paddle collision
     */
    private void rightPaddleCollision(Paddle paddleObject) {
        if (paddleObject.getY() < this.getY() && paddleObject.getY() + paddleObject.getHeight() > this.getY()) {
            if (paddleObject.getX() < this.getX() && paddleObject.getX() + paddleObject.getWidth() > this.getX()) {
                sideWallBounce();
            }
        } else if (paddleObject.getY() < this.getY() && paddleObject.getY() + paddleObject.getHeight() > this.getY()) {
            if (paddleObject.getX() < this.getX() + this.getWidth() && paddleObject.getX() + paddleObject.getWidth() > this.getX() + this.getWidth()) {
                sideWallBounce();
            }
        }

    }

    /**
     * checks left side paddle collision
     */
    private void leftPaddleCollision(Paddle paddleObject) {
        if (paddleObject.getY() < this.getY() && paddleObject.getY() + paddleObject.getHeight() > this.getY()) {
            if (paddleObject.getX() < this.getX() + this.getWidth() && paddleObject.getX() + paddleObject.getWidth() > this.getX() + this.getWidth()) {
                sideWallBounce();
            }
        } else if (paddleObject.getY() < this.getY() && paddleObject.getY() + paddleObject.getHeight() > this.getY()) {
            if (paddleObject.getX() < this.getX() && paddleObject.getX() + paddleObject.getWidth() > this.getX()) {
                sideWallBounce();
            }
        }
    }

    /**
     * method for ball to calculate the angle it needs to bounce of the top and
     * bottom frame
     */
    private void topBotBounce() {
        if (movingAngle > 0 && movingAngle < 180) {
            movingAngle = 180 - movingAngle + rand.nextInt(3) + 1;
            //System.out.println(movingAngle + " " + getX());
        } else if (movingAngle >= 270) {
            movingAngle = 360 - movingAngle + 180 + rand.nextInt(3) + 1;
            //System.out.println(movingAngle + " " + getX());
        } else if (movingAngle >= 180 && movingAngle < 270) {
            movingAngle = 360 - (movingAngle - 180) + rand.nextInt(3) + 1;
            //System.out.println(movingAngle + " top bounce" + getX());
        }

    }

    /**
     * calculation for ball angle of travel on the left and right frame
     */
    private void sideWallBounce() {
        movingAngle = 360 - movingAngle + rand.nextInt(5) + 1;
        //System.out.println(movingAngle + " side" + getX());
    }

    /**
     * checks top jframe collision
     */
    private void checkTopFrameCollision() {
        if (this.getY() < 0) {
            topBotBounce();
            // System.out.println("topCollision");
        }
    }

    /**
     * checks bottom jframe collision
     */
    private void checkBotFrameCollision() {
        if (this.getY() + this.getHeight() > frameHeight) {
            topBotBounce();
            //System.out.println("botCollision");
        }
    }

    /**
     * checks left jframe collision
     */
    public void checkLeftFrameCollision() {
        if (getX() < 0) {
            sideWallBounce();
        }
    }

    /**
     * checks right jframe collision
     */
    public void checkRightFrameCollision() {
        if (getX() + this.getWidth() > frameWidth) {
            sideWallBounce();
        }
    }

    /**
     * Renders ball
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
