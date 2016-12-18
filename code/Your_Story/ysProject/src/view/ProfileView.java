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

public class ProfileView extends JFrame implements Viewable {
	
	public ProfileView(ViewManager ref) {
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
			JTextField name = new JTextField();
			JTextField lastname = new JTextField();
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
			textFields.add(name);
			textFields.add(lastname);
			
			picture.setIcon(new ImageIcon(createProfileImage(150, 150)));
			
			name.setBounds(10, 50, 160, 30);
			name.setForeground(Color.gray);
			name.setText("First Name Here..");
			name.setEditable(editable);
			FocusListener nameFocusListener = new FocusListener() {
				boolean focused = false;
				@Override
				public void focusGained(FocusEvent e) {
					if (!focused) {
						name.setForeground(Color.black);
						name.setText("");
					}
					focused = true;
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (name.getText().trim().equals("")) {
						name.setForeground(Color.gray);
						name.setText("First Name Here..");
						focused = false;
					}
				}
			};
			
			lastname.setBounds(10, 90, 160, 30);
			lastname.setForeground(Color.gray);
			lastname.setText("Last Name Here..");
			lastname.setEditable(editable);
			FocusListener lastnameFocusListener = new FocusListener() {
				boolean focused = false;
				@Override
				public void focusGained(FocusEvent e) {
					if (!focused) {
						lastname.setForeground(Color.black);
						lastname.setText("");
					}
					focused = true;
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (lastname.getText().trim().equals("")) {
						lastname.setForeground(Color.gray);
						lastname.setText("Last Name Here..");
						focused = false;
					}
				}
			};
			
			buttons.add(edit);
			buttons.add(exit);
			edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().toString().equals("Edit")) {
						editable = true;
						name.setEditable(editable);
						lastname.setEditable(editable);
						name.addFocusListener(nameFocusListener);
						lastname.addFocusListener(lastnameFocusListener);
						((JButton) e.getSource()).setName("Save");
					} else if (e.getSource().toString().equals("Save")) {
						editable = false;
						name.setEditable(editable);
						lastname.setEditable(editable);
						name.removeFocusListener(nameFocusListener);
						lastname.removeFocusListener(lastnameFocusListener);
						((JButton) e.getSource()).setName("Save");
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
