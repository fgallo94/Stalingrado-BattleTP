package Modelo.Defensas;


public class Chaleco extends Defensa {
    //Clase que hereda de la clase Defensa, tiene un nombre de defensa
    String nombre;

    //Constructor por defecto, que contiene una defensa de valor 10 y el nombre del chaleco
    public Chaleco() {
        super(15, "Chaleco antibalas camuflado");
    }

    //Respectivo Get y Set de los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
