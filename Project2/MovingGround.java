import mayflower.*;
/**
 * Class for MovingGround objects
 * Makes up the "floor" of the game
 * Linked to obstacles
 * Removed upon reaching beyond left side of the screen
 * Can be "stood on" by player
 * Extends GameObject
 */
public class MovingGround extends GameObject
{
    public double xAxis;
    double startFixedUpdate, endFixedUpdate = 0; 
    /**
     * Default constructor for objects of class MovingGround
     * deafault xAxis is 1400
     * Sets image
     */
    public MovingGround(){
        super("MovingGround");
         id = (int)((double)   (Math.random() * 10000));
        setImage("Assets/movingGround.png");
        xAxis = 1400;
    }
    
    /**
     * @param x - starting xAxis of the instance
     * Sets image
     */
    public MovingGround(int x)
    {
        super("MovingGround");
         id = (int)((double)   (Math.random() * 10000));
        setImage("Assets/movingGround.png");
        xAxis = x;
    }
    
    /**
     * Ensures proper location for instances created using the default constructor
     * Moves Actor
     */
    public void act()
    {
        if(xAxis == 1400)
            xAxis = getX();
        move();
        if(Time.currentTime >= endFixedUpdate)
            FixedUpdate();
    }
    
    /**
     * Calculates and saves the new location before moving the object
     * Changes location by 5.5 to the left (decreases xAxis) every call
     */
    public void move()
    {
        double newLocation = xAxis - 5.5d;
        xAxis = newLocation;
        setLocation(newLocation,526);
    }
    
    /**]
     * @return whether xAxis is <= -200
     */
    public boolean isTouchingLeftBoundary()
    {
        if(xAxis<=-200)
            return true;
        return false;
    }
    
    /**
     * saves the current object. 
     * calls itself at a random interval from 0-0.3d
     */
    public void FixedUpdate(){
        startFixedUpdate = Time.currentTime; 
        endFixedUpdate = Time.currentTime + Math.random() * 0.3;
        vel = new Vector2(getX(), getY());
        xAxis = getX();
        try{
            new Saver(MovingGround.class, this);
        }catch(Exception e){
            System.out.println("Oh no :( we had an error saving " + MovingGround.class.getName() + " We will try saving again on the next Fixed Update!");
        }
    }

}
