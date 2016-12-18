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
 */
public class Profile {
 
    private String name;
    private BufferedImage photo;
    private String description;
    private ArrayList<Lobby> finishedGames;
    private boolean isYours;
    
    public Profile( String name, BufferedImage photo, String description, boolean isYours){
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.isYours = isYours;
        finishedGames = new ArrayList<Lobby>();
    
    }
    
    //getters / setters 
    
    public String getName(){return name;}
    public BufferedImage getImage(){return photo;}
    public String getDescription(){return description;}
    public ArrayList<Lobby> getFinishedGames(){return finishedGames;}
    public boolean getIsYours(){return isYours;}
   
    public void setName(String name){this.name = name;}
    public void setImage(BufferedImage photo){this.photo = photo;}
    public void setDescription(String description){this.description = description;}
    public void setFinishedGames(ArrayList<Lobby> finishedGames) {this.finishedGames = finishedGames;}
    public void setIsYours(boolean isYours){this.isYours = isYours;}
    
    
    
    
    public void retrieveData(){
    //Retrieves the data of profile. This will sync the variables of object
    //with the data in the database, therefore before showing a profile to the users, this method will
    //make it certain that the updated data will be showed to the user.
    }
    
    public void updateData(){
    //Updates the data of profile page in database with current values. This
    //is the opposite of the retrieveData method, so this one will sync the data in the database with
    //the current values of variables in an Profile object.
    
    }
    
    public Player createPlayer (int ID, boolean isYours){return null;}
            /*Creates a player object with given values.
This will be useful method when getting new player information comes from the database, by
this method an new player instance will be created with given unique ID. The other information
about the players will be taken with Profile class via ID variable. isYours parameter shows if new
player object will be representative of current user of the client. If so, some actions will be
available for the user, like updating profile data, joining a lobby.*/
    
    public ArrayList<Lobby> viewUnfinishedGames(){
    //Returns unfinished games of player that profile belongs to
    return null;
    }
    
}
