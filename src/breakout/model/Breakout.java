package breakout.model;


import breakout.event.EventBus;
import breakout.event.ModelEvent;
import breakout.view.Assets;
import breakout.view.BreakoutGUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 *  Overall all logic for the Breakout Game
 *  Model class representing the full game
 *  This class should use other objects delegate work.
 *
 *  NOTE: Nothing visual here
 *
 */
public class Breakout {

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;  // Nano seconds used by JavaFX

    private int nBalls = 5;
    int points;

    private Ball ball;
    private List<Brick> bricks;
    private Paddle paddle;
    private List<Wall> walls;
    private Brick brickToRemove;

    // TODO Constructor that accepts all objects needed for the model


    public Breakout(Ball ball, List<Wall> walls, Paddle paddle, List<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.walls = walls;
        this.bricks = bricks;
        brickToRemove = null;
        timeForLastHit = System.currentTimeMillis();
    }

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here
        ball.updateBallPosition();

        if(shouldSpawnNewBall()){
            spawnNewBall();
        }
        else {
            Wall wallHit = wallCollision();
            if (wallHit != null) {
                System.out.println("HIT X: " + wallHit.getX() + ", Y: " + wallHit.getY());
                if (wallHit.getDirection() == Wall.Dir.HORIZONTAL) {
                    ball.setDy(-ball.getDy());
                } else {
                    ball.setDx(-ball.getDx());
                }
            }
            else if(paddleHit()){
                EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE, new Assets().ballHitPaddle));
                ball.setDy(-ball.getDy());
            }
            else if(brickCollition()){
                System.out.println("HIT BRICK");
                brickCollisionHandling();

            }
        }
  }

    // ----- Helper methods--------------
    public boolean shouldSpawnNewBall(){
        return ball.getC2() > GAME_HEIGHT & nBalls !=0;
    }

    public void spawnNewBall(){
        nBalls--;
        ball = new Ball();
    }
    // Used for functional decomposition

    public Wall wallCollision() {
        for(Wall wall : walls) {
            if (wall.wallHit(ball)){
                return wall;
            }
        }
        return null;
    }

    public boolean paddleHit(){
        //boolean xHit = ball.getX2() <= paddle.getX1() && ball.getX1() >= paddle.getX();
        //boolean yHit = ball.getY2() <= paddle.getY1() && ball.getY1() >= paddle.getY();
        if(ball.collitionObj(paddle)){
            return true;
        }
        return false;
    }
    // Check if colltion occured and mark collided brick as brickToRemove
    public boolean brickCollition(){
        for(Brick brick : bricks){
            //boolean xHit = ball.getX2() <= brick.getX1() && ball.getX1() >= brick.getX();
            //boolean yHit = ball.getY2() <= brick.getY1() && ball.getY1() >= brick.getY();
            if(ball.collitionObj(brick)){
                brickToRemove = brick;
                return true;
            }
        }
        return false;
    }
    // Handler for brick collition
    public void brickCollisionHandling(){
        EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_BRICK, new Assets().ballHitBrick));
        points += brickToRemove.getPoints();
        ballBounceDirection();
        bricks.remove(brickToRemove);
    }
    //add more handling to this so it handles all situations
    public void ballBounceDirection(){
        //Collition from side
        if(ball.getC2() <= brickToRemove.getY1() && ball.getC2() >= brickToRemove.getY()){
            ball.setDx(-ball.getDx());
        }
        //collition from botton or top
        else{
            ball.setDy(-ball.getDy());
        }
    }

    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> list = new ArrayList<>();
        list.add(ball);
        list.addAll(walls);
        list.add(paddle);
        list.addAll(bricks);
        return list;  // TODO return all objects to be rendered by GUI
    }

    public int getPoints() {
        return points;
    }

    public int getnBalls() {
        return nBalls;
    }
    public Paddle getPaddle() {
        return paddle;
    }





}
