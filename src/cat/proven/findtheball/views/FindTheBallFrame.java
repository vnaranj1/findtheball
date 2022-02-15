package cat.proven.findtheball.views;

import cat.proven.findtheball.model.FindTheBall;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class FindTheBallFrame extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private CardLayout layout;
    private GamePanel gamePanel;
    private JPanel configPanel;
    
    private final String aboutMessage;
    
    public FindTheBallFrame() {
        aboutMessage = "<html><p>Find the ball application</p><p><em>(c) ProvenSoft 2021</em></p></html>";
        initComponents();
    }

    private void initComponents() {
        
        try {
            //Initialize Frame
            this.setTitle("Find The Ball application");
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            
            this.addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing( WindowEvent evt ) {
                    exitApplication();
                }
            } );
            
            //Initialize menubar
            this.menuBar = buidMenuBar();
            this.setJMenuBar(menuBar);
            
            //Initialize contentPane
            Container pane = this.getContentPane();
            layout=new CardLayout();
            pane.setLayout(layout);
            
            //Add gamePanel
            gamePanel = new GamePanel(this);
            pane.add("game",gamePanel);
            
            //Add configPanel
            configPanel= new ConfigPanel();
            pane.add("config",configPanel);
            
            //Show frame
            this.setLocationRelativeTo(null);
            this.setSize(600, 400);
            this.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(FindTheBallFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JMenuBar buidMenuBar() {
        JMenuBar mnuBar = new JMenuBar();
        JMenu mnu;
        JMenuItem mItem;
        
        //File menu
        mnu = new JMenu("File");
            mItem = new JMenuItem("Exit");
            mItem.setActionCommand("menu_exit");
            mItem.addActionListener(this);
            mnu.add(mItem);
        mnuBar.add(mnu);          

        //Clear game
        mnu = new JMenu("Game");
            mItem = new JMenuItem("Play");
            mItem.setActionCommand("menu_game");
            mItem.addActionListener(this);
            mnu.add(mItem);
            mItem = new JMenuItem("Config");
            mItem.setActionCommand("menu_config");
            mItem.addActionListener(this);
            mnu.add(mItem);
        mnuBar.add(mnu);

        //Help menu
        mnu = new JMenu("Help");
            mItem = new JMenuItem("About");
            mItem.setActionCommand("menu_about");
            mItem.addActionListener(this);
            mnu.add(mItem);
        mnuBar.add(mnu);        
        
        return mnuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        processAction(action);
    }

    private void processAction(String action) {
        if (action != null) {
            switch (action ) {
                case "menu_exit":
                    exitApplication();
                    break;
                case "menu_game":
                    displayGameMode();
                    break;
                case "menu_config":
                    displayConfigMode();
                    break;
                case "menu_about":
                    aboutDialog();
                    break;
                default:                   
                    break;
            } 
            System.out.println("Execution action on Main Frame: "+action);
        }
    }

    /**
     * asks for confirmation and exits application.
     */
    private void exitApplication() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you really want to exit?");
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);            
        }
    }
    

    /**
     * Displays game mode
     */
    private void displayGameMode() {
        //Show game panel
        layout.show(this.getContentPane(), "game");
        
        //Initialize little panels
        gamePanel.resetPanels();
        //Asignar la imagen de pelota encontrada

    }
    /**
     * Displays configuration mode
     */
    private void displayConfigMode() {
        layout.show(this.getContentPane(), "config");

    }

    /**
     * Displays about dialog
     */
    private void aboutDialog() {
        JOptionPane.showMessageDialog(this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    
}
