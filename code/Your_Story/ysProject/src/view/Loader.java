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
import javax.swing.*;

public class Loader extends JFrame{
    JLabel txt;
    
    public Loader(String msg){
        getContentPane().setLayout(new BorderLayout());
        JLabel l  = new JLabel();
        l.setIcon(new ImageIcon("./img/loading.gif"));
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
    public void setText(String msg){
        txt.setText(msg);
    }
    public String getText(){
        return txt.getText();
    }
}
