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
public class Player {
    
    private long playerID;
    private Profile profile; 
    
    public Player(long ID){//
        playerID = ID;
        profile = new Profile(ID);
    }
    
    //getters / setters
    public long getPlayerID(){return playerID;}
    
    public void setPlayerID(long ID){playerID = ID;}
    
    //other methods 
    
    public Lobby createLobby (Story story){
    //Creates a lobby with given story. It will get the data
    //given by user interface and create the lobby in the database via database connection classes.
    return null;
    }
    
    public boolean enterLobby (Lobby aLobby){
    //Adds user into the given lobby if it has enough
    //quota, it returns a boolean that shows if action was successful or not. This boolean will be
    //returned by database classes that first will check if there’s still an empty seat, or is the game on
    //the state that still let new players to join.
    return false;
    }
    
    public Profile getProfile(){
    //Returns profile of the user. This profile object will include all
    //information about that user, name, photo and the descritipion.
    return null;}
    
    public void updateProfile(String name, BufferedImage photo, String description){
    //Returns profile of the user. This profile object will include all
    //information about that user, name, photo and the descritipion.
    }
    
    public ArrayList<Lobby> getOngoingGames(){ 
    return null;}
    //Returns the ongoing games of player via database
    //classes.
    
    
    
    
    
   
    
}
