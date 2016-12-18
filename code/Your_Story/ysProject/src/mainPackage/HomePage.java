package mainPackage;

import java.util.ArrayList;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 2.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the homepage class that has the data for the home
 * page of a user
 * */

public class HomePage {
    private static Player myPlayer;
    private ArrayList<Lobby> lobbiesWaiting;
    
    public HomePage(long id){
        myPlayer = new Player(id);
        lobbiesWaiting = LobbyConnection.getWaitingLobbies();
        myPlayer.getProfile().getIsYours();
    }
    public static Player getPlayer(){
        return myPlayer;
    }
    public ArrayList<Lobby> getLobbiesWaiting(){
        return lobbiesWaiting;
    }
    public int getOnlineUsers(){
        return LobbyConnection.getOnlineUsers();
    }
    public void updateLobbiesWaiting(){
        lobbiesWaiting = LobbyConnection.getWaitingLobbies();
    }
}
