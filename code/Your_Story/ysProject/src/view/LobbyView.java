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
 * This class is the lobby view for a lobby that is waiting
 * */
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.*;
import javax.swing.border.EtchedBorder;
import view.*;
import mainPackage.*;

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
    
    //newly added
    private VotingHandler voter;
    private Lobby theLobby;
    private JPanel seatsPanel;
    private ArrayList<JPanel> seats;
    
    private final int PROFILE_SIZE = 230;
    private final int COLOR_COLD_RAND = 155;
    private final int COLOR_RAND = 255;
    private final int BORDER_THICKNESS = 5;
    

    public LobbyView(Lobby aLobby,ViewManager ref){
        referrer = ref;
        theLobby = aLobby;
        theStory = new Story(theLobby.getID());
        voter = new VotingHandler(theLobby.getID());
        theSeats = new JScrollPane();
        //seatsPanel = new JPanel(new GridLayout(/*theLobby.getSeats().size()*/50.2,1));
    }    
    
    public void startVote(int type) {
        if(type == Lobby.VOTE_START){
            voter.startVoting(type);
        }
    }

    public void startKick(Seat target) {
        voter.startVoting(Lobby.VOTE_KICK,null/*target.getPlayer()*/);
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
    
    private void showWaitingLobbies(){
        theSeats.removeAll();
        seats.removeAll(seats);
        Random r = new Random();
        
        for(Seat s:theLobby.getSeats()){
            JPanel emptyPanel = new JPanel();
            Color c = new Color(r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND));
            emptyPanel.setBackground(c);
            JPanel tmpLobby = new JPanel(new BorderLayout());
            tmpLobby.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            tmpLobby.setBackground(c);
            //this sets up a padding from the border to the components
            tmpLobby.add(emptyPanel, BorderLayout.NORTH);
            tmpLobby.add(emptyPanel, BorderLayout.SOUTH);
            tmpLobby.add(emptyPanel, BorderLayout.EAST);
            tmpLobby.add(emptyPanel, BorderLayout.WEST);

            //here add the specifics for each lobby
            JPanel toFill = new JPanel(new BorderLayout());
            toFill.setBackground(c);

            //adding the icon to the left
            JLabel icon = new JLabel();

            try {
                    /////// get image and resize it///////////////////////////////////////////////
                    FileInputStream fis = new FileInputStream(new File("./img/itsygoAlternate.jpg")/*get database image*/);
                    BufferedImage gameIcon = ImageIO.read(fis);
                    Image dimg = gameIcon.getScaledInstance(72, 72,Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    //////////////////////////////////////////////////////////////////////////////
                    icon.setIcon(imageIcon);
                    icon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            } catch (Exception e) {
                    e.printStackTrace();
            }

            toFill.add(icon,BorderLayout.WEST);

            JLabel lobbyName = new JLabel("Best Lobby"/*l.getName()*/);
            try {
                    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")).deriveFont(25f);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")));
                    lobbyName.setFont(customFont);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            lobbyName.setForeground(Color.WHITE);
            lobbyName.setHorizontalAlignment(JLabel.CENTER);
            lobbyName.setVerticalAlignment(JLabel.NORTH);
            toFill.add(lobbyName,BorderLayout.NORTH);

            JLabel lobbyQuota = new JLabel("Player(s): "+"4"/*l.getQuota()*/+"/"+"5"/*l.getSeats().size()*/);
            lobbyQuota.setForeground(Color.WHITE);
            lobbyQuota.setHorizontalAlignment(JLabel.RIGHT);
            lobbyQuota.setFont(new Font("Times New Roman",Font.PLAIN,14));
            toFill.add(lobbyQuota,BorderLayout.SOUTH);

            JLabel storyTimeline = new JLabel("Timeline: "/*+l.getStory().getTimeline()*/);
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);

            tmpLobby.add(toFill, BorderLayout.CENTER);

            //adding the listeners for the components
            //tmpLobby.addMouseListener(l);
            theSeats.add(tmpLobby,BorderLayout.CENTER);
            //lobbies.add(l);
            seats.add(tmpLobby);
        }
        theSeats.updateUI();
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
