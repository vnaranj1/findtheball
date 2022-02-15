package cat.proven.findtheball.model;

import java.util.concurrent.ThreadLocalRandom;
import static javax.swing.Spring.max;

public class FindTheBall {
    public static final int PANELS=9;
    static int ballPosition;
    
    public static int generateRandom(){
        return ThreadLocalRandom.current().nextInt(1, FindTheBall.PANELS + 1);
        
    } 
    
    /**
     * Cada vez que se pulsa Start
     * Generar un número aleatorio, y coloca ahí la bolita
     * Inicializar los littlepanels con la imagen que toque
     * 
     */
}
