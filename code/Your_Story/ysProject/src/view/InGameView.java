package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import mainPackage.*;

/**
 *
 * @author kaxell
 */
public class InGameView extends JFrame implements Viewable {
    
	private ViewManager referrer;
	
	static Font font = new Font("Tahoma", Font.PLAIN, 12);;
	static Font boldFont = new Font("Tahoma", Font.BOLD, 12);
	static int LINE_HEIGHT;
	JScrollPane scrollPane;
	ChatView chat;
	JPanel onlineUsers;
	JPanel bottomPanel;
	MessageTypingBoxView mv;
	Lobby l;
        
	private ScheduledExecutorService chatExec;
	private ScheduledExecutorService onlineUsersExec;
	
    /**
     *
     * @param l
     * @param ref
     */
	public InGameView(Lobby l, ViewManager ref) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(LobbyView.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		LINE_HEIGHT = font.getSize() + 8;
		referrer = ref;
		JPanel mainPanel = new JPanel();
		
		this.l = l;
		chat = new ChatView(l);
		scrollPane = new JScrollPane(chat);
		mv = new MessageTypingBoxView(scrollPane, l);
		
		JPanel panel = new JPanel();
		onlineUsers = new JPanel();
		JPanel story = new JPanel();
		JScrollPane storyScroll = new JScrollPane(story);
		bottomPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton voteButton = new JButton("Vote");
		JButton exitButton = new JButton("Exit");
		
		logoutOnExitWithDialogue();
		updateView();
		add(mainPanel);
		addMouseListener(mv);
		setMinimumSize(new Dimension(700, 400));
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e) {
				scrollPane.setSize(new Dimension(mv.getWidth(), 
								mv.getHeight() - mv.getBoxHeight()));
				storyScroll.setSize(new Dimension(300, getHeight() / 3));
			}
		});
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(mv, BorderLayout.CENTER);
		mainPanel.add(panel, BorderLayout.EAST);
		
		panel.setPreferredSize(new Dimension(300, 400));
		panel.setLayout(new BorderLayout());
		panel.add(storyScroll, BorderLayout.NORTH);
		panel.add(onlineUsers, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		storyScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		storyScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		storyScroll.getVerticalScrollBar().setUnitIncrement(15);
		storyScroll.getVerticalScrollBar().setBlockIncrement(0);
		storyScroll.setPreferredSize(new Dimension(300, getHeight() / 3));
		
		BufferedImage img = createStoryImage();
		story.add(new JLabel(new ImageIcon(img)));
		story.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		
		onlineUsers.add(new JLabel(new ImageIcon(createOnlineUsersImage()), SwingConstants.CENTER));
		
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.setLayout(new FlowLayout());
//		buttonPanel.add(voteButton);
		buttonPanel.add(exitButton);
		
		voteButton.addActionListener(new VoteListener());
		exitButton.addActionListener(new ExitListener());
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getVerticalScrollBar().setBlockIncrement(0);
                setVisible(true);
	}
	
    /**
     *
     * @return
     */
    public BufferedImage createStoryImage() {
		int width = 300;
		Story s = l.getStory();
		String storyString = s.getTimeline() + "\n" + s.getDescription();
		ArrayList<String> messageContent = InGameView.fitString(storyString, font, width);
		int height = LINE_HEIGHT * (messageContent.size());
		
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.RED);
		g.setFont(font);
		for (int i = 0; i < messageContent.size(); i++) {
			g.drawString(messageContent.get(i), 10, i * LINE_HEIGHT + 15);
		}
		return img;
	}
	
    /**
     *
     * @return
     */
    public BufferedImage createOnlineUsersImage() {
		ArrayList<Player> onlineUsers = LobbyConnection.getOnlineUsersOfLobby(l.getID());
		ArrayList<Player> offlineUsers = LobbyConnection.getOfflineUsersOfLobby(l.getID());
		int userNumber = onlineUsers.size() + offlineUsers.size();
		String onlineUsersHeader = "Online Users";
		Font headerFont = new Font("Tahoma", Font.BOLD, 24);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		int height = userNumber * LINE_HEIGHT + 50;
		int width = (int)(headerFont.getStringBounds(onlineUsersHeader, frc).getWidth()) + 20;
		
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setFont(headerFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString(onlineUsersHeader, 10, 30);
		g.setFont(font);
		for (int i = 0; i < onlineUsers.size(); i++) {
			String name = onlineUsers.get(i).getProfile().getName();
			g.setColor(Color.GREEN);
			g.fillOval(30, i * LINE_HEIGHT + 55, 5, 5);
			g.setColor(Color.BLACK);
			g.drawString(name, 40, (i + 1) * LINE_HEIGHT + 40);
		}
		for (int i = 0; i < offlineUsers.size(); i++) {
			String name = offlineUsers.get(i).getProfile().getName();
			g.setColor(Color.RED);
			int exHeight = LINE_HEIGHT * onlineUsers.size();
			g.fillOval(30, i * LINE_HEIGHT + 55 + exHeight, 5, 5);
			g.setColor(Color.BLACK);
			g.drawString(name, 40, (i + 1) * LINE_HEIGHT + 40 + exHeight);
		}
		return img;
	}
	
    /**
     *
     * @param s
     * @param finalWidth
     * @return
     */
    public static ArrayList<String> fitString(String s, Font font, int finalWidth) {
		ArrayList<String> modified = new ArrayList<String>();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		int index = -1;
		
		for (int i = 0; i < s.length(); i++) {
			int length = (int)(font.getStringBounds(s.substring(0, i + 1), frc).getWidth());
			if (s.charAt(i) == ' ' && length + 30 < finalWidth) {
				index = i;
			} else if (length + 30 >= finalWidth) {
				if (index == -1) {
					modified.add(s.substring(0, i));
					s = s.substring(i);
				} else {
					modified.add(s.substring(0, index));						
					s = s.substring(index + 1);
					index = -1;
				}
				i = 0;	
			} else if (s.charAt(i) == '\n') {
				modified.add(s.substring(0, i));
				s = s.substring(i + 1);
				index = -1;
				i = 0;
			} 
		}
		modified.add(s);
		return modified;
	}
	
    /**
     *
     */
    @Override
	public void terminateView() {
            referrer.showHomePage(null);
	}

    /**
     *
     */
    @Override
	public void hideView() {
            setVisible(false);
            chatExec.shutdown();
            onlineUsersExec.shutdown();
	}

    /**
     *
     */
    @Override
	public void updateView() {
            chatExec = Executors.
                    newSingleThreadScheduledExecutor();
            onlineUsersExec = Executors.
                    newSingleThreadScheduledExecutor();
            
            chatExec.scheduleAtFixedRate(
                    new ChatUpdater(),2,2,TimeUnit.SECONDS);
            onlineUsersExec.scheduleAtFixedRate(
                    new OnlineUsersUpdater(),9,9,TimeUnit.SECONDS);
	}

    /**
     *
     */
    @Override
	public void showView() {
		setVisible(true);
	}
	
	private class ChatUpdater implements Runnable {
		@Override
		public void run() {
			int height = chat.getArraySize();
			chat.fetchNewMessages();
			revalidate();
			if (height != chat.getArraySize()) {
				scrollPane.repaint();
				revalidate();
				JScrollBar vertical = scrollPane.getVerticalScrollBar();
				vertical.setValue(vertical.getMaximum());
			}
		}
	}
	
	private class OnlineUsersUpdater implements Runnable {
		@Override
		public void run() {
			onlineUsers.removeAll();
			onlineUsers.add(new JLabel(new ImageIcon(createOnlineUsersImage()), 
					SwingConstants.CENTER));
			repaint();
			revalidate();
		}
	}
	
//	public static void main (String [] args) {
//            java.awt.EventQueue.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    AccessHandler.username = "e3";
//                    AccessHandler.userID = 6;
//                    new InGameView(LobbyConnection.getLobby(9), null);
//                }
//            });
//	}
	
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                terminateView();
            }
        });
    }
    
    private class VoteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
//			bottomPanel.add(new );
			mv.addNotify();
		}
    }
    
    private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			terminateView();
			mv.addNotify();
		}
    }
}