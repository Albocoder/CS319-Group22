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
        //updateOngoingGames();
    }
    
    //getters / setters
    public long getPlayerID(){return playerID;}
    public Profile getProfile(){return profile;}
        
    public void setPlayerID(long ID){playerID = ID;}
    public void setProfile(Profile profile){this.profile = profile;}
    //other methods 
    
    public Lobby createLobby (String name, Story story){
        
        long lobbyID = LobbyConnection.createLobby(name,story.getID());
        enterLobby(LobbyConnection.getLobby(lobbyID));
        return LobbyConnection.getLobby(lobbyID);              
    }
    
    public boolean enterLobby (Lobby aLobby){
       
          boolean check = LobbyConnection.addPlayerToLobby(playerID, aLobby.getID());
          ArrayList<Seat> seats = LobbyConnection.getSeats(aLobby.getID());
          
          if (check){
            for (int i = 0; i < seats.size(); i++)
            {
                if (seats.get(i).getPlayerID() == playerID)
                    return true;
            }
          }
          return false;
    }
    public boolean exitLobby (Lobby aLobby){
        LobbyConnection.removePlayerFromLobby(playerID,aLobby.getID());
        ArrayList<Seat> seats = LobbyConnection.getSeats(aLobby.getID());
        for (int i = 0; i < seats.size(); i++)
            {
                if (seats.get(i).getPlayerID() == playerID)
                return false;
            }
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
    
    public Player updateOngoingGames(){ 
        ongoing = LobbyConnection.getOngoingGamesOfPlayer(playerID);
        return this;
    }
    //Returns the ongoing games of player via database
    //classes.
}
