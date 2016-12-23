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
    
    public Lobby createLobby (Story story){
        
        return new Lobby("sample",0, 10, 00, new ArrayList<Long>(), story.getID());
        
    }
    
    public boolean enterLobby (Lobby aLobby){
       
          boolean check = LobbyConnection.addPlayerToLobby(playerID, aLobby.getID());
          Seat playerSeat =  LobbyConnection.getSeat(playerID);
          ArrayList<Seat> seats = LobbyConnection.getSeats(aLobby.getID());
          int i = 0;
         
          if (check){
             i = seats.indexOf(playerSeat);  
             if (i != -1)
                 return true;
          }
          else
              return false;
          return false; 
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
