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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.*;
import javax.swing.border.EtchedBorder;
import view.*;
import mainPackage.*;
import mainPackage.Character;

public class LobbyView extends JFrame implements Viewable {
    private JScrollPane theSeats;
    private JLabel storyDesc;
    private JLabel timeLeft;
    private JButton startVote;
    private JButton leaveLobby;
    private JComboBox kickPlayer;
    private ViewManager referrer;
    private Story theStory;
    private Seat mySeat;
    
    //newly added
    private VotingHandler voter;
    private Lobby theLobby;
    private JPanel seatsPanel;
    private ArrayList<JPanel> seats;
    
    private final int COLOR_COLD_RAND = 155;
    private final int TIME_MAX = 120;
    private final int BORDER_THICKNESS = 5;
    

    public LobbyView(Lobby aLobby,ViewManager ref){
        if (aLobby == null)
            throw new NullPointerException("Lobby object is nul can't get data");
        logoutOnExitWithDialogue();
        referrer = ref;
        theLobby = aLobby;
        theStory = new Story(theLobby.getID());
        voter = new VotingHandler(theLobby.getID());
        showSeatsWaiting();
        theSeats = new JScrollPane(seatsPanel);
        
        storyDesc = new JLabel(theLobby.getStory().getDescription());
        
        showFreeCharacters();
        kickPlayer = new JComboBox();
        
        //mySeat = new Seat(joinSeat());
        timeLeft = new JLabel(""+TIME_MAX);
    }    
    
    public void startVote(int type) {
        if(type == Lobby.VOTE_START){
            voter.startVoting(type);
        }
    }

    public void startKick(Seat target) {
        voter.startVoting(Lobby.VOTE_KICK,target.getPlayer());
    }

    public void leaveLobby() {
       //do something to leave mySeat
    }
    
    public Lobby getLobby(){return theLobby;}
    
    private long joinSeat(){
        // do something to add yourself to the seat and update the seat table
        return -1;
    }
    
    @Override
    public void terminateView() {
        referrer.showHomePage(null);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }

    @Override
    public void updateView() {
        /*
        ScheduledExecutorService chatExec = Executors.
            newSingleThreadScheduledExecutor();
            
        chatExec.scheduleAtFixedRate(
            new (),5,5,TimeUnit.SECONDS);
        */
    }

    @Override
    public void showView() {
        this.setVisible(true);
    }
    
    private void showFreeCharacters(){
        ArrayList<Character> freeOnes;
        freeOnes = theLobby.getFreeChars();
        JPanel all = new JPanel(new GridLayout(3,freeOnes.size()/3));
        
        JLabel rand = new JLabel();
        rand.setIcon(new ImageIcon("./img/random.jpg"));
        all.add(rand);
        
        for(int i = 0; i<5; i++/*Character c:freeOnes*/){
            
        }
    }
    
    private void showSeatsWaiting(){
        theSeats.removeAll();
        seats.removeAll(seats);
        Random r = new Random();
        int numberOfSeatsOccupied = 0;
        
        for(Seat s:theLobby.getSeats()){
            if(s.getIsOccupied())
                numberOfSeatsOccupied++;
        }
        seatsPanel = new JPanel(new GridLayout(numberOfSeatsOccupied,1));
        for(Seat s:theLobby.getSeats()){
            if(!s.getIsOccupied())
                continue;
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
                BufferedImage playerImg = s.getPlayer().getProfile().getImage();
                Image dimg = playerImg.getScaledInstance(72, 72,Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                icon.setIcon(imageIcon);
                icon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            } catch (Exception e) {
                    e.printStackTrace();
            }

            toFill.add(icon,BorderLayout.WEST);

            JLabel plName = new JLabel(s.getPlayer().getProfile().getName());
            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")).deriveFont(25f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")));
                plName.setFont(customFont);
            } catch (Exception e){/* do nothing */}
            plName.setForeground(Color.WHITE);
            plName.setHorizontalAlignment(JLabel.CENTER);
            plName.setVerticalAlignment(JLabel.NORTH);
            toFill.add(plName,BorderLayout.NORTH);

            /*
            //Maybe useless
            JLabel lobbyQuota = new JLabel(" ");
            lobbyQuota.setForeground(Color.WHITE);
            lobbyQuota.setHorizontalAlignment(JLabel.RIGHT);
            lobbyQuota.setFont(new Font("Times New Roman",Font.PLAIN,14));
            toFill.add(lobbyQuota,BorderLayout.SOUTH);
            */
            
            JLabel storyTimeline = new JLabel("Selected: "+s.getCharacter().getName());
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);

            tmpLobby.add(toFill, BorderLayout.CENTER);
            //lobbies.add(l);
            seatsPanel.add(tmpLobby);
            seats.add(tmpLobby);
        }
        theSeats.updateUI();
    }
    
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                terminateView();
            }
        });
    }
}
