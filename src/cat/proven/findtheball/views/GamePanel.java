package cat.proven.findtheball.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

    private List<LittlePanel> panels =new ArrayList();
    
    //TODO: hacer esto variable y configurable
    private final int PANELS=9;
    
    //Constructor
    public GamePanel(ActionListener al) {
        initComponents();
    }

    //Components initialization
    private void initComponents() {
        
        setLayout(new GridLayout(3,3));
        
        for (int i = 0; i < PANELS; i++) {
            //Adds the panel to a list of littlepanels
            panels.add(new LittlePanel(this));
            //Adds the panel to the bigpanel
            add(panels.get(i));            
        }
    }
    
    /**
     * gets value of celsius textfield
     * @return celsius in textfield or 0 if value does not validate.
     * @throws NumberFormatException if celsius is not a valid numeric value
     */
    public double getCelsius() throws NumberFormatException {
     /*   String text = txtCelsius.getText();
        double value = 0;
        value = Double.parseDouble(text);
        return value;*/
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
