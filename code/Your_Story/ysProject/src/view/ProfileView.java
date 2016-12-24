package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import mainPackage.*;

import javax.swing.event.*;

/**
 *
 * @author kaxell
 */
public class ProfileView extends JFrame implements Viewable {
	
	private ViewManager referrer;
        private Profile theProfile;
	
    /**
     *
     * @param toShow
     * @param ref
     */
    public ProfileView(Profile toShow,ViewManager ref) {
                theProfile = toShow;
		referrer = ref;
		add(new ProfilePanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 400));
		setVisible(true);
	}
	
//	public static void main(String [] args) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ProfileView(null);
//            }
//        });
//	}

    /**
     *
     */

	@Override
	public void terminateView() {
		// TODO Auto-generated method stub
		
	}

    /**
     *
     */
    @Override
	public void hideView() {
		// TODO Auto-generated method stub
		
	}

    /**
     *
     */
    @Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

    /**
     *
     */
    @Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}
	
	class ProfilePanel extends JPanel {
		
		private boolean editable;
		
		public ProfilePanel() {
			editable = false;
			JScrollPane scrollPane = new JScrollPane(new FinishedGames());
			JPanel centerPanel = new JPanel();
			JPanel picturePanel = new JPanel();
			JPanel textFields = new JPanel();
			JPanel buttons = new JPanel();
			JLabel picture = new JLabel();
			JTextField username = new JTextField();
			JTextField bio = new JTextField();
			JButton edit = new JButton("Edit");
			JButton exit = new JButton("Exit");
			
			setLayout(new BorderLayout());
			setSize(700, 400);
			add(scrollPane, BorderLayout.EAST);
			add(centerPanel, BorderLayout.CENTER);
			
			scrollPane.setPreferredSize(new Dimension(300, getHeight()));
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.getVerticalScrollBar().setUnitIncrement(15);
			scrollPane.getVerticalScrollBar().setBlockIncrement(0);
			
			centerPanel.setLayout(new BorderLayout());
			centerPanel.add(picturePanel, BorderLayout.NORTH);
			centerPanel.add(bio, BorderLayout.CENTER);
			centerPanel.add(buttons, BorderLayout.SOUTH);
			
			picturePanel.setLayout(new BorderLayout());
			picturePanel.add(picture, BorderLayout.WEST);
			picturePanel.add(textFields, BorderLayout.CENTER);
			
			textFields.setLayout(null);
			textFields.add(username);
			
			picture.setIcon(new ImageIcon(createProfileImage(150, 150)));
			
			username.setBounds(10, 70, 160, 30);
			username.setForeground(Color.gray);
			username.setText("Username Here..");
			username.setEditable(editable);
			FocusListener nameFocusListener = new FocusListener() {
				boolean focused = false;
				@Override
				public void focusGained(FocusEvent e) {
					if (!focused) {
						username.setForeground(Color.black);
						username.setText("");
					}
					focused = true;
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (username.getText().trim().equals("")) {
						username.setForeground(Color.gray);
						username.setText("Username Here..");
						focused = false;
					}
				}
			};
			
			buttons.add(edit);
			buttons.add(exit);
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (((JButton) e.getSource()).getText().equals("Edit")) {
						editable = true;
						username.setEditable(editable);
						username.addFocusListener(nameFocusListener);
						((JButton) e.getSource()).setText("Save");
					} else if (((JButton) e.getSource()).getText().equals("Save")) {
						editable = false;
						username.setEditable(editable);
						username.removeFocusListener(nameFocusListener);
						((JButton) e.getSource()).setText("Edit");
					} else if (((JButton) e.getSource()).getText().equals("Exit")) {
						referrer.showHomePage(null);
					}
				}
			});
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		public BufferedImage createProfileImage(int width, int height) {
			BufferedImage img = new BufferedImage(width, height, 
					BufferedImage.TYPE_INT_ARGB);
			BufferedImage blank = null;
			try {
				blank = ImageIO.read(new File("img/blankProfileImg.png"));
			} catch (IOException e) {
				System.out.println("Error");
			}
			Graphics2D g = (Graphics2D) img.createGraphics();
			g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, 
					RenderingHints.VALUE_INTERPOLATION_BILINEAR );
			g.drawImage( blank, 20, 20, width - 30, height - 30, null );
			
			return img;
		}
	}

	class FinishedGames extends JPanel {
		
		final static int LIST_OBJECT_WIDTH = 270;
		final static int LIST_OBJECT_HEIGHT = 80;
		Lobby [] lobbies;
		
		public FinishedGames() {
//			lobbies = LobbyConnection.getLobbies(AccessHandler.userID);
			
			JList list = new JList(new Object[7]);
//			list.setModel(new AbstractListModel() {
//
//	            @Override
//	            public int getSize() {
//	                return lobbies.length;
//	            }
//
//	            @Override
//	            public Object getElementAt(int i) {
//	                return lobbies[i];
//	            }
//	        });
			list.setCellRenderer(new LobbyListRenderer());
			list.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent evt) {
	                
	            }
	        });
			
			add(list);
			setBackground(Color.WHITE);
		}
		
		public class LobbyListRenderer extends DefaultListCellRenderer {

	        @Override
	        public Component getListCellRendererComponent(
	                JList list, Object value, int index,
	                boolean isSelected, boolean cellHasFocus) {

	            JLabel label = (JLabel) super.getListCellRendererComponent(
	                    list, value, index, isSelected, cellHasFocus);
	            label.setIcon(new ImageIcon(createListImage(index)));
	            return label;
	        }
	    }
		
		public BufferedImage createListImage(int index) {
			BufferedImage img = new BufferedImage(LIST_OBJECT_WIDTH, LIST_OBJECT_HEIGHT, 
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) img.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
			
			g.setColor(Color.BLACK);
			g.drawRect(10, 10, LIST_OBJECT_HEIGHT - 20, LIST_OBJECT_HEIGHT - 20);
			g.drawString("Photo", 15, LIST_OBJECT_HEIGHT/2 + 6);
			g.drawString("Story Description", LIST_OBJECT_HEIGHT + 10, LIST_OBJECT_HEIGHT/2 + 6);
			
			return img;
		}
	}
}
