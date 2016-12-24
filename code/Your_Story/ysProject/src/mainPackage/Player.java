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
    
    /**
     *
     * @param ID
     */
    public Player(long ID){////
        playerID = ID;
        profile = new Profile(ID);
        //updateOngoingGames();
    }
    
    //getters / setters

    /**
     *
     * @return
     */
    public long getPlayerID(){return playerID;}

    /**
     *
     * @return
     */
    public Profile getProfile(){return profile;}
        
    /**
     *
     * @param ID
     */
    public void setPlayerID(long ID){playerID = ID;}

    /**
     *
     * @param profile
     */
    public void setProfile(Profile profile){this.profile = profile;}
    //other methods 
    
    /**
     *
     * @param name
     * @param story
     * @return
     */
    public Lobby createLobby (String name, Story story){
        
        long lobbyID = LobbyConnection.createLobby(name,story.getID());
        enterLobby(LobbyConnection.getLobby(lobbyID));
        return LobbyConnection.getLobby(lobbyID);              
    }
    
    /**
     *
     * @param aLobby
     * @return
     */
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

    /**
     *
     * @param aLobby
     * @return
     */
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
    
    /**
     *
     * @param name
     * @param photo
     * @param description
     */
    public void updateProfile(String name, BufferedImage photo, String description){
    //Returns profile of the user. This profile object will include all
    //information about that user, name, photo and the descritipion.
        profile.setDescription(description);
        profile.setImage(photo);
        profile.setName(name);
        profile.updateData();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Lobby> getOngoingGames(){ 
        return ongoing;
    }
    
    /**
     *
     * @return
     */
    public Player updateOngoingGames(){ 
        ongoing = LobbyConnection.getOngoingGamesOfPlayer(playerID);
        return this;
    }
    //Returns the ongoing games of player via database
    //classes.
}
