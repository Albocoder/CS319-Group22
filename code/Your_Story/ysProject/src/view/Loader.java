/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is used to make loading animation to let the user know
 * things are happening. Uses free gif from http://loading.io/
 * */
package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Erin Avllazagaj
 */
public class Loader extends JFrame{
    private JLabel txt;
    
    /**
     *
     * @param msg
     */
    public Loader(String msg){
        getContentPane().setLayout(new BorderLayout());
        JLabel l  = new JLabel();
        File f = new File("./img/loading.jpg");
        BufferedImage i = null;
        try {
            i = ImageIO.read(f);
        } catch (Exception ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        l.setIcon(new ImageIcon(i));
        txt = new JLabel(msg);
        //txt.setForeground(new Color());
        txt.setFont(new Font("Arial",Font.BOLD,20));
        setUndecorated(true);
        add(l,BorderLayout.CENTER);
        add(txt,BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     *
     * @param msg
     */
    public void setText(String msg){
        txt.setText(msg);
    }

    /**
     *
     * @return
     */
    public String getText(){
        return txt.getText();
    }
}
