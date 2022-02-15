package cat.proven.findtheball.views;

import cat.proven.findtheball.model.FindTheBall;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

    //The list of all the little panels that are shown
    private List<LittlePanel> panels =new ArrayList();
    
    //Total number of panels
    private final int PANELS=9;
    //Number of little panels in arow of the game panel
    private final int WIDHT=3;
    //Number of little panels in arow of the game panel
    private final int HEIGHT=3;
    
    //To know if the game has ended
    boolean endedGame=false;
    
    //Constructor
    public GamePanel(ActionListener al) throws IOException {
        initComponents();
    }

    //Components initialization
    private void initComponents() throws IOException {
        
        //Load images
        File f = new File ("hola");
        System.out.println(f.getAbsolutePath());
        BufferedImage nothingImage = ImageIO.read(new File("images/nothing.png"));
        BufferedImage somethingImage = ImageIO.read(new File("images/ball.png"));
        
        //Initialize little panels: set the button and the image of each panel
        initializePanels(nothingImage, somethingImage);
        
        //Reset all panels.  For each panel: show buttons and 
        resetPanels();
    }

    /**
     * Method to attend the events produced in the little panels
     * @param e The event that that generates this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        //Check found

        //flip the little panel and show the image
        switch (action) {
            case "flip":
                showImage(e);
                break;
        }
        System.out.println("Execution action on Game Panel: " + action);

    }
    

    /**
     * Shows the image behind a little panel.
     * If the  little panel is priced, then shows a message, and stops the game
     * @param e the source event of the action
     */
    private void showImage(ActionEvent e){      
        
        // Only works while the game is been played
        if (endedGame) {
            JOptionPane.showMessageDialog(
                this, 
                "Juego finalizado", 
                "Ended game", 
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }
        
        //Flip the little panel selected
        JButton button= (JButton)e.getSource();
        LittlePanel panel = (LittlePanel)button.getParent();
        panel.showImage();
        
        //Check the kind of little panel selected
        //if the selected little panel is not the one that contains the image, 
        if (panel.isPrizePanel()){
            //Game Over
            endedGame=true;
            //Show message
            JOptionPane.showMessageDialog(
                this, 
                "Enhora buena.  Imagen en contrada", 
                "Ended game", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    /**
     * Initializes all the little panels and puts them into the game panel
     * @param nothingImage The image that will be shown when there is no ball
     * @param somethingImage The image of the ball that whe are looking for 
     */
    private void initializePanels(BufferedImage nothingImage, BufferedImage somethingImage) {
        //Initialize Layout
        setLayout(new GridLayout(HEIGHT,WIDTH));

        //Initialize little panels
        for (int i = 0; i < PANELS; i++) {
            //Adds the panel to a list of littlepanels, setting the nothingImage of the pannel
            panels.add(new LittlePanel(this,nothingImage, somethingImage));
            //Adds the panel to the game panel
            add(panels.get(i));            
        }
    }
    /**
     * Sets the little panels to the initial configuration to be able to start the game
     */
    public void resetPanels(){
        
        //For each panel, shows the button and set as empty panel
        for (int i = 0; i < PANELS; i++) {
            //Adds the panel to a list of littlepanels, setting the nothingImage of the pannel
            panels.get(i).showButton();
            panels.get(i).setEmptyPanel();            
        }
        
        //Random selection on a panel to set the prize
        int randomPanel=generateRandom();
        panels.get(randomPanel).setPrizePanel();
        
        //Start game
        endedGame=false;

    }
    
    
    private int generateRandom(){
      int min = 0;
      int max = PANELS-1;
        
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
}
