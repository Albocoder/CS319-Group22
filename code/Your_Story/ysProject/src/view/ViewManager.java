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
    }

    public void showLogin() {
        //show login and destroy the rest
        login = new LoginView(this);
        hideHomePage(true);
        hideOngoingGame(true);
        hideProfile(true);
        hideLobby(true);
        hideHomePage(true);
    }

    public void showHomePage(/*HomePage*/Object playersHome) {
        homepage = new HomeView(this,playersHome);
    }

    public void showLobby(Lobby aLobby) {
        // TODO - implement ViewManager.showLobby
        throw new UnsupportedOperationException();
    }

    //public void showProfile(Profile aProfile) {
        // TODO - implement ViewManager.showProfile
    //    throw new UnsupportedOperationException();
    //}

    public void showOngoingGame(Lobby aLobby) {
        throw new UnsupportedOperationException();
    }

    public void showCreateLobby(){
        throw new UnsupportedOperationException();
    }

    public void showLastHidden() {
        throw new UnsupportedOperationException();
    }

    public void update(Viewable v) {
        // TODO - implement ViewManager.update
        throw new UnsupportedOperationException();
    }

    public void hideLogin(boolean terminate) {
        login.setVisible(false);
        if(terminate)
            login = null;
        else
            hiddenViews.add(login);
    }

    public void hideHomePage(boolean terminate) {
        // TODO - implement ViewManager.hideHomePage
        throw new UnsupportedOperationException();
    }

    public void hideLobby(boolean terminate) {
        // TODO - implement ViewManager.hideLobby
        throw new UnsupportedOperationException();
    }

    public void hideProfile(boolean terminate) {
        // TODO - implement ViewManager.hideProfile
        throw new UnsupportedOperationException();
    }

    public void hideOngoingGame(boolean terminate) {
        // TODO - implement ViewManager.hideOngoingGame
        throw new UnsupportedOperationException();
    }
    
    public void hideCreateLobby(){
        // TODO - implement ViewManager.showOngoingGame
        throw new UnsupportedOperationException();
    }
}