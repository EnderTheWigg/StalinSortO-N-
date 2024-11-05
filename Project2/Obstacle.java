import mayflower.*;
/**
 * Superclass for obstacles (Short, Medium, Tall, Empty - Wall Classes)
 *  Extends GameObject
 *  Framework for implicit method calls in subclasses
 */
public class Obstacle extends GameObject
{
    public double xAxis;
    /**
     * Default constructor for objects of class Obstacle
     * Default xAxis set at 1400
     * Has randomized image selection for use in loading, overridden in subclasses
     */
    public Obstacle()
    {
        super("Obstacle");
        id = (int)((double)   (Math.random() * 10000));
        int rand = (int)(Math.random() * 10) + 1;
        if(rand % 2 == 0){
            MayflowerImage x = new MayflowerImage("Assets/obstacle1.png");
            x.scale(15,75);
            setImage(x);
        }
        else if(rand % 3 == 0){
            MayflowerImage x = new MayflowerImage("Assets/obstacle2.png");
            x.scale(15,75);
            setImage(x);
        }

        else
        {
            MayflowerImage x = new MayflowerImage("Assets/obstacle3.png");
            x.scale(15,75);
            setImage(x);
        }
        xAxis = 1400;
    }
    
    /**
     * Constructor for objects of class Obstacle
     * @param x is the initial xAxis
     */
    public Obstacle(int x)
    {
        super("Obstacle");
        id = (int)((double)   (Math.random() * 10000));
        xAxis = x;
    }
    
    /**
     * Ensures proper location for instances created using the default constructor
     * Moves Actor
     */
    public void act()
    {   if(xAxis == 1400)
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
        double newLocation = xAxis - 5.5;
        xAxis = newLocation;
        setLocation(newLocation,456);
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
            new Saver(Obstacle.class, this);
        }catch(Exception e){
            System.out.println("Oh no :( we had an error saving " + MovingGround.class.getName() + " We will try saving again on the next Fixed Update!");
        }
    }
        /**
     * @return whether xAxis is <= -200
     */
    public boolean isTouchingLeftBoundary()
    {
        if(xAxis<=-200)
            return true;
        return false;
    }
}