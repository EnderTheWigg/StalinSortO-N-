import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text filess
import mayflower.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Loads from the save file.
 *
 * @author Sohum Tambe
 * Takes in a list of all the classes you have saved, the current world, the moving ground queue, and the obstacle queue
   Reads through the save file and instantiates the saved objects to the current world
 */
public class Loader
{

    public static GameObject player;
     /**
     * Takes in a list of all the classes you have saved, the current world, the moving ground queue, and the obstacle queue
     * Reads through the save file and instantiates the saved objects to the current world
     */
    public Loader(List<Class> allClassesUsed, World w, LinkedList<MovingGround> gq, LinkedList<Obstacle> oq)
    {
        try {
            File myObj = new File("project2SaveGame.stalin");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String nData = data.split("<>,")[0];
                for(Class s : allClassesUsed){
                    if(s.getName().equals(nData.trim())){
                        //match!
                        String[] reg = data.split("[^0-9\\.]+");
                        System.out.println(reg.length);
                        String x,y;
                        for(String l :reg){
                            System.out.println(l);
                        }
                        // Use results...
                        if(s.getName().equals("MovingGround")){

                            x = reg[3];
                            y =reg[4]; 
                        }else if(s.getName().equals("Obstacle")){

                            x = reg[3];
                            y =reg[4]; 
                        }else{
                            x = reg[1];
                            y = reg[2]; 
                        }
                        Double xval = Double.parseDouble(x);
                        Double yval = Double.parseDouble(y);
                        try{
                            System.out.println("X, Y" + xval.intValue() + " addsa " +  yval.intValue());
                            Actor z = (Actor)s.getDeclaredConstructor().newInstance();
                            w.addObject(z, 
                                0,
                                0);
                            z.setLocation(xval.intValue(), yval.intValue());
                            if(s.getName().equals("MovingGround")){
                                
                                gq.add((MovingGround)z);
                            }else if(s.getName().equals("Obstacle")){
                               
                                oq.add((Obstacle)z);
                            }else if(s.getName().equals("GameObject")){
                                player=(GameObject)z;
                                 z.setLocation(100, 100);
                            }
                            
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                }
                System.out.println(nData);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    /**
     * Helper method
     */
    public static GameObject getPlayer(){
        return player;
    }
    

}