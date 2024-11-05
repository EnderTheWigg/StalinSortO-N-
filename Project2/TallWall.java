import mayflower.*;
/**
 * Tall moving obstacle, tied to movingGround
 * Player loses if they touch this.
 * Extends class Obstacle
 */
public class TallWall extends Obstacle
{
    private double xAxis;
    MayflowerImage image = new MayflowerImage("Assets/obstacle3.png");
    /**
     * Constructor for objects of class MediumWall
     * 
     * @param x: initial x coordinates of the instance, used for calculating new position when moving
     */
    public TallWall(int x)
    {
        super(x);
        image.scale(15, 75);
        setImage(image);
        xAxis = x;
    }
}

