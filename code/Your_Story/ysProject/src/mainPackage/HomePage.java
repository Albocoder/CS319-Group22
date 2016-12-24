package mainPackage;

import java.util.ArrayList;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 2.1.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the homepage class that has the data for the home
 * page of a user
 * */

/**
 * First page that a user sees when logging in. 
 * @author kaxell
 */


public class HomePage {
    private static Player myPlayer;
    private ArrayList<Lobby> lobbiesWaiting;
    
    /**
     *
     * @param id
     */
    public HomePage(long id){
        myPlayer = new Player(id);
        lobbiesWaiting = LobbyConnection.getWaitingLobbies();
        myPlayer.getProfile().getIsYours();
    }
    //player will be static when class is created

    /**
     *
     * @return
     */
    public static Player getPlayer(){
        return myPlayer;
    }

    /**
     *
     * @return
     */
    public ArrayList<Lobby> getLobbiesWaiting(){
        return lobbiesWaiting;
    }

    /**
     *
     * @return
     */
    public int getOnlineUsers(){
        return LobbyConnection.getOnlineUsers();
    }

    /**
     *
     */
    public void updateLobbiesWaiting(){
        lobbiesWaiting = LobbyConnection.getWaitingLobbies(); 
    }
}
