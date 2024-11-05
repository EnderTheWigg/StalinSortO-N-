import mayflower.*;
/**
 * Medium-height moving obstacle, tied to movingGround
 * Player loses if they touch this.
 * Extends class Obstacle
 */
public class MediumWall extends Obstacle
{
    private double xAxis;
    MayflowerImage image = new MayflowerImage("Assets/obstacle2.png");
    /**
     * Constructor for objects of class MediumWall
     * 
     * @param x: initial x coordinates of the instance, used for calculating new position when moving
     */
    public MediumWall(int x)
    {
        super(x);
        image.scale(15,75);
        setImage(image);
        xAxis = x;
    }

}