/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;
import java.awt.image.BufferedImage; 
import java.util.ArrayList;

/**
 *
 * @author kaxell
 * Profile of a player. 
 */
public class Profile {
 
    private String name;
    private BufferedImage photo;
    private String description;
    private ArrayList<Lobby> finishedGames;
    private boolean isYours;
    private long ID;
    
    /*public Profile( String name, BufferedImage photo, String description, boolean isYours){
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.isYours = isYours;
        finishedGames = new ArrayList<Lobby>();
    
    }*/

    /**
     *
     * @param ID
     */

    public Profile(long ID){
        this.ID = ID;
        isYours = this.ID == AccessHandler.userID;
        retrieveData();
    }
    
       
    //getters / setters 

    /**
     *
     * @return
     */
    
    public String getName(){return name;}

    /**
     *
     * @return
     */
    public BufferedImage getImage(){return photo;}

    /**
     *
     * @return
     */
    public String getDescription(){return description;}

    /**
     *
     * @return
     */
    public ArrayList<Lobby> getFinishedGames(){return finishedGames;}

    /**
     *
     * @return
     */
    public boolean getIsYours(){return isYours;}

    /**
     *
     * @return
     */
    public long getID(){
        return ID;
    }
   
    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
        //updateData();
    }

    /**
     *
     * @param photo
     */
    public void setImage(BufferedImage photo){
        this.photo = photo;
        //updateData();
    }
        
    /**
     *
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
        //updateData();
    }

    /**
     *
     * @param finishedGames
     */
    public void setFinishedGames(ArrayList<Lobby> finishedGames){
        this.finishedGames = finishedGames;
        //updateData();
    }

    /**
     * Describes if this profile is yours or not. 
     * @param isYours
     */
    public void setIsYours(boolean isYours){
        this.isYours = isYours;
        //updateData();
    }
    
    /**
     * Pulls the profile info in database.
     */
    public void retrieveData(){
        
        ProfileConnection.updateProfileInClient(this);
    //Retrieves the data of profile. This will sync the variables of object
    //with the data in the database, therefore before showing a profile to the users, this method will
    //make it certain that the updated data will be showed to the user.
    
    }
    
    /**
     * Updates the profile info in database
     */
    public void updateData(){//
        ProfileConnection.updateProfileInDatabase(this);
    }
    
    /**
     *
     * @param ID
     * @param isYours
     * @return
     */
    public Player createPlayer (long ID, boolean isYours){
        return new Player(ID);
    }
    
    /**
     * Views finished games of a player.
     * @return an arraylist of lobbies
     */
    public ArrayList<Lobby> viewFinishedGames(){
    //Returns unfinished games of player that profile belongs to
    return ProfileConnection.getFinishedGames(ID);
    }
    
    /**
     * Views unfinished games of a player.
     * @return an arraylist of lobbies
     */
    public ArrayList<Lobby> viewUnfinishedGames(){
    //Returns unfinished games of player that profile belongs to
    return LobbyConnection.getOngoingGamesOfPlayer(ID);
    }
    
    
}