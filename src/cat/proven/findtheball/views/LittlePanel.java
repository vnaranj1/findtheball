/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.findtheball.views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author victor
 */
public class LittlePanel extends JPanel{
    
    private JButton button;
    private ActionListener al;
        
    //Constructor
    public LittlePanel(ActionListener al) {
        this.al = al;
        initComponents();
    }

    //Components initialization
    private void initComponents() {
        
        //setLayout(new GridLayout(3,3));
        button=new JButton("but..");
        this.setLayout(new BorderLayout());
        this.add(button,BorderLayout.CENTER);
        
    }
    
}
