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

    /**
     *
     * @param description
     * @param name
     * @param charID
     * @param isTaken : describes if a char is taken by a user
     */
    public Character(String description, String name, long charID, boolean isTaken)
    {
        this.description = description;
        this.name = name;
        this.charID = charID;
        this.isTaken = isTaken;
    }
    
    /**
     * Constructor of a character
     * @param charID
     */
    public Character(long charID)
    {
        this.charID = charID;
        Character temp = LobbyConnection.getCharacter(charID);
        this.description = temp.description;
        this.name = temp.name;
        this.charID = temp.charID;
        this.isTaken = temp.isTaken;
    }
    
    /**
     *Sample Constructor
     */
    public Character()
    {
        this.description = "";
        this.name = "";
        this.charID = 0;
        this.isTaken = false;
    }
    
    //getters

    /**
     *
     * @return
     */
    public String getDescription(){return description;} 

    /**
     *
     * @return
     */
    public String getName(){return name;} 

    /**
     *
     * @return
     */
    public long getCharID(){return charID;} 

    /**
     *
     * @return
     */
    public boolean getIsTaken(){return isTaken;}

    /**
     *
     * @return
     */
    public BufferedImage getPhoto(){return photo;}
    
    //setters

    /**
     *
     * @param description
     */
    public void setDescription(String description)
    {this.description = description;}

    /**
     *
     * @param name
     */
    public void setName(String name)
    {this.name = name;}

    /**
     *
     * @param charID
     */
    public void setCharID(long charID)
    {this.charID = charID;}

    /**
     *
     * @param isTaken
     */
    public void setIsTaken(boolean isTaken)
    {this.isTaken = isTaken;}   

    /**
     *
     * @param photo
     */
    public void setPhoto (BufferedImage photo)
    {this.photo = photo;}
    
    
    
}
