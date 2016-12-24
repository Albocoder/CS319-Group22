package view;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.3.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the lobby view for a lobby that is waiting
 * */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.*;
import javax.swing.border.EtchedBorder;
import view.*;
import mainPackage.*;
import mainPackage.Character;

/**
 *
 * @author Erin Avllazagaj
 */
public class LobbyView extends JFrame implements Viewable {
    private JScrollPane theSeats;
    private JLabel storyDesc;
    private JLabel timeLeft;
    private JButton startVote;
    private JButton leaveLobby;
    private JComboBox<String> kickPlayer;
    private ViewManager referrer;
    private Story theStory;
    private Seat mySeat;
    
    //newly added
    private VotingHandler voter;
    private Lobby theLobby;
    private JPanel seatsPanel;
    private JPanel freeChars;
    private ArrayList<JLabel> freeCharList;
    private int myTime;
    private ArrayList<Player> inGamePlayers;
    
    private ArrayList<JPanel> seats = new ArrayList<JPanel>();
    
    private final int COLOR_COLD_RAND = 155;
    private final int TIME_MAX = 120;
    private final int CHAR_SIZE_W = 70;
    private final int CHAR_SIZE_H = 97;
    
    /**
     *
     * @param aLobby
     * @param ref
     */
    public LobbyView(Lobby aLobby,ViewManager ref){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {System.out.println("Error: "+ex.getMessage());}
        setTitle(aLobby.getName());
        
        if (aLobby == null)
            throw new NullPointerException("Lobby object is null can't get data");
        logoutOnExitWithDialogue();
        setLayout(new BorderLayout());
        
        referrer = ref;
        theLobby = aLobby;
        mySeat = joinSeat();
        theStory = aLobby.getStory();
        voter = new VotingHandler(theLobby.getID());
        
        seatsPanel = new JPanel();
        theSeats = new JScrollPane(seatsPanel);
        showSeatsWaiting();
        theSeats.getVerticalScrollBar().setUnitIncrement(16);
        
        storyDesc = new JLabel("<html>"+theStory.getDescription()+"</html>");
        Dimension d = new Dimension(300,(theStory.getDescription().length()/2));
        storyDesc.setPreferredSize(d);
        
        JPanel theRest = new JPanel(new BorderLayout());
        theRest.setBackground(Color.BLACK);
        JScrollPane storyScroller = new JScrollPane(storyDesc);
        storyScroller.setPreferredSize(new Dimension(320,220));
        storyScroller.getVerticalScrollBar().setUnitIncrement(14);
        storyDesc.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        theRest.add(storyScroller,BorderLayout.NORTH);
        freeChars = new JPanel();
        freeCharList = new ArrayList<>();
        freeChars.setBackground(Color.BLACK);
        
        showFreeCharacters();
        JScrollPane charScroller = new JScrollPane(freeChars);
        charScroller.getVerticalScrollBar().setUnitIncrement(5);
        theRest.add(charScroller,BorderLayout.CENTER);
        
        JPanel botWrapper = new JPanel(new BorderLayout());
        JPanel botup = new JPanel(new FlowLayout());
        JPanel botdown = new JPanel(new FlowLayout());
        botWrapper.setBackground(Color.BLACK);
        botup.setBackground(Color.BLACK);
        botdown.setBackground(Color.BLACK);
        
        myTime = TIME_MAX;
        JLabel timeLeft2 = new JLabel("Time Left: ");
        timeLeft = new JLabel(""+myTime);
        timeLeft.setFont(new Font("Arial",Font.BOLD,16));
        timeLeft.setForeground(Color.RED);
        timeLeft.setBackground(Color.BLACK);
        timeLeft2.setBackground(Color.BLACK);
        timeLeft2.setForeground(Color.WHITE);
        JLabel divider = new JLabel("  ");
        botup.add(divider);
        botup.add(timeLeft2);
        botup.add(timeLeft);
        botup.add(new JLabel("              "));
        startVote = new JButton("Start Game");
        startVote.setBackground(Color.LIGHT_GRAY);
        startVote.setOpaque(true);
        startVote.setForeground(Color.red);
        startVote.setFocusPainted(false);
        startVote.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        botup.add(startVote);
        botup.add(divider);
        botdown.add(divider);
        kickPlayer = new JComboBox();
        kickPlayer.addItem("Kick a player");
        kickPlayer.setBackground(Color.LIGHT_GRAY);
        kickPlayer.setOpaque(true);
        kickPlayer.setForeground(Color.red);
        kickPlayer.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        botdown.add(kickPlayer);
        botdown.add(new JLabel("          "));
        leaveLobby = new JButton("Leave");
        leaveLobby.setBackground(Color.LIGHT_GRAY);
        leaveLobby.setOpaque(true);
        leaveLobby.setForeground(Color.red);
        leaveLobby.setFocusPainted(false);
        leaveLobby.setFont(new Font("Comic Sans MS",Font.BOLD,13));
        botdown.add(leaveLobby);
        botdown.add(divider);
        botWrapper.add(botup,BorderLayout.NORTH);
        botWrapper.add(botdown,BorderLayout.CENTER);
        botWrapper.add(divider,BorderLayout.SOUTH);
        theRest.add(botWrapper,BorderLayout.SOUTH);
        inGamePlayers = new ArrayList<>();
        showKickable();
        updateView();
        //Listeners go here
        kickPlayer.addActionListener(new KickPlayerListener());
        leaveLobby.addActionListener(new LeaveLobbyListener());
        startVote.addActionListener(new StartGameListener());
        freeChars.addMouseListener(new CharacterSelectionMouseListener());
        ///////////////////////////////
        add(theSeats,BorderLayout.CENTER);
        add(theRest,BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    /**
     *
     * @param type
     */

    public void startVote(int type) {
        if(type == Lobby.VOTE_START){
            voter.startVoting(type);
        }
    }

    /**
     *
     * @param target
     */
    public void startKick(Seat target) {
        voter.startVoting(Lobby.VOTE_KICK,target.getPlayer());
    }

    /**
     *
     */
    public void leaveLobby() {
        theLobby.kickPlayer(mySeat.getPlayer());
    }

    //public Lobby getLobby(){return theLobby;}
    
    private Seat joinSeat(){
        return HomePage.getPlayer().enterLobby(theLobby);
    }
    
    @Override
    public void terminateView() {
        referrer.showHomePage(null);
        this.dispose();
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }

    @Override
    public void updateView() {
        ScheduledExecutorService seatExec = Executors.
            newSingleThreadScheduledExecutor();
        
        ScheduledExecutorService timeExec = Executors.
            newSingleThreadScheduledExecutor();
        
        ScheduledExecutorService stateUpdate = Executors.
            newSingleThreadScheduledExecutor();
        
        
        //RUNNING ROUTINES    
        seatExec.scheduleAtFixedRate(
            new SeatUpdater(),3,3,TimeUnit.SECONDS);
        
        timeExec.scheduleAtFixedRate(
            new TimeUpdater(),1,1,TimeUnit.SECONDS);
        
        stateUpdate.scheduleAtFixedRate(
            new StatusUpdater(),4,4,TimeUnit.SECONDS);
    }

    @Override
    public void showView() {
        this.setVisible(true);
    }
    private void kickPlayer(Player p){
        if(inGamePlayers.get(0).getPlayerID()==HomePage.getPlayer().getPlayerID())
            theLobby.kickPlayer(p);
        else
            JOptionPane.showMessageDialog(null, 
                "A-a-aaaa... You don't have permission to do that!",
                "As if it was so ez!!!", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("./img/operationDenied.png"));
    }
    private void startGame(){
        if(inGamePlayers.get(0).getPlayerID()==HomePage.getPlayer().getPlayerID()){
            theLobby.setState(Lobby.LOBBY_INGAME);
            referrer.showOngoingGame(theLobby);
        }
        else{
            JOptionPane.showMessageDialog(null,
                "A-a-aaaa... You don't have permission to do that!",
                "As if it was so ez!!!", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("./img/operationDenied.png"));
        }
    }
    private void showFreeCharacters(){
        ArrayList<Character> freeOnes;
        freeOnes = theLobby.getFreeChars();
        freeCharList.removeAll(freeCharList);
        freeChars.removeAll();
        freeChars.setLayout(new GridLayout((freeOnes.size()/3),3));
        JLabel rand = new JLabel();
        try {
            /////// get image and resize it///////////////////////////////////////////////
            FileInputStream fis = new FileInputStream(new File("./img/random.png"));
            Image gameIcon = ImageIO.read(fis);
            ImageIcon imageIcon = new ImageIcon(gameIcon);
            //////////////////////////////////////////////////////////////////////////////
            rand.setIcon(imageIcon);
        } catch (Exception e) {System.out.println("Error: "+e.getMessage());}
        rand.setBorder(BorderFactory.createLineBorder(Color.BLUE,2, true));
        freeCharList.add(rand);
        freeChars.add(rand);
        for(Character c:freeOnes){
            JLabel tmp = new JLabel(c.getName());
            try {
                /////// get image and resize it///////////////////////////////////////////////
                FileInputStream fis = new FileInputStream(new File("./img/dtb.jpg"));
                Image gameIcon = ImageIO.read(fis).getScaledInstance(CHAR_SIZE_H,
                        CHAR_SIZE_W-c.getName().length(), BufferedImage.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(gameIcon);
                //////////////////////////////////////////////////////////////////////////////
                tmp.setIcon(imageIcon);
            } catch (Exception e) {}
            tmp.setToolTipText(c.getDescription());
            tmp.setBorder(BorderFactory.createLineBorder(Color.BLUE,2, true));
            freeCharList.add(tmp);
            freeChars.add(tmp);
        }
    }
    
    private void showSeatsWaiting(){
        seats.removeAll(seats);
        seatsPanel.removeAll();
        Random r = new Random();
        int numberOfSeatsOccupied = 0;
        theLobby.updateSeats();
        for(Seat s:theLobby.getSeats()){         
            if(s.getIsOccupied())
                numberOfSeatsOccupied++;
        }
        seatsPanel.setLayout(new GridLayout(numberOfSeatsOccupied,1));
        for(Seat s:theLobby.getSeats()){
            if(s == null)
                continue;
            if(!s.getIsOccupied())
                continue;
            JPanel emptyPanel = new JPanel();
            Color c = new Color(r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND));
            emptyPanel.setBackground(c);
            JPanel tmpLobby = new JPanel(new BorderLayout());
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
                BufferedImage playerImg = ImageIO.read(new File("./img/itsygoAlternate.jpg"))/*s.getPlayer().getProfile().getImage()*/;
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
            JLabel lobbyQuota = new JLabel("Locked in");
            lobbyQuota.setForeground(Color.WHITE);
            lobbyQuota.setHorizontalAlignment(JLabel.RIGHT);
            lobbyQuota.setFont(new Font("Times New Roman",Font.PLAIN,14));
            toFill.add(lobbyQuota,BorderLayout.SOUTH);
            */
            JLabel storyTimeline;
            if(s.getCharacter()!=null)
                storyTimeline = new JLabel("Selected: "+s.getCharacter().getName());
            else
                storyTimeline = new JLabel("Selected: random");
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);

            tmpLobby.add(toFill, BorderLayout.CENTER);
            
            seatsPanel.add(tmpLobby);
            seats.add(tmpLobby);
        }
        seatsPanel.updateUI();
    }
    private void checkStatus(){
        int newState = LobbyConnection.getState(theLobby.getID());
        this.setState(newState);
        if(newState == Lobby.LOBBY_INGAME){
            referrer.showOngoingGame(this.theLobby);
        }
    }
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                leaveLobby();
                hideView();
                referrer.showHomePage(null);
            }
        });
    }
    private void showKickable(){
        inGamePlayers.removeAll(inGamePlayers);
        while( kickPlayer.getItemCount() > 1)
            kickPlayer.removeItemAt(1);
        for(Seat s: theLobby.getSeats())
            if(s.getPlayer() != null){
                inGamePlayers.add(s.getPlayer());
                kickPlayer.addItem(s.getPlayer().getProfile().getName());
            }
    }
    private class SeatUpdater implements Runnable{
        @Override
        public void run(){
            showSeatsWaiting();
            showFreeCharacters();
            showKickable();
        }
    }
    private class TimeUpdater implements Runnable{
        Random r = new Random();
        @Override
        public void run(){
            if (myTime > 0)
                myTime--;
            else{
                //cage in the player
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                kickPlayer.setEnabled(false);
                leaveLobby.setEnabled(false);
                if(mySeat.getCharacter()==null){
                    mySeat.setCharacter(theLobby.getFreeChars().get(r.nextInt(theLobby.getFreeChars().size())));
                }
            }
            timeLeft.setText(""+myTime);
        }
    }
    private class StatusUpdater implements Runnable{
        @Override
        public void run(){
            checkStatus();
        }
    }
    
    private class KickPlayerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                kickPlayer(inGamePlayers.get(kickPlayer.getSelectedIndex()-1));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }
    
    private class LeaveLobbyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            leaveLobby();
            referrer.showHomePage(null);
        }
    }
    
    private class StartGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            startGame();
        }
    }
    private class CharacterSelectionMouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e){
            Character selected = theLobby.getFreeChars().get(
                    freeCharList.indexOf(
                            (JLabel)(e.getComponent().getComponentAt(e.getPoint()))
                    ));
            mySeat.setCharacter(selected);
            showSeatsWaiting();
        }
    }
}
