/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.findtheball.views;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author victor
 */
public class LittlePanel extends JPanel{
    
    private JButton button;
    private JLabel  imageLabel;
    private CardLayout layout;
    private ActionListener al;
        
    //Constructor
    public LittlePanel(ActionListener al, BufferedImage image) {
        this.al = al;
        initComponents(image);
    }

    //Components initialization
    private void initComponents(BufferedImage image) {
        
        //setLayout(new GridLayout(3,3));
        layout=new CardLayout();
        setLayout(layout);
        
        //Initialize the button
        button=new JButton("try");
        button.setActionCommand("flip");
        button.addActionListener(al);

        //Add the button
        add(button,"button");
        
        //Initialize the image
        Image scaledImage=image.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        
        ImageIcon nue= new ImageIcon(scaledImage);
        imageLabel = new JLabel(nue,JLabel.CENTER);
        add(imageLabel,"image");                
    }
    
    /** 
     *  Shows the button of the little panel
     */
    public void showButton(){
        layout.show(this,"button");    
    }
    
    /** 
     *  Shows the image
     */
    public void showImage(){
        layout.show((JPanel)this,"image");    
    }

    
    
}
