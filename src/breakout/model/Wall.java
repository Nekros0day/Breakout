package breakout.model;

/*
        A wall for the ball to bounce
 */
public class Wall extends abstractPositionable implements IPositionable{

    private Dir direction;

    public Wall(Dir direction, double x, double y, double x1, double y1, double width, double height) {
        super(x, y, x1, y1, width, height);
        this.direction = direction;

    }

    public Dir getDirection(){
        return this.direction;
    }

    public boolean wallHit(Ball ball){
        boolean xHit = ball.getC1() <= this.getX1() && ball.getC1() >= this.getX();
        boolean yHit = ball.getC2() <= this.getY1() && ball.getC2() >= this.getY();

        return xHit & yHit;
    }

    public enum Dir {
        HORIZONTAL, VERTICAL
    }

}
