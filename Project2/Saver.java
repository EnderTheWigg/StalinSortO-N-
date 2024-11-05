import java.util.*;
import java.lang.reflect.*;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import mayflower.*;
/**
 * Saves an instance of a class
 */
public class Saver
{
    public static Queue<String> allSaves = new LinkedList<String>();
    public static Set<Integer> ids = new HashSet<Integer>();
    public static int totalSaves =1;
    

    /**
     * Takes the class you want saved as well as the instance of the object you want saved  
     * and writes the class in the format "className <>, variableName {} variableValue ::: otherVariableName {} otherVariableValue ::: ....etc...."
     * Saves that string to a Queue. 
     * When the Queue gets a size >= 33 then we overwrite the current save file and we save all strings in the queue
     */
    public Saver(Class classS, Object z) throws IOException
    {
        Field[] fields = classS.getFields();
       
        String classSet= classS.getName() + " <>, ";
        boolean x = true;
        if(((Actor)z).getX() <= -200 || ((Actor)z).getX() >= 1150)
            return;
        for(Field s : fields){
            
            try{
                //System.out.println(s.getName() + s.get(z));
                try{
                    if(s.getName().equals("id")){
                        //System.out.println(((Actor)z).getX());
                         x = ids.add(Integer.parseInt(s.get(z).toString())); 
                    }
                    classSet+= s.getName() + " {} " + s.get(z).toString() + " ::: ";
                    
                   
                }catch(Exception e){

                }
            }catch(Exception e){
                continue;
            }
        }

        if(x)
            allSaves.add(classSet);
        
        //System.out.println(allSaves.size());
  
        if(allSaves.size() >= 33){
            //System.out.println(allSaves.size() + "sasadas");
            save();
            
        }
    }
    
    /**
     * Gets all objects from queue, writes line by line to the file, overriding the previouisly saved file
     */
    public void save(){
        String fileName = "project2SaveGame.stalin";
        String fullString = "";
        String x = allSaves.poll();
        ids.clear();
        while(x!=null){
            fullString += x + "\n\n";
            x = allSaves.poll();
        }
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(fullString);
            myWriter.close();
           
        } catch (IOException e) {
            System.out.println("Error while Saving: "+e);
            e.printStackTrace();
        }
    }
}

/*
 * Data Structure for file :
 * \n
 * className <>, variableName {} variableValue ::: otherVariableName {} otherVariableValue ::: ....etc....
 * 
 * 
 */

