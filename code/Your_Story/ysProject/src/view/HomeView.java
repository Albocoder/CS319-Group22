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
 *==================================================================
 *TODO:
 *  Add the Homepage implementation and all the information
 *  gathering from online database
 *
 * */
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.Border;
import mainPackage.*;

//HomePage.getPlayer().getProfile().setDescription("1234");
//HomePage.getPlayer().getProfile().updateData();

@SuppressWarnings("serial")
public class HomeView extends JFrame implements Viewable{

    private HomePage mainData;
    private ViewManager referrer;
    private JScrollPane onWaitLobbies;
    private JLabel profilePic;
    private JButton logoutButton;
    private JButton viewFinished;
    private JComboBox<String> onGoing;
    private JButton createLobby;
    private JLabel onlineUsers;

    //newly added classes
    private ArrayList<JPanel> lobbiesPanels;
    //private ArrayList<Lobby> lobbies;
    private JPanel lobbiesContainer;
    private JPanel theRest;
    //private Player loggedInPlayer;
    
    //constants
    private final int PROFILE_SIZE = 230;
    private final int COLOR_COLD_RAND = 155;
    private final int COLOR_RAND = 255;
    private final int BORDER_THICKNESS = 5;
    private final String LOGOUT_IMG = "./img/logout.png";
    private final String FINISHED_IMG = "./img/finished.png";
    private final String CREATE_IMG = "./img/create.png";

    
    public HomeView(HomePage data,ViewManager ref){
        setTitle("Your Story - Home Page");
        logoutOnExitWithDialogue();
        Random r = new Random();
        getContentPane().setBackground(new Color(0, 0, 0));
        referrer = ref;
        mainData = data;
        lobbiesPanels = new ArrayList<>();
        //lobbies = new ArrayList<Lobby>();
        lobbiesContainer = new JPanel(new GridLayout(
                mainData.getLobbiesWaiting().size(),1));
        
        showWaitingLobbies();
        onWaitLobbies = new JScrollPane(lobbiesContainer);
        onWaitLobbies.getVerticalScrollBar().setUnitIncrement(16);
        profilePic = new JLabel();

        showProfilePic();

        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.LIGHT_GRAY);
        logoutButton.setOpaque(true);
        logoutButton.setForeground(Color.red);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        try {
                /////// get image and resize it///////////////////////////////////////////////
                FileInputStream fis = new FileInputStream(new File(LOGOUT_IMG));
                Image gameIcon = ImageIO.read(fis);
                ImageIcon imageIcon = new ImageIcon(gameIcon);
                //////////////////////////////////////////////////////////////////////////////
                logoutButton.setIcon(imageIcon);
        } catch (Exception e) {
                e.printStackTrace();
        } 

