package breakout.model;

public abstract class abstractPositionable {
    private double x;      // x, y upper left corner
    private double y;
    private double x1;      // x, y upper left corner
    private double y1;
    private final double width;
    private final double height;

    abstractPositionable(double x ,double y, double x1, double y1, double width, double height) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }
    public double getX1(){
        return this.x1;
    }

    public double getY1(){
        return this.y1;
    }
    public void setX1(double x1){
        this.x1 = x1;
    }

    public void setY1(double y1){
        this.y1 = y1;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }
    public boolean collitionObj(abstractPositionable o){
        boolean xHit = this.getX1() <= o.getX1() && this.getX() >= o.getX();
        boolean yHit = this.getY1() <= o.getY1() && this.getY() >= o.getY();

        return (xHit && yHit);
    }
}
