
import mayflower.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
/**
 * Creates the game "World"
 * Extends Mayflower
 * Creates save files
 * Used to start game
 */
public class Screen extends Mayflower
{
    boolean j = false;
    /**
     * Default constructor
     */
    public Screen(){
        
        super("StalinSort", 1200, 600);
    }
    
    /**
     * Creats a new save file if none exist
     * Creates a new StalinWorld, serving as the game World
     */
    public void init(){ 
     
        try {
            File myObj = new File("project2SaveGame.stalin");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Mayflower.setFullScreen(false);
        StalinWorld x = new StalinWorld();
        Mayflower.setWorld(x);

    }

}