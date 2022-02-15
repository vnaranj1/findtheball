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
    
    
    private CardLayout layout;
    private ActionListener al;
    
    //The button
    private JButton button;
    
    //The image that will be shown when there is nothing
    private ImageIcon badIcon;    
    //The image that will be shown when there is a prize
    private ImageIcon goodIcon;
    //The label that contains the image
    private JLabel imageLabel;
    
    
    //It is true when the panel has the ball
    private boolean isPrizePanel;
        
    //Constructor
    public LittlePanel(ActionListener al, BufferedImage badImage, BufferedImage goodImage) {
        this.al = al;
        
        initializeIcons(badImage,goodImage);
        
        initComponents();
    }
    
    //Initializes the images
    private void initializeIcons(BufferedImage badImage, BufferedImage goodImage) {
        
                //Escales the image to be shown 
        Image scaledImageA=badImage.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        Image scaledImageB=goodImage.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        
        badIcon= new ImageIcon(scaledImageA);
        goodIcon= new ImageIcon(scaledImageB);

        imageLabel = new JLabel(badIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        
    }

    //Components initialization
    private void initComponents() {
        
        //setLayout(new GridLayout(3,3));
        layout=new CardLayout();
        setLayout(layout);
        
        //Initialize the button
        button=new JButton("try");
        button.setActionCommand("flip");
        button.addActionListener(al);

        //Add the button
        add(button,"button");
        
        add(imageLabel,"image");                
        
        //At the begining, the panel has no prize
        isPrizePanel=false;
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
    
    /**
     * Sets the Little panel as prizePanel
     */
    public void setPrizePanel(){
        isPrizePanel=true;
        imageLabel.setIcon(goodIcon);
                
    }
    
    /**
     * Sets the Little panel as empty panel
     */
    public void setEmptyPanel(){
        isPrizePanel=false;
        imageLabel.setIcon(badIcon);
        
                
    }

    
    /**
     * 
     */
    public boolean isPrizePanel(){
        return isPrizePanel;
    }
    
    
}
