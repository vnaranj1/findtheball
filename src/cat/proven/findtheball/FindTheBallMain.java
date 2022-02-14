package cat.proven.findtheball;

import cat.proven.findtheball.views.FindTheBallFrame;

public class FindTheBallMain {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FindTheBallFrame();
            }
        });
    }
    
}
