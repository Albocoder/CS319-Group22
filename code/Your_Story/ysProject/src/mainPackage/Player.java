
package mainPackage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author kaxell
 * Constructor class for a player.
 * 
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

    /**private
     *private
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
     * Enters a lobby if there is an empty seat, and assings the player. 
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
     * Removes the player from the "aLobby" if the player is in that lobby
     * and the seat of that player turns empty
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
     * Updates everything about the player in database after editing. 
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
     * 
     * @return ongoing games 
     */
    public ArrayList<Lobby> getOngoingGames(){ 
        return ongoing;
    }
    
    /**
     * Returns the ongoing games of player via database classes
     * @return
     */
    public Player updateOngoingGames(){ 
        ongoing = LobbyConnection.getOngoingGamesOfPlayer(playerID);
        return this;
    }
    
}
