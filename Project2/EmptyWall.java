
/**
 * Placeholder obstacle, tied to movingGround
 * Hidden off map
 * Extends class Obstacle
 * Act method overwritten to prevent movement and collision with player
 */
public class EmptyWall extends Obstacle
{
    private double xAxis;
    /**
     * Constructor for objects of class EmptyWall
     */
    public EmptyWall(int x)
    {
        super(x);
        setImage("Assets/emptyWall.png");
        xAxis = x;
        setLocation(-100,326);
    }
    public void act()
    {
        
    }

}

