import mayflower.*;
/**
 * Static image
 * Extends GameObject
 */
public class ComradeStalin extends GameObject
{
    MayflowerImage image = new MayflowerImage("Assets/stalin.png");
    /**
     * Constructor for objects of class ComradeStalin
     */
    public ComradeStalin()
    {
        setImage(image);
        image.scale(100, 244);
    }
    /**
     * Overridden act to prevent movement
     */
    public void act()
    {
        
    }
    
}
