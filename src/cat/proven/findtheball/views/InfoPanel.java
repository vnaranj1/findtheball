/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.findtheball.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author victor
 */
public class InfoPanel extends JPanel {

    JLabel lTriesValue;
    JLabel lPrizesValue;

    public InfoPanel(String textInfo1,int valueInfo1,String textInfo2,int valueInfo2) {
        
        setLayout(new GridLayout(1, 2));

        JLabel lTries = new JLabel(textInfo1);//"Intents permesos: ");
        JLabel lPrizes = new JLabel(textInfo2);//"Número de premis: ");
        
        lTriesValue = new JLabel("" + valueInfo1);
        lPrizesValue = new JLabel("" + valueInfo2);

        add(lTries);
        add(lTriesValue);
        add(lPrizes);
        add(lPrizesValue);
    }

    public void updateInfo(int val1,int val2){
        lTriesValue.setText(""+val1);
        lPrizesValue.setText(""+val2);
    }
    

}
