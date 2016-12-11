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
 * This class is the login view and will server to handle login to 
 * the game.
 * */

import java.awt.event.*; // a comment
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginView extends JFrame implements Viewable{
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	private JButton register;
	private JLabel icon;
	private ViewManager referrer;
	private long playerID;
	JPanel contentPane;
    
	
	public LoginView(ViewManager ref){
		// add title and color to the background of the frame
		getContentPane().setBackground(new Color(0, 0, 0));
		setTitle("Your Story - Login");
		
		setDefaultCloseOperation (EXIT_ON_CLOSE);
        getContentPane().setLayout (null);
        setSize(408, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        
        //Set up the game welcome image
        BufferedImage gameIcon = null;
        // Game's icon
        try {
        	/////// get image and resize it///////////////////////////////////////////////
        	FileInputStream fis = new FileInputStream(new File("./img/YS.png"));
        	gameIcon = ImageIO.read(fis);
        	Image dimg = gameIcon.getScaledInstance(387, 290,Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            //////////////////////////////////////////////////////////////////////////////
            icon = new JLabel();
            icon.setBounds(10, 10, 387, 290);
            icon.setIcon(imageIcon);
            add(icon);
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        ////////////////////////////////////////////////
        
        
        //username prompt label
        JLabel username_txt = new JLabel("Username");
        username_txt.setBounds(50,300,340,50);
        username_txt.setFont(new Font("SansSerif", Font.BOLD, 18));
        username_txt.setForeground(Color.WHITE);
        add(username_txt);
        
        //password prompt label
        JLabel pasw_txt = new JLabel("Password");
        pasw_txt.setBounds(50,348,150,50);
        pasw_txt.setFont(new Font("SansSerif", Font.BOLD, 18));
        pasw_txt.setForeground(Color.WHITE);
        add(pasw_txt);
            
        //password Field
        password = new JPasswordField();
        password.setBounds(200, 358, 150, 30);
        add(password);
        
        //username text field
        username = new JTextField();
        username.setBounds(200, 310, 150, 30);
        add(username);
            
        //login
        login = new JButton( "Login");
        login.setBounds(80, 420, 100, 30);
        //login.setIcon(new ImageIcon(loginIcon));
        login.setMargin(new Insets(1,1,1,1));
        add(login);
        
        //register
        register = new JButton( "Register");
        register.setBounds(200, 420, 100, 30);
        add(register);
        
        showView(); 
	}
	
	@Override
	public void terminateView() {
		//check this for nonlooping function
		//referrer.hideLogin(true);
	}

	@Override
	public void hideView() {
		this.setVisible(false);
	}

	@Override
	public void updateView() {
		//this should get new data!
	}

	@Override
	public void showView() {
		this.setVisible(true);
	}

}
