import mayflower.*;
/**
 * Short moving obstacle, tied to movingGround
 * Player loses if they touch this.
 * Extends class Obstacle
 */
public class ShortWall extends Obstacle
{
    private double xAxis;
    MayflowerImage image = new MayflowerImage("Assets/obstacle1.png");
    /**
     * Constructor for objects of class ShortWall
     * 
     * @param x: initial x coordinates of the instance, used for calculating new position when moving
     */
    public ShortWall(int x)
    {
        super(x);
        image.scale(15, 75);
        setImage(image);
        xAxis = x;
    }

}
