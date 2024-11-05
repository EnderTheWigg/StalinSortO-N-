import mayflower.*;
/**
 * Simple Actor that acts as a loading bar
 * Moved via StalinWorld Class
 */
public class LoadingBar extends Actor
{
    /**
     * Default constructor
     * Sets image
     */
    public LoadingBar(){
        setImage("Assets/loadingBar.png");
    }
    /**
     * Overriden act method, prevents movement
     */
    public void act(){}
}