        viewFinished = new JButton("Finished Games"); 
        viewFinished.setBackground(Color.LIGHT_GRAY);
        viewFinished.setOpaque(true);
        viewFinished.setForeground(Color.red);
        viewFinished.setFocusPainted(false);
        viewFinished.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        try {
            /////// get image and resize it///////////////////////////////////////////////
            FileInputStream fis = new FileInputStream(new File(FINISHED_IMG));
            Image gameIcon = ImageIO.read(fis);
            ImageIcon imageIcon = new ImageIcon(gameIcon);
            //////////////////////////////////////////////////////////////////////////////
            viewFinished.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        onGoing = new JComboBox<String>();
        onGoing.setBackground(Color.LIGHT_GRAY);
        onGoing.setOpaque(true);
        onGoing.setForeground(Color.red);
        onGoing.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        onGoing.addItem("Ongoing Games");
        showOngoing();

        createLobby = new JButton("Create Lobby");
        try {
                /////// get image and resize it///////////////////////////////////////////////
                FileInputStream fis = new FileInputStream(new File(CREATE_IMG));
                Image gameIcon = ImageIO.read(fis);
                ImageIcon imageIcon = new ImageIcon(gameIcon);
                //////////////////////////////////////////////////////////////////////////////
                createLobby.setIcon(imageIcon);
        } catch (Exception e) {
                e.printStackTrace();
        }
        createLobby.setBackground(Color.LIGHT_GRAY);
        createLobby.setOpaque(true);
        createLobby.setForeground(Color.red);
        createLobby.setFocusPainted(false);

        onlineUsers = new JLabel();
        showOnlineUsers();
        onlineUsers.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        onlineUsers.setBackground(Color.BLACK);
        onlineUsers.setForeground(Color.GREEN);

        theRest = new JPanel(new GridLayout(4,1));
        JPanel nullOne = new JPanel();
        nullOne.setBackground(Color.BLACK);
        theRest.setBackground(Color.BLACK);

        theRest.add(profilePic);

        JPanel finOngoing = new JPanel(new GridLayout(3,1));
        JPanel finishedPan = new JPanel(new FlowLayout());
        JPanel goingOn = new JPanel(new FlowLayout());
        goingOn.setBackground(Color.BLACK);
        finishedPan.setBackground(Color.BLACK);

        goingOn.add(onGoing);
        finishedPan.add(viewFinished);

        finOngoing.add(finishedPan);
        finOngoing.add(new JLabel());
        finOngoing.add(goingOn);

        finOngoing.setBackground(Color.BLACK);

        theRest.add(finOngoing);

        //the pannel for Creating a game
        JPanel logCreate = new JPanel(new GridLayout(3,1));
        JPanel createPan = new JPanel(new FlowLayout());
        createPan.add(createLobby);
        createPan.setBackground(Color.BLACK);

        logCreate.add(new JLabel());
        logCreate.add(new JLabel());
        logCreate.add(createPan);
        logCreate.setBackground(Color.BLACK);

        theRest.add(logCreate);

        JPanel usersPan = new JPanel(new GridLayout(4,1));
        JPanel logoutPan = new JPanel(new BorderLayout());
        logoutPan.setBackground(Color.BLACK);
        usersPan.setBackground(Color.BLACK);

        logoutPan.add(logoutButton,BorderLayout.CENTER);
        logoutPan.add(new JLabel("  "),BorderLayout.WEST);
        logoutPan.add(new JLabel("  "),BorderLayout.EAST);

        usersPan.add(new JLabel());
        usersPan.add(onlineUsers);
        usersPan.add(logoutPan);
        usersPan.add(new JLabel());

        theRest.add(usersPan);

        // add the listeners
        lobbiesContainer.addMouseListener( new WaitingLobbiesMouseListener() );
        profilePic.addMouseListener(new ProfilePicMouseListener());
        viewFinished.addActionListener(new FinishedGamesListener());
        //onGoing.addItemListener(new OngoingGamesItemListener());
        onGoing.addActionListener(new OngoingActionListener());
        createLobby.addActionListener(new CreateLobbyListener());
        logoutButton.addActionListener(new LogoutListener());

        //create a big panel to add the created panels
        JPanel allTheThings = new JPanel( new BorderLayout());
        allTheThings.setPreferredSize(new Dimension(550,600));
        //add the lobbies and the rest
        allTheThings.add(onWaitLobbies,BorderLayout.CENTER);
        allTheThings.add(theRest,BorderLayout.EAST);

        //add the big panel to the JFrame
        add(allTheThings);
        updateView();
        pack();
        setVisible(true);
    }

    public void logout() {
        referrer.hideLogin(true);
        AccessHandler.logOut();
        referrer.showLogin();
    }
    public void createLobby() {
        //show creationUI(loggedInPlayer)
        //hide this!
    }
    public void joinLobby(Lobby aLobby){
        //referrer.showOngoingGame(aLobby,referrer);
        if(aLobby.getState() == Lobby.LOBBY_INGAME){
            referrer.showOngoingGame(aLobby);
        }
        else{
            referrer.showLobby(aLobby);
        }
    }

