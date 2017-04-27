package Vista;

import java.util.Observable;
import java.util.Observer;

public class Odin implements Observer {
    public void update(Observable observable, Object o) {
        System.out.println(o);
    }
}
