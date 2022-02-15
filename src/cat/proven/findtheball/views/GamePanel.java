package cat.proven.findtheball.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

    //The list of all the little panels that are shown
    private List<LittlePanel> panels =new ArrayList();
    
    //Total number of panels
    private final int PANELS=9;
    //Number of little panels in arow of the game panel
    private final int WIDTH=3;
    //Number of little panels in arow of the game panel
    private final int HEIGHT=3;
    
    //max number of tries to get the prize
    private int maxTries;
    
    //Tries counter
    private int tries;
    
    //Number of prizes
    private int prizes;
    
    //Number of prizes found
    private int found;
    
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
        
        //Sets the initial maximum number of tries and prizes
        maxTries=6;
        prizes=2;
        found=0;
        
        //Reset all panels.  For each panel: show buttons and 
        resetPanels();
        

    }

    /**
     * Method to attend the events produced.
     * Listen to flip action produced in the little panels
     * Listen to config action produced in condiguration panel
     * @param e The event that that generates this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        //Check found

        //flip the little panel and show the image
        switch (action) {
            case "flip":
                flipBoard(e);
                break;
            case "config":
                resetConfig(e);
                break;

        }
        System.out.println("Execution action on Game Panel: " + action);

    }
    

    /**
     * Shows the image behind a little panel.
     * If the  little panel is priced, then shows a message, and stops the game
     * If tries are exhausted, then hows a message, and stops the game
     * @param e the source event of the action
     */
    private void flipBoard(ActionEvent e){      
        System.out.println("tries: "+tries+" maxtries: "+maxTries);
        
        // Only works while the game is been played
        if (endedGame) {
            JOptionPane.showMessageDialog(
                this, 
                "Joc  Finalitzat", 
                "Ended game", 
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }
        
        
        //Flip the little panel selected
        JButton button= (JButton)e.getSource();
        LittlePanel panel = (LittlePanel)button.getParent();
        panel.showImage();
        
        //Adds this try to the counter of tries
        tries++;
        
        //Check the kind of little panel selected
        //if the selected little panel is not the one that contains the image, 
        if (panel.isPrizePanel()){
            
            found++;
            if (found==prizes){
                //Game Over
                endedGame=true;
                //Show message
                String imatge=found>1?"les imatges":"l'imatge";
                JOptionPane.showMessageDialog(
                    this, 
                    "Enhorabona.  Has trobat "+imatge+"!!", 
                    "Ended game", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //If was the last try, then the game is over, and the player looses
        else if (tries>=maxTries) {
            endedGame=true;
            JOptionPane.showMessageDialog(
                this, 
                "Has perdut.  Número d'intets exhaurit", 
                "Ended game", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    /**
     * Updates the configuration of the game when the ok button of the config
     * panel gets pressed
     * @param e 
     */
    private void resetConfig(ActionEvent e){

        JButton button= (JButton)e.getSource();
        ConfigPanel panel = (ConfigPanel)button.getParent();

        maxTries=panel.getTries();
        prizes=panel.getPrizes();
        found=0;
        
        
    }
    
    /**
     * Initializes all the little panels and puts them into the game panel
     * @param nothingImage The image that will be shown when there is no ball
     * @param somethingImage The image of the ball that whe are looking for 
     */
    private void initializePanels(BufferedImage nothingImage, BufferedImage somethingImage) {
        //Initialize Layout
        setLayout(new GridLayout(HEIGHT, WIDTH));

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
        
        setPrizes();
        
        //Start game
        endedGame=false;
        
        //Initialize number of tries
        tries=0;
        found=0;

    }
    
    /**
     * Set the little panes with prize depending on the configuration value
     */ 
    private void setPrizes() {
        int settedPrizes=0;
                
        while (settedPrizes<prizes){
            //Random selection on a panel to set the prize
            int randomPanel=generateRandom();        
            LittlePanel panel = panels.get(randomPanel);        
            if (!panel.isPrizePanel()){
                panel.setPrizePanel();
                settedPrizes++;
            }
        }
        
        
        
        
    }
    
    
    private int generateRandom(){
      int min = 0;
      int max = PANELS-1;
        
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
}
