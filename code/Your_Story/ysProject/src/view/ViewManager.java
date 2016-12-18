package view;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 2.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Changelog: Added the lobby creator view and its methods.
 * We forgot that in design!
 *==================================================================
 * Description:
 * This class is the one responsible for managing and coordinating
 * the views.
 * */
import java.util.*;
import mainPackage.*;

public class ViewManager {
    //////// we forgot these too in the design ///////////////////
    private ArrayList<Viewable> hiddenViews;
    private LoginView login;
    private LobbyView lobby;
    private HomeView homepage;
    private InGameView ingame;
    //private LobbyCreatorView lobbycreator;
    //private ProfileView profile;
    //////////////////////////////////////////////////////////////

    public ViewManager(){
        hiddenViews = new ArrayList<Viewable>();
        showLogin();
    }

    public void showLogin() {
        //show login and destroy the rest
        if(login == null)
            login = new LoginView(this);
        else
            login.setVisible(true);
        hideHomePage(true);
        hideOngoingGame(true);
        hideLobby(true);
    }

    public void showHomePage(HomePage playersHome) {
        if(homepage == null && playersHome != null)
            homepage = new HomeView(this,playersHome);
        else if(homepage!=null)
            homepage.setVisible(true);
        else
            throw new UnsupportedOperationException();
        hideLogin(false);
        hideOngoingGame(false);
        hideLobby(false);
    }

    public void showLobby(Lobby aLobby) {
        if(lobby == null && aLobby != null)
            lobby = new LobbyView(aLobby,this);
        else if(lobby != null)
            lobby.setVisible(true);
        else
            throw new UnsupportedOperationException();
        hideLogin(false);
        hideOngoingGame(false);
        hideHomePage(false);
    }

    public void showOngoingGame(Lobby aLobby) {
        if(ingame == null && aLobby != null)
            ingame = new InGameView(aLobby,this);
        else if(ingame != null)
            ingame.setVisible(true);
        else
            throw new UnsupportedOperationException();
        hideLogin(false);
        hideLobby(false);
        hideHomePage(false);
    }

    public void showCreateLobby(){
        throw new UnsupportedOperationException();
    }

    public void showLastHidden() {
        if(hiddenViews.size()>=1)
            hiddenViews.remove(hiddenViews.size()-1).showView();
    }

    public void update(Viewable v) {
        // TODO - implement ViewManager.update
        throw new UnsupportedOperationException();
    }

    public void hideLogin(boolean terminate) {
        if(login == null)
            return;
        login.setVisible(false);
        if(terminate)
            login = null;
        else
            hiddenViews.add(login);
    }

    public void hideHomePage(boolean terminate) {
        if(homepage == null)
            return;
        homepage.setVisible(false);
        if(terminate)
            homepage = null;
        else
            hiddenViews.add(homepage);
    }

    public void hideLobby(boolean terminate) {
        if(lobby == null)
            return;
        lobby.setVisible(false);
        if(terminate)
            lobby = null;
        else
            hiddenViews.add(lobby);
    }

    public void hideProfile(boolean terminate) {
        // TODO - implement ViewManager.hideProfile
        throw new UnsupportedOperationException();
    }

    public void hideOngoingGame(boolean terminate) {
        if(ingame == null)
            return;
        ingame.setVisible(false);
        if(terminate)
            ingame = null;
        else
            hiddenViews.add(ingame);
    }
    
    public void hideCreateLobby(){
        // TODO - implement ViewManager.showOngoingGame
        throw new UnsupportedOperationException();
    }
}