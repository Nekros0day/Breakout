package breakout.model;

import java.util.Random;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 *    A Ball for the Breakout game
 */

public class Ball extends abstractPositionable implements IPositionable{

    private double c1;      // x, y upper left corner
    private double c2;
    private double dx;
    private double dy;
    private double dirSpeed; //speed

    public Ball() {
        super(GAME_WIDTH/2 + 2, GAME_HEIGHT/2 + 2,
                GAME_WIDTH/2 - 2, GAME_HEIGHT /2 - 2, 8, 8);
        c1 = GAME_WIDTH/2;       // Start parametrar
        c2 = GAME_HEIGHT/2;      // Start PARAMETERAR
        dx = -Math.cos(getRandomSpeed(7*Math.PI/6, 11*Math.PI/6));
        dy = -Math.sin(getRandomSpeed(7*Math.PI/6, 11*Math.PI/6));
        dirSpeed = getRandomSpeed(1.5, 3);
        //width = 8;
        //height = 8;
        //x = c1 + (width-2)/2;      // Start parametrar
        //y = c2 + (height-2)/2;      // Start PARAMETERAR
        //x1 = c1 - (width-2)/2;       // Start parametrar
        //y1 = c2 - (height-2)/2;      // Start PARAMETERAR
    }

    public void updateBallPosition() {
        c1 += dx*dirSpeed;
        c2 += dy*dirSpeed;
        setX(c1 + (getWidth()-2)/2);
        setY(c2 + (getHeight()-2)/2);
        setX1(c1 - (getWidth()-2)/2);
        setY1(c2 - (getHeight()-2)/2);
        /*x1 = c1 + (width-2)/2;      // Start parametrar
        y1 = c2 + (height-2)/2;      // Start PARAMETERAR
        x2 = c1 - (width-2)/2;       // Start parametrar
        y2 = c2 - (height-2)/2;      // Start PARAMETERAR
        */
    }


    public double getRandomSpeed(double upBound, double lowBond){
        return ThreadLocalRandom.current().nextDouble(upBound,lowBond);
    }
    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }
    public double getC1() {
        return c1;
    }
    public double getC2() {
        return c2;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }

}
