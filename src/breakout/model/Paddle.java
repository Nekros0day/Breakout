package breakout.model;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 * A Paddle for the Breakout game
 *
 */
public class Paddle extends abstractPositionable implements IPositionable{

    public static final double PADDLE_WIDTH = 120;
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 10;

    public Paddle() {
        super(GAME_WIDTH / 2, GAME_HEIGHT - 30,  (GAME_WIDTH / 2) + PADDLE_WIDTH,
                (GAME_HEIGHT - 30) + PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public boolean canMoveToPositionLeft(){
        return getX() >= PADDLE_SPEED;
    }
    public boolean canMoveToRight(){
        return getX() + PADDLE_WIDTH <= GAME_WIDTH-PADDLE_SPEED;
    }

    public void updatePaddleXPosition(double change){
        setX(getX() + change);
        setX1(getX1() + change);
    }

    public void paddleDeteriate(double change){
        for(double i = 1; i <= 20; i++) {
            double p = 1;
            double c = (change / Math.abs(change)) / p;
            setX(getX() + c);
            setX1(getX1() + c);
            if(i % 2 == 0 && i > 3){
                p++;
            }

        }
    }

}
