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
    private long ID;//
    
    /*public Profile( String name, BufferedImage photo, String description, boolean isYours){
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.isYours = isYours;
        finishedGames = new ArrayList<Lobby>();
    
    }*/
    public Profile(long ID,boolean isYours){
        this.ID = ID;
        this.isYours = isYours;
        retrieveData();
    }
    
       
    //getters / setters 
    
    public String getName(){return name;}
    public BufferedImage getImage(){return photo;}
    public String getDescription(){return description;}
    public ArrayList<Lobby> getFinishedGames(){return finishedGames;}
    public boolean getIsYours(){return isYours;}
   
    public void setName(String name){
        this.name = name;
        updateData();
    }
    public void setImage(BufferedImage photo){
        this.photo = photo;
        updateData();
    }
        
    public void setDescription(String description){
        this.description = description;
        updateData();
    }
    public void setFinishedGames(ArrayList<Lobby> finishedGames){
        this.finishedGames = finishedGames;
        updateData();
    }
    public void setIsYours(boolean isYours){
        this.isYours = isYours;
        updateData();
    }
    
    public void retrieveData(){
        ProfileConnection.updateProfileInClient(this);
    //Retrieves the data of profile. This will sync the variables of object
    //with the data in the database, therefore before showing a profile to the users, this method will
    //make it certain that the updated data will be showed to the user.
    
    }
    
    public void updateData(){//
        ProfileConnection.updateProfileInDatabase(this);

    }
    
    public Player createPlayer (long ID, boolean isYours){
        return new Player(ID);
    }
    
    public ArrayList<Lobby> viewUnfinishedGames(){
    //Returns unfinished games of player that profile belongs to
    return ProfileConnection.getFinishedGames(ID);
    }
    
}
