import java.util.*;
import mayflower.*;
/**
 * World Class for the game.
 * Creates and removes objects.
 * Manages background screens.
 * Manages win and lose conditions.
 * Manages starting and running the game after initial launch
 */
public class StalinWorld extends World
{
    public boolean isSaved;
    private LinkedList<MovingGround> groundQueue;
    private LinkedList<Obstacle> obstacleQueue;
    private GameObject player;
    private int obstacleTimer;
    private int gameLength;
    private int gameTimer;
    private boolean gameOver;
    private String wonOrLost;
    private boolean gameStarted;
    private LoadingBar bar;
    private ComradeStalin stalin;
    /**
     *  Default constructor
     *  Initalizes variables
     */
    public StalinWorld()
    {
        groundQueue = new LinkedList<MovingGround>();
        obstacleQueue = new LinkedList<Obstacle>();
        stalin = new ComradeStalin();
        setBackground("Assets/titleScreen.png");
        bar = new LoadingBar();
        gameStarted = false;
        wonOrLost = "";
        isSaved = false;
        Time.UpdateTime();
        obstacleTimer = 0;
        gameLength = 20;
        gameTimer = 0;
    }
    
    /**
     * Executes every frame
     * Removes objects when they touch out of ounds
     * Increments gameTimer
     * Maintains saver size as equal to number of objects
     * Starts or ends the game
     */
    public void act()
    {
        Time.UpdateTime();
        
        if(removeOutOfBounds() == true && !gameOver)
        {
            addGroundSegment();
            gameTimer++;
        }
        
        Saver.totalSaves = numberOfObjects();
        
        if(gameStarted == false){
            newGame();

        }
        else
            gameOver();
    }

    /**
     * Either creates a new world or loads the save game depending on player input
     */
    public void generateWorld()
    {
        setBackground("Assets/Background.png");
        System.out.println("Yay! " + isSaved);
        if(!isSaved){
            player = new GameObject("Player"); 
            addObject(player, 0,0);
            player.setLocation(100,100);
            for(int i = 1; i < 15; i++)
            {
                addObject(bar, -1200, 50);
                MovingGround n = new MovingGround(i*100);
                addObject(n, i*100, 526);
                groundQueue.add(n);

                Obstacle y = new EmptyWall(i*-100);
                addObject(y, i*-100, 476);
                obstacleQueue.add(y);
            }
        }else{
            List<Class> p = new ArrayList<Class>();
            p.add(MovingGround.class);
            p.add(GameObject.class);
            p.add(Obstacle.class);

            new Loader(p, this, groundQueue,obstacleQueue);
            player = Loader.getPlayer();
            addObject(stalin, 1100, 300);
        }
    }
    
    /**
     * Creates new ground and obstacles, called when a ground object is removed (out of bounds)
     */
    public void addGroundSegment()
    {
        MovingGround n = new MovingGround(1200);
        addObject(n, 1200, 526);
        groundQueue.add(n);

        Obstacle y = chooseObstacle(1200);
        addObject(y, 1200, 326);
        obstacleQueue.add(y);
    }
    
    /**
     * Returns randomly chosen obstacle, ensures minimum distance of 5 blocks between obstacles
     */
    private Obstacle chooseObstacle(int x)
    {
        if((int)(Math.random()*2) == 1 && obstacleTimer >= 5)
        {
            obstacleTimer = 0;
            int rand = (int)(Math.random() * 10) + 1;
            if(rand % 2 == 0)
                return new ShortWall(x);
            else if(rand % 3 == 0)
                return new MediumWall(x);
            else
                return new TallWall(x);
        }
        obstacleTimer++;

        return new EmptyWall(x);
    }
    
    /**
     * Removes objects if out of bounds
     * Removes player if out of bounds
     * Ends game if loading bar is out of bounds
     * Moves loading bar
     */
    public boolean removeOutOfBounds()
    {
        if(bar.getX() >= 0 && gameStarted == true && !isSaved)
        {
            System.out.println("Win");
            clearWorld();
            gameOver = true;
            wonOrLost = "Won";
            return false;
        }

        if(groundQueue.size() != 0 && (groundQueue.get(0).isTouchingLeftBoundary() ))
        {
            removeObject(groundQueue.get(0));
            groundQueue.pop();
            if(player.getX() <= 0 || player.getY() > 600){
                clearWorld();
                System.out.println("Lose");
                gameOver = true;
                wonOrLost = "Lost";
            }
            if(!isSaved)
                bar.setLocation(bar.getX()+(1200.0 / gameLength), 5);
            return true;
        }
        return false;
    }

    /**
     * Starts a new game based on player input if game is over
     */
    public void newGame()
    {
        if(Mayflower.isKeyPressed(Keyboard.KEY_ENTER)){
            gameTimer = 0;
            gameStarted = true;
            isSaved = false;
            generateWorld();
        }
        else if(Mayflower.isKeyPressed(Keyboard.KEY_SPACE))
        {
            gameTimer = 0;
            gameStarted = true;
            isSaved = true; 
            generateWorld();
        }
    }
    
    /**
     * Removes all objects from the world
     */
    public void clearWorld()
    {
        while(groundQueue.size()!= 0){
            removeObject(groundQueue.get(0));
            groundQueue.pop();
        }
        
        while(obstacleQueue.size()!= 0){
            removeObject(obstacleQueue.get(0));
            obstacleQueue.pop();
        }
        
        removeObject(bar);
        removeObject(stalin);
        removeObject(player);
    }

    /**
     * Sets win or lose screen if game is over
     */
    public void gameOver()
    {
        if(gameOver)
        {
            if(wonOrLost.equals("Won"))
            {
                System.out.println("clearing - Won");
                clearWorld();

                setBackground("Assets/winScreen.png");
                gameStarted = false;
                gameOver = false;

            }
            else if(wonOrLost.equals("Lost"))
            {
                System.out.println("clearing - Lost");
                clearWorld();
                setBackground("Assets/loseScreen.png");

                gameStarted = false;
                gameOver = false;

            }
        }
    }

}