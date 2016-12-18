package mainPackage;

import java.io.File;
import java.awt.image.BufferedImage;


/**
 *
 * @author kaxell
 * 
 */


public class Character {
    private String description;
    private String name;
    private long charID;
    private boolean isTaken;
    BufferedImage photo = null;
   

    //read image file
    public Character(String description, String name, long charID, boolean isTaken)
    {
        this.description = description;
        this.name = name;
        this.charID = charID;
        this.isTaken = isTaken;
    }
    
    public Character(long charID)
    {
        this.charID = charID;
    }
    
    public Character()
    {
        this.description = "";
        this.name = "";
        this.charID = 0;
        this.isTaken = false;
    }
    
    //getters
    public String getDescription(){return description;} 
    public String getName(){return name;} 
    public long getCharID(){return charID;} 
    public boolean getIsTaken(){return isTaken;}
    public BufferedImage getPhoto(){return photo;}
    
    //setters
    public void setDescription(String description)
    {this.description = description;}
    public void setName(String name)
    {this.name = name;}
    public void setCharID(long charID)
    {this.charID = charID;}
    public void setIsTaken(boolean isTaken)
    {this.isTaken = isTaken;}   
    public void setPhoto (BufferedImage photo)
    {this.photo = photo;}
    
    
    
}