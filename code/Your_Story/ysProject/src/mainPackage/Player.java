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
    private ArrayList<Lobby> ongoing;
    
    public Player(long ID){////
        playerID = ID;
        profile = new Profile(ID);
        updateOngoingGames();
    }
    
    //getters / setters
    public long getPlayerID(){return playerID;}
    public Profile getProfile(){return profile;}
        
    public void setPlayerID(long ID){playerID = ID;}
    public void setProfile(Profile profile){this.profile = profile;}
    //other methods 
    
    public Lobby createLobby (Story story){
        
        return new Lobby("sample",0, 10, 00, new ArrayList<Long>(), story.getID());
        
    }
    
    public boolean enterLobby (Lobby aLobby){
        ArrayList<Seat> seats = aLobby.getSeats();
        boolean check = false;
        for (int i = 0; i < seats.size(); i++ ){
            if (!seats.get(i).getIsOccupied()){
                check = true;
                seats.get(i).addPlayer(playerID);
                LobbyConnection.addPlayerToLobby(playerID, aLobby.getID());
                break;
            }
        }
        if (check == false)
            return false;
        else
            return true;
        
    }
  
    public void updateProfile(String name, BufferedImage photo, String description){
    //Returns profile of the user. This profile object will include all
    //information about that user, name, photo and the descritipion.
        profile.setDescription(description);
        profile.setImage(photo);
        profile.setName(name);
        profile.updateData();
    }
    
    public ArrayList<Lobby> getOngoingGames(){ 
        return ongoing;
    }
    
    public void updateOngoingGames(){ 
        ongoing = LobbyConnection.getOngoingGamesOfPlayer(playerID);
    }
    //Returns the ongoing games of player via database
    //classes.
}
