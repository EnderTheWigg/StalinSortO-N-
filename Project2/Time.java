
/**
 * Tracks time between frames, and change in time in seconds
 * Tracks game start and current time
 */
public class Time
{
    public static double gameStartTime = 0.0d;  // in ms
    public static double currentTime = 0.0d;    //in seconds
    public static double deltaTime = 0.0d;      //in seconds
    private static double prevFrame = 0d;
    /**
     * Update currentTime
     * Set new deltaTime
     * recalculate previous frame length
     */
    public static void UpdateTime(){
        currentTime = (System.currentTimeMillis() - gameStartTime)/1000;
        SetDeltaTime(prevFrame, System.currentTimeMillis());
        prevFrame = System.currentTimeMillis();
    }
    /**
     * Calculate change in time in seconds between frames
     */
    public static void SetDeltaTime(double prevFrameTime, double currFrameTime){ 
        deltaTime = (currFrameTime - prevFrameTime)/1000; 
    }
    
}
