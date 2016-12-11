import java.io.File;
import java.awt.image.BufferedImage;


/**
 *
 * @author kaxell
 * Things to consider: Images. How do we really store them?  
 */


public class Character {
    private String description;
    private String name;
    private long ID;
    private boolean isTaken;
   
    BufferedImage image = null;
    File imageFile = null;

    //read image file
    public Character(String description, String name, long ID, boolean isTaken)
    {
        this.description = description;
        this.name = name;
        this.ID = ID;
        this.isTaken = isTaken;
    }
    
    public Character()
    {
        this.description = "";
        this.name = "";
        this.ID = 0;
        this.isTaken = false;
    }
    
    //getters
    public String getDescription(){return description;} 
    public String getName(){return name;} 
    public long getID(){return ID;} 
    public boolean getIsTaken(){return isTaken;}
    
    //setters
    public void setDescription(String description)
    {this.description = description;}
    public void setName(String name)
    {this.name = name;}
    public void setID(long charID)
    {ID = charID;}
    public void setIsTaken(boolean isTaken)
    {this.isTaken = isTaken;}   
    
    
    
}
