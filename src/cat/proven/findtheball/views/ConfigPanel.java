/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.findtheball.views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author victor
 */
public class ConfigPanel extends JPanel {

    JTextField tfTries;
    JTextField tfPrizes;

    public ConfigPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        JLabel lTries = new JLabel("Número màxim d'intents:");
        lTries.setBounds(32, 32, 180, 20);
        add(lTries);
        
        tfTries = new JTextField();
        tfTries.setBounds(210, 32, 40, 20);
        tfTries.setHorizontalAlignment(JTextField.RIGHT);
        add(tfTries);
        
        JLabel lPrizes = new JLabel("Número de premis:");
        lPrizes.setBounds(32, 64, 180, 20);
        add(lPrizes);
        
        tfPrizes  = new JTextField();
        tfPrizes.setBounds(210, 64, 40, 20);
        tfPrizes.setHorizontalAlignment(JTextField.RIGHT);
        add(tfPrizes);

    }

    public int getTries() {
        return 3;
    }

    public int getPrizes() {
        return 1;
    }

}
