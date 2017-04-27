package Vista;


import Controladora.ControladoraMain;

public class Main {

    public static void main(String[] args) {
        //Desde la vista se llama a la controladora correspondiente y se ejecuta el metodo "play"

        ControladoraMain ctrlMain= new ControladoraMain();
        ctrlMain.play();

    }
}
