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
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

    //The list of all the little planes that are shown
    private List<LittlePanel> panels =new ArrayList();
    
    //TODO: hacer esto variable y configurable
    private final int PANELS=9;
    
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
        
        initializePanels(nothingImage, somethingImage);
        
        resetPanels();
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "flip":
                showImage(e);
                break;
        }
        System.out.println("Execution action on Game Panel: " + action);

    }

    private void processAction(String action) {
        if (action != null) {
        }
    }
    
    private void showImage(ActionEvent e){
        JButton button= (JButton)e.getSource();
        LittlePanel panel = (LittlePanel)button.getParent();
        panel.showImage();
    }
    
    private void initializePanels(BufferedImage nothingImage, BufferedImage somethingImage) {
        //Initialize Layout
        setLayout(new GridLayout(3,3));

        //Initialize little panels
        for (int i = 0; i < PANELS; i++) {
            //Adds the panel to a list of littlepanels, setting the nothingImage of the pannel
            panels.add(new LittlePanel(this,nothingImage, somethingImage));
            //Adds the panel to the game panel
            add(panels.get(i));            
        }
    }
    
    public void resetPanels(){
        for (int i = 0; i < PANELS; i++) {
            //Adds the panel to a list of littlepanels, setting the nothingImage of the pannel
            panels.get(i).showButton();
            panels.get(i).setEmptyPanel();

            
        }
        
        int randomPanel=generateRandom();
        panels.get(randomPanel).setPrizePanel();

    }
    
    
    private int generateRandom(){
      int min = 0;
      int max = PANELS-1;
        
      return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
    
}
