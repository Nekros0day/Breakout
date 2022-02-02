package breakout.model;

/*
 *   A brick for the rows of bricks
 */

public class Brick extends abstractPositionable implements IPositionable{

    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;
    public int points;

    public Brick(double x, double y, int points){
        super(x, y, x + BRICK_WIDTH, y + BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
        //this.x = x;
        //this.y = y;
        this.points = points;
        //this.x1 = x + BRICK_WIDTH;
        //this.y1 = y + BRICK_HEIGHT;
    }
    public int getPoints() {
        return points;
    }
}

