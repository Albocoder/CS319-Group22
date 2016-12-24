package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.border.EtchedBorder;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.1.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the one responsible for creating new lobbies with a 
 * name and a story and then when created automatically add the 
 * creator to the lobby or just goes back to home page if not created
 * */
import mainPackage.*;
import mainPackage.Character;

public class LobbyCreatorView extends JFrame implements Viewable{
    private JScrollPane stories;
    ////////// REMOVED !!! /////////////////////
    //private JButton creator;
    //private Story[] availableStories;
    ////////////////////////////////////////////
    private ViewManager referrer;
    
    //added new stuff
    private JTextField lobbyName;
    private JLabel namePrompt;
    private ArrayList<JPanel> storyPanelsList;
    private JPanel allStories;
    private ArrayList<Story> storiesList;
    
    //constants
    private final int COLOR_COLD_RAND = 155;
    
    public LobbyCreatorView(ViewManager ref){
        referrer = ref;
        logoutOnExitWithDialogue();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {System.out.println("Error: "+ex.getMessage());}
        setTitle("Your Story - Lobby Creator");
        JPanel upperPanel = new JPanel(new BorderLayout());
        namePrompt = new JLabel("Lobby name: ");
        String name;
        if (HomePage.getPlayer().getProfile().getName().length()>13)
            name = HomePage.getPlayer().getProfile().getName().substring(0, 10)+"...";
        else 
            name = HomePage.getPlayer().getProfile().getName();
        lobbyName = new JTextField(name+"'s Lobby");
        upperPanel.add(namePrompt,BorderLayout.WEST);
        upperPanel.add(lobbyName,BorderLayout.CENTER);
        // initializing the scrolling list of stories
        allStories = new JPanel();
        storyPanelsList = new ArrayList<>();
        storiesList = new ArrayList<>();
        showStories();
        stories = new JScrollPane(allStories);
        stories.setPreferredSize(new Dimension(600,700));
        /////////////////////////////////////////////
        // adding the listener
        allStories.addMouseListener(new StoryChooserMouseListener());
        
        add(upperPanel,BorderLayout.NORTH);
        add(stories,BorderLayout.CENTER);
        pack();
        showView();
    }
    
    /*
    public static void main(String args[]){
        new LobbyCreatorView(null);
    }
    */
    
    private void showStories(){
        storyPanelsList.removeAll(storyPanelsList);
        storiesList.removeAll(storiesList);
        allStories.removeAll();
        Random r = new Random();
        storiesList = Story.getStories();
        allStories.setLayout(new GridLayout(storiesList.size(),1));
        for(Story s:storiesList){
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
                BufferedImage playerImg = ImageIO.read(new File("./img/castleBlack.jpg"));//s.getImage()
                Image dimg = playerImg.getScaledInstance(72, 72,Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                icon.setIcon(imageIcon);
                icon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            } catch (Exception e) {
                    //e.printStackTrace();
            }
            toFill.add(icon,BorderLayout.WEST);
            
            JLabel plName = new JLabel("Timeline: "+s.getTimeline());
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

            JLabel storyTimeline = new JLabel("<html>Description: "+s.getDescription()+"</html>");
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);
            String text = "";
            for (Iterator<Character> it = s.getCharList().iterator(); it.hasNext();) {
                Character ch = it.next();
                if(it.hasNext())
                    text+= ch.getName()+", ";
            }
            
            JLabel chars = new JLabel("<html>"+text+"</html>");
            toFill.add(chars,BorderLayout.SOUTH);

            tmpLobby.add(toFill, BorderLayout.CENTER);
            
            allStories.add(tmpLobby);
            storyPanelsList.add(tmpLobby);
        }
        allStories.updateUI();
    }
    
    public void createLobby(String name, Story s) {
        if(name.length() > 22)
            JOptionPane.showMessageDialog(null, 
                "Name of your lobby is longer than 22 characters!!!",
                "Ooops!!!", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("./img/longNaming.png"));
        else{
            referrer.showLobby(Lobby.createLobby(name, s));
        }
    }

    //REMOVED: public void getStories() {}
    
    private void logoutOnExitWithDialogue(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                hideView();
                referrer.showHomePage(null);
            }
        });
    }
    
    @Override
    public void terminateView() {hideView();this.dispose();}
    @Override
    public void hideView() {
        setVisible(false);
    }
    @Override
    public void updateView() {/*** nothing to update ***/}
    @Override
    public void showView() {
        setVisible(true);
    }
    private class StoryChooserMouseListener extends MouseAdapter{
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
                
                //this is the one we need the ones above are extensible features
                if(storyPanelsList.indexOf( (JPanel) 
                        e.getComponent().getComponentAt(e.getPoint()))>-1){
                    createLobby(
                            lobbyName.getText(),
                        storiesList.get(storyPanelsList
                            .indexOf( (JPanel) 
                                e.getComponent().getComponentAt(e.getPoint() ) 
                            )
                        )
                    );
                }
            }
    }
}
