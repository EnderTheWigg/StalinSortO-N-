import mayflower.*;
import java.util.*;
/**
 * Extends Actor
 * Superclass of all interactable game objects
 * 
 */
public class GameObject extends Actor
{
    public String name; 
    public int id = 0; 
    public Vector2 vel = new Vector2(0,0); 
    boolean ignoreGravity = false; 
    double elapsed = 0;
    double startJumpTime, endJumpTime = 0;
    double startFixedUpdate, endFixedUpdate = 0; 
    boolean isJumping;
    boolean isCaught;
    double xAxis;
    MayflowerImage image = new MayflowerImage("Assets/rasputin.png");
    /**
     * Default Constructor
     * Implicitly creates a player
     */
    public GameObject(){
        this.name = "player";
        image.scale(100, 100);
        id = (int)((double)   (Math.random() * 10000));
        setImage(image);
        xAxis = 100;
        isCaught = false;
    }

    /**
     * 
     */
    public GameObject(String name){
        this.name = name;
        isJumping = false;
        isCaught = false;
        id = (int)((double)   (Math.random() * 10000));
        xAxis = 100;
        setImage("Assets/rasputin.png");
    }

    /**
     * 
     */
    public void act(){
        if(this.isTouching(Obstacle.class))
        {
            isCaught=true;
        }
        if(!ignoreGravity){
            if(!this.isTouching(MovingGround.class) || isJumping){
                vel.y += 0.1d;
                setLocation(getX(), (getY()  + vel.y));
            }
        }
        if(!isCaught){
            if(Math.abs(vel.y) >= 300){
                if(vel.y < 0) vel.y = -200;
                else vel.y = 200;
            }
            if(getY() >= 0)
                Jump();
        }
        else{
            double newLocation = xAxis - 300*Time.deltaTime;
            xAxis = newLocation;
            setLocation(newLocation, this.getY());
        }

        if(Time.currentTime >= endFixedUpdate)
            FixedUpdate();
    }

    /**
     * 
     */
    public void Jump(){

        if(Mayflower.isKeyDown(Keyboard.KEY_UP) || Mayflower.isKeyDown(Keyboard.KEY_SPACE)){
            elapsed = elapsed +  Time.deltaTime;
        }else if((Mayflower.wasKeyDown(Keyboard.KEY_UP) || Mayflower.wasKeyDown(Keyboard.KEY_SPACE)) 
        || Time.currentTime + 0.2 < endJumpTime){
            double baseJumpForce = 3d;
            if(startJumpTime == 0){
                isJumping = true;
                startJumpTime = Time.currentTime; 
                endJumpTime = Time.currentTime + 0.5d;
                vel = new Vector2(vel.x+ 0,  vel.y + (-baseJumpForce * (elapsed >= 0.2733? 0.2733 : elapsed) * 20));
                if(Math.abs(vel.y) < 3.2)
                    vel.y=-3.2;
                if(vel.y > 0 ){
                    vel.y = -Math.abs(vel.y);
                }
                System.out.println(vel.y);
                setLocation(getX(), (getY() - vel.y*Time.deltaTime));
            }

            else if(Time.currentTime >= endJumpTime){
                vel = new Vector2(0, 0 );
                startJumpTime = 0;
                endJumpTime = 0;
            }
            elapsed = 0;
        }
        else
            isJumping = false;
    }

    /**
     * saves the current object. 
     * calls itself at a random interval from 0-0.3d
     */
    public void FixedUpdate(){
        startFixedUpdate = Time.currentTime; 
        endFixedUpdate = Time.currentTime + Math.random() * 0.3;
        try{
            new Saver(GameObject.class, this);
        }catch(Exception e){
            System.out.println("Oh no :( we had an error saving " + GameObject.class.getName() + " We will try saving again on the next Fixed Update!");
        }
    }

}