package view;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 2.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Changelog: 
 * 1) Added some more classes to increase efficiency with 
 *  tradeoff of memory. 
 * 2) Added some constants, listeners and private functions;reduced
 * the constructor
 *==================================================================
 * Description:
 * This class is the homepage view that the user will see when 
 * logged in.
 * */
import java.awt.event.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.*;
import view.*;
import mainPackage.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author black-knight
 */
public class LobbyView extends JFrame implements Viewable {
    
    private JScrollPane theSeats;
    private JLabel storyDesc;
    private JLabel timeLeft;
    private JButton startVote;
    private JButton leaveLobby;
    private JComboBox kickPlayer;
    private ViewManager referrer;
    //private ArrayList<Seat> seats;
    private Story theStory;
    //private Seat mySeat;
    private VotingHandler voter;
    private Lobby theLobby;

    public LobbyView(Lobby aLobby,ViewManager ref){
        referrer = ref;
        theLobby = aLobby;
        //theStory = new Story(theLobby.getID());
        voter = new VotingHandler(theLobby.getID());
        
    }    
    
    public void startVote(int type) {
        if(type == Lobby.VOTE_START){
            voter.startVoting(type);
        }
    }

    public void startKick(/*Player target*/) {
        voter.startVoting(Lobby.VOTE_KICK,/*target*/null);
    }

    public void leaveLobby() {
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
        this.setVisible(true);
    }
    
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null, 
                    "Are you sure to exit the game?", "Really?! Leaving just now?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,new ImageIcon("./img/leaving.png")) == JOptionPane.YES_OPTION){
                    terminateView();
                }
            }
        });
    }
}
