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
 * This class is the homepage view that the user will see when 
 * logged in.
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

@SuppressWarnings("serial")
public class HomeView extends JFrame implements Viewable{

	//private HomePage mainData;
	private ViewManager referrer;
	private JScrollPane onWaitLobbies;
	private JLabel profilePic;
	private JButton logoutButton;
	private JButton viewFinished;
	private JComboBox<String> onGoing;
	private JButton createLobby;
	private JLabel onlineUsers;

	//newly added classes
	private ArrayList<JPanel> lobbies;
	private JPanel lobbiesContainer;
	private JPanel allTheThings;

	public HomeView(ViewManager ref){
		setTitle("Your Story - Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Random r = new Random();
		getContentPane().setBackground(new Color(0, 0, 0));
		referrer = ref;
		lobbies = new ArrayList<JPanel>();
		
		lobbiesContainer = new JPanel(new GridLayout(/*mainData.getLobbiesWaiting().size()*/9,1));
		//lobbiesContainer.setPreferredSize(new Dimension());

		for(int i = 0; i < 9; i++/*Lobby l:mainData.getLobbiesWaiting()*/){
			JPanel emptyPanel = new JPanel();
			Color c = new Color(r.nextInt(155),r.nextInt(155),r.nextInt(155));
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
			JPanel toFillCenter = new JPanel(new GridLayout(3,1));
			toFill.setBackground(c);
			toFillCenter.setBackground(c);
			
			//adding the icon to the left
			JLabel icon = new JLabel();
			
			try {
				/////// get image and resize it///////////////////////////////////////////////
				FileInputStream fis = new FileInputStream(new File("./img/castleBlack.jpg")/*get database image*/);
				BufferedImage gameIcon = ImageIO.read(fis);
				Image dimg = gameIcon.getScaledInstance(50, 50,Image.SCALE_SMOOTH);
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
			toFill.add(lobbyName,BorderLayout.NORTH);

			JLabel lobbyQuota = new JLabel("Player(s): "+"4"/*l.getQuota()*/+"/"+"5"/*l.getSeats().size()*/);
			lobbyQuota.setForeground(Color.WHITE);
			lobbyQuota.setHorizontalAlignment(JLabel.RIGHT);
			toFill.add(lobbyQuota,BorderLayout.SOUTH);

			JLabel storyTimeline = new JLabel("Timeline: "/*+l.getStory().getTimeline()*/);
			storyTimeline.setForeground(Color.WHITE);
			storyTimeline.setHorizontalAlignment(JLabel.LEFT);
			toFill.add(storyTimeline,BorderLayout.CENTER);

			tmpLobby.add(toFill, BorderLayout.CENTER);
			
			//adding the listeners for the components
			//tmpLobby.addMouseListener(l);

			lobbiesContainer.add(tmpLobby,BorderLayout.CENTER);
			lobbies.add(tmpLobby);
		}
		
		onWaitLobbies = new JScrollPane(lobbiesContainer);
		onWaitLobbies.getVerticalScrollBar().setUnitIncrement(16);
		
		profilePic = new JLabel();
		try {
			/////// get image and resize it///////////////////////////////////////////////
			FileInputStream fis = new FileInputStream(new File("./img/gameWelcome.jpg"));
			BufferedImage gameIcon = ImageIO.read(fis);
			Image dimg = gameIcon.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			//////////////////////////////////////////////////////////////////////////////
			profilePic.setIcon(imageIcon/*mainData.getPlayer().getProfile().getPhoto()*/);
			profilePic.setBorder(BorderFactory.createLineBorder(
					new Color(r.nextInt(155),r.nextInt(155),r.nextInt(155)), 5));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		logoutButton = new JButton("Logout");
		logoutButton.setBackground(Color.LIGHT_GRAY);
		logoutButton.setOpaque(true);
		logoutButton.setForeground(Color.red);
		logoutButton.setFocusPainted(false);
		logoutButton.setFont(new Font("Comic Sans MS",Font.BOLD,13));
		
		try {
			/////// get image and resize it///////////////////////////////////////////////
			FileInputStream fis = new FileInputStream(new File("./img/logout.png"));
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
			FileInputStream fis = new FileInputStream(new File("./img/finished.png"));
			Image gameIcon = ImageIO.read(fis);
			ImageIcon imageIcon = new ImageIcon(gameIcon);
			//////////////////////////////////////////////////////////////////////////////
			viewFinished.setIcon(imageIcon);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		onGoing = new JComboBox<String>();
		onGoing.addItem("Ongoing Games");
		for(int i = 0; i < 1; i++/*Lobby l:mainData.getPlayer().getOngoingGames()*/){
			onGoing.addItem("Secret Lobby"/*l.getName()*/);
		}
		
		onGoing.setBackground(Color.LIGHT_GRAY);
		onGoing.setOpaque(true);
		onGoing.setForeground(Color.red);
		onGoing.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		
		createLobby = new JButton("Create Lobby");
		try {
			/////// get image and resize it///////////////////////////////////////////////
			FileInputStream fis = new FileInputStream(new File("./img/create.png"));
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
		
		onlineUsers = new JLabel("        Online users: 286"/*+mainData.getOnlineUsers()/*remove 286*/);
		onlineUsers.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		onlineUsers.setBackground(Color.BLACK);
		onlineUsers.setForeground(Color.GREEN);
		
		//TODO- add listeners for the combobox and the buttons(especially finished games button)
		// add all the things created above to one JPanel and add that JPanel to the JFrame
		
		allTheThings = new JPanel( new BorderLayout());// TODO - do this crap here 
		allTheThings.setPreferredSize(new Dimension(550,600));
		allTheThings.add(onWaitLobbies,BorderLayout.CENTER);
		
		JPanel theRest = new JPanel(new GridLayout(4,1));
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
		
		allTheThings.add(theRest,BorderLayout.EAST);
		add(allTheThings);
		
		pack();
		showView();
	}

	public void logout() {
		// TODO - implement HomeView.logout
		throw new UnsupportedOperationException();
	}

	public void createLobby() {
		// TODO - implement HomeView.createLobby
		throw new UnsupportedOperationException();
	}

	public void joinLobby(/*Lobby aLobby   ******** uncomment when Lobby is created ***********/) {
		// TODO - implement HomeView.joinLobby
		throw new UnsupportedOperationException();
	}

	@Override
	public void terminateView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showView() {
		this.setVisible(true);
	}

}