package view;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.2.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
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
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import mainPackage.*;

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
        setSize(408, 500);
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
        
        //adding listeners
        login.addActionListener(new ButtonListener());
        register.addActionListener(new ButtonListener());
        
        showView(); 
    }

    @Override
    public void terminateView() {
        username.setText("");
        password.setText("");
        hideView();
    }

    @Override
    public void hideView() {
        username.setText("");
        password.setText("");
        this.setVisible(false);
    }

    @Override
    public void updateView() {/*nothing to update here*/}

    @Override
    public void showView() {
            this.setVisible(true);
    }
    private void login(){
        //send a login query for that user id (AccessHandler.userID;)
        //create player and profile objects and HomePage and pass it 
        //to homepage to be created
        //terminateView();
        //referrer.showHomePage();}
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==login){
                String un = username.getText();
                char[] pwChar = password.getPassword();
                String pw = new String(pwChar);
                if ( AccessHandler.accessGame(un, pw,false) ){
                    //clearing out things for high security.
                    Arrays.fill(pwChar,'\0');
                    pw = "";
                    //calling garbage collector
                    System.gc();
                    username.setText("");
                    password.setText("");
                    //login here
                    login();
                }
                else{
                    JOptionPane.showMessageDialog(null, 
                        "Username/Password combination is wrong!",
                        "Authentication Error!", JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("./img/authProblem.png"));
                }
            }
            else{
                String un = username.getText();
                if(AccessConnection.isAvailable(un)){
                    JOptionPane.showMessageDialog(null, 
                        "Uh this username is taken!",
                        "Uhhh!!!", JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("./img/usernameTaken.png"));
                    username.setText("");
                }
                char[] pwChar = password.getPassword();
                String pw = new String(pwChar);
                if(true/*AccessHandler.accessGame(un,pw,true)*/){
                    JOptionPane.showMessageDialog(null, 
                        "Welcomeee <3. We are happier with you!!!",
                        "Welcomeee!!!", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon("./img/oneTimeGreeting.png"));
                    //clearing out things for high security.
                    Arrays.fill(pwChar,'\0');
                    pw = "";
                    //calling garbage collector
                    System.gc();
                    username.setText("");
                    password.setText("");
                    //login the same way 
                    login();
                }
            }
        }
    }
}