    @Override
    public void terminateView() {
        AccessHandler.logOut();
        System.exit(0);
    }
    @Override
    public void hideView() {
        this.setVisible(false);
    }
    @Override
    public void updateView() {
        //showProfilePic();
        ScheduledExecutorService lobbyexec = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService ongoingExec = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService onlinePlayersExec = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService profilePicExec = Executors.newSingleThreadScheduledExecutor();
        
        ///////////// TODO - Change the parameters
        lobbyexec.scheduleAtFixedRate(new LobbyUpdater(),7,7,TimeUnit.SECONDS);
        ongoingExec.scheduleAtFixedRate(new OngoingUpdater(),11,15,TimeUnit.MINUTES);
        onlinePlayersExec.scheduleAtFixedRate(new OnlinePlayersUpdater(),17,17,TimeUnit.SECONDS);
        profilePicExec.scheduleAtFixedRate(new ProfilePicUpdater(),10,15,TimeUnit.MINUTES);
    }
    @Override
    public void showView() {
        this.setVisible(true);
    }
    
    // functions for the UI 
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit the game?", "Really?! Leaving just now?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,new ImageIcon("./img/leaving.png")) == JOptionPane.YES_OPTION){
                    terminateView();
                }
            }
        });
    }
    private void showProfilePic(){
        try {
            Random r = new Random();
            /////// get image and resize it///////////////////////////////////////////////
            //get image from 
            FileInputStream fis = new FileInputStream(new File("./img/itsygoAlternate.jpg"));
            BufferedImage gameIcon = ImageIO.read(fis);
            Image dimg = gameIcon.getScaledInstance(PROFILE_SIZE,PROFILE_SIZE,Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            //////////////////////////////////////////////////////////////////////////////
            profilePic.setIcon(imageIcon/*mainData.getPlayer().getProfile().getPhoto()*/);
            profilePic.setBorder(BorderFactory.createLineBorder(
                new Color(r.nextInt(COLOR_COLD_RAND),
                    r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND))
                    ,BORDER_THICKNESS));
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    private void showOngoing(){
        while( onGoing.getItemCount() > 1)
            onGoing.removeItemAt(1);
        for(Lobby l:mainData.getPlayer().updateOngoingGames().getOngoingGames())
            onGoing.addItem(l.getName());
    }
    private void showOnlineUsers(){
        onlineUsers.setText("        "+"Online users: "+
                mainData.getOnlineUsers());
    }
    private void showWaitingLobbies(){
        lobbiesContainer.removeAll();
        lobbiesPanels.removeAll(lobbiesPanels);
        Random r = new Random();
        for(Lobby l:mainData.getLobbiesWaiting()){
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
                    FileInputStream fis = new FileInputStream(new File("./img/castleBlack.jpg")/*get database image*/);
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

            JLabel lobbyName = new JLabel(l.getName());
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

           /*
            JLabel lobbyQuota = new JLabel(""+l.getSeats().size());
            lobbyQuota.setForeground(Color.WHITE);
            lobbyQuota.setHorizontalAlignment(JLabel.RIGHT);
            lobbyQuota.setFont(new Font("Times New Roman",Font.PLAIN,14));
            toFill.add(lobbyQuota,BorderLayout.SOUTH);
            */
            JLabel storyTimeline = new JLabel("Timeline: "+l.getStory().getTimeline());
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);

            tmpLobby.add(toFill, BorderLayout.CENTER);

            //adding the listeners for the components
            //tmpLobby.addMouseListener(l);
            lobbiesContainer.add(tmpLobby,BorderLayout.CENTER);
            //lobbies.add(l);
            lobbiesPanels.add(tmpLobby);
        }
        lobbiesContainer.updateUI();
    }
    
    //listener classes
    private class WaitingLobbiesMouseListener extends MouseAdapter{
            Color c;
            JPanel pressedPanel;
            JPanel pressedPanel2;
            JPanel pressedPanel3;
            public void mousePressed(MouseEvent e){
                pressedPanel = ((JPanel)e.getComponent().getComponentAt(e.getPoint()));
                pressedPanel2 = (JPanel)((JPanel)e.getComponent().getComponentAt(e.getPoint())).getComponent(0);
                pressedPanel3 = (JPanel)((JPanel)e.getComponent().getComponentAt(e.getPoint())).getComponent(1);
                c = pressedPanel.getBackground();
                Random r = new Random();
                Color col = Color.WHITE;
                pressedPanel.setBackground(col);
                pressedPanel2.setBackground(col);
                pressedPanel3.setBackground(col);
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                pressedPanel.setBackground(c);
                pressedPanel2.setBackground(c);
                pressedPanel3.setBackground(c);
                //JPanel clickedPanel = (JPanel)((JPanel)e.getComponent().getComponentAt(e.getPoint())).getComponent(1);
                //JLabel lobbyIcon = (JLabel)(clickedPanel.getComponent(0));
                //JLabel lobbyTitle = (JLabel)(clickedPanel.getComponent(1));
                //JLabel lobbyPlayerCount = (JLabel)(clickedPanel.getComponent(2));
                //JLabel lobbyTimeline = (JLabel)(clickedPanel.getComponent(3));
                //System.out.println(lobbyIcon.getIcon().getIconWidth());
                //System.out.println(lobbyTitle.getText());
                //System.out.println(lobbyPlayerCount.getText());
                //System.out.println(lobbyTimeline.getText());
                
                //this is the one we need the ones above are extensible features
                if(lobbiesPanels.indexOf( (JPanel) 
                        e.getComponent().getComponentAt(e.getPoint()))>-1){
                    joinLobby(
                        mainData.getLobbiesWaiting().get(lobbiesPanels
                            .indexOf( (JPanel) 
                                e.getComponent().getComponentAt(e.getPoint() ) 
                            )
                        )
                    );
                }
            }
    }
    private class ProfilePicMouseListener extends MouseAdapter{
        Border c;
        @Override
        public void mousePressed(MouseEvent e){
            JLabel pressedPanel = ((JLabel)e.getComponent());
            c = pressedPanel.getBorder();
            Random r = new Random();
            profilePic.setBorder(BorderFactory.createLineBorder(Color.WHITE
                    ,BORDER_THICKNESS));
        }
            
        @Override
        public void mouseReleased(MouseEvent e){
            JLabel pressedPanel = ((JLabel)e.getComponent());
            pressedPanel.setBorder(c);
            // TODO - showProfile thingy();
            
        }
    }
    private class FinishedGamesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Viewing finished games!");
            //viewing the finished games in some other thing 
            //maybe in the referrer have something about finished games
            //hideView();
        }
    }
    /*
    private class OngoingGamesItemListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(onGoing.getSelectedIndex()>0){
                if(e.getStateChange()==1){
                    //joinLobby(e.getItem()) !!! JComboBox must be made of lobbies not Strings
                    System.out.println(e.getItem());
                }
            }
        }
    }
    */
    private class CreateLobbyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Creating a lobby!");
            //hideView();
            //referrer.showCreateLobby();
        }
    }
    private class LogoutListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Logging out!");
            if (JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to logout?", "Really?! Logging out just now?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,new ImageIcon("./img/leaving.png")) == JOptionPane.YES_OPTION){
                logout();
            }
            
        }
    }
    
    //runnable classes
    private class LobbyUpdater implements Runnable {
        @Override
        public void run() {
            mainData.updateLobbiesWaiting();
            showWaitingLobbies();
        }
    }
    private class OngoingUpdater implements Runnable {
        @Override
        public void run() {
            //update data from mainData.getPlayer().getProfile()
            //and call showOngoing()
            HomePage.getPlayer().updateOngoingGames();
            showOngoing();
        }
    }
    private class OnlinePlayersUpdater implements Runnable {
        @Override
        public void run() {
            showOnlineUsers();
        }
    }
    private class ProfilePicUpdater implements Runnable{
        @Override
        public void run(){
            //get new profile pic by update()-ing from 
            //mainData.getPlayer().getProfile() and call showProfilePic()
            showProfilePic();
        }
    }
    private class OngoingActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try{
                joinLobby(HomePage.getPlayer().getOngoingGames()
                        .get(onGoing.getSelectedIndex()-1));
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }
}