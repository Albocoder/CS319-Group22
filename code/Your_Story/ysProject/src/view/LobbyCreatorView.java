package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.1.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the one responsible for creating new lobbies with a 
 * name and a story
 * */
import mainPackage.*;

public class LobbyCreatorView extends JFrame implements Viewable{
    private JScrollPane stories;
    private JButton creator;
    private Story[] availableStories;
    private ViewManager referrer;
    
    //added new stuff
    private JTextField lobbyName;
    
    public LobbyCreatorView(){
        
    }
    
    public void createLobby(Story s) {
    }

    public void getStories() {
    }
        
    @Override
    public void terminateView() {
    }
    @Override
    public void hideView() {
    }
    @Override
    public void updateView() {
    }
    @Override
    public void showView() {
    }
}
