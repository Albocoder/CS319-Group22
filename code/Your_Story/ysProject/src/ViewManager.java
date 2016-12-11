/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Changelog:
 *==================================================================
 * Description:
 * This class is the one responsible for managing and coordinating
 * the views.
 * */
import java.util.*;

public class ViewManager {
	private ArrayList<Viewable> hiddenViews;
	private LoginView login;
	private LobbyView lobby;
	private HomeView homepage;
	private InGameView ingame;
	private LobbyCreatorView lobbycreator;
	private ProfileView profile;
	
	
	public ViewManager(){
		hiddenViews = new ArrayList<Viewable>();
		login = new LoginView(this);
		login.setVisible(true);
	}
	
	public void showLogin() {
		//show login and destroy the rest
		showLogin();
		hideHomePage(true);
		hideOngoingGame(true);
		hideProfile(true);
		hideLobby(true);
		hideHomePage(true);
	}

	/**
	 * 
	 * @param playersHome
	 */
	public void showHomePage(HomePage playersHome) {
		// TODO - implement ViewManager.showHomePage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aLobby
	 */
	public void showLobby(Lobby aLobby) {
		// TODO - implement ViewManager.showLobby
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aProfile
	 */
	public void showProfile(Profile aProfile) {
		// TODO - implement ViewManager.showProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aLobby
	 */
	public void showOngoingGame(Lobby aLobby) {
		// TODO - implement ViewManager.showOngoingGame
		throw new UnsupportedOperationException();
	}

	public void showLastHidden() {
		// TODO - implement ViewManager.showLastHidden
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param v
	 */
	public void update(Viewable v) {
		// TODO - implement ViewManager.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terminate
	 */
	public void hideLogin(boolean terminate) {
		// TODO - implement ViewManager.hideLogin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terminate
	 */
	public void hideHomePage(boolean terminate) {
		// TODO - implement ViewManager.hideHomePage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terminate
	 */
	public void hideLobby(boolean terminate) {
		// TODO - implement ViewManager.hideLobby
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terminate
	 */
	public void hideProfile(boolean terminate) {
		// TODO - implement ViewManager.hideProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param terminate
	 */
	public void hideOngoingGame(boolean terminate) {
		// TODO - implement ViewManager.hideOngoingGame
		throw new UnsupportedOperationException();
	}
}