import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

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
		setTitle("Your Story login!");
		username = new JTextField();
		password = new JPasswordField();
		login = new JButton("Login");
		register = new JButton("Register");		//add '+' image type for register
		icon = new JLabel();  					//add image here
		referrer = ref;
		playerID = -1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setLayout(new BorderLayout());
		//contentPane.add(null, BorderLayout.CENTER);
		//contentPane.add(null, BorderLayout.PAGE_END);
		pack();
		show();
	}
	
	@Override
	public void terminate() {
		//check this for nonlooping function
		referrer.hideLogin(true);
	}

	@Override
	public void hide() {
		setVisible(false);
	}

	@Override
	public void update() {
		//this should get new data!
	}

	@Override
	public void show() {
		setVisible(true);
	}

}
