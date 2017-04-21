package Modelo.Defensas;


import Modelo.IDefensa;

public class Defensa implements IDefensa {
    //Clase Padre llamada Defensa que implementa la interfaz de tipo IDefensa

    //Atributos generales de la clase padre, un entero que contiene el valor que defensa
    // que se setea luego en cada clase que hereda de esta, como asi tambien el nombre
    // de la defensa
    private int defensa;
    private String nombre;

    //Constructor copia
    public Defensa(int defensaNueva, String nombre) {
        this.defensa = defensaNueva;
        this.nombre = nombre;
    }

    //Respectivos Get y Set de cada atributo
    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodo redefinido de la implementacion de IDefensa, imprime por pantalla el tipo de defensa y su valor
    //retorna el numero de defensa
    public int defender() {
        System.out.printf("\n La defensa del soldado es %s, su defensa es igual a %d", this.nombre, getDefensa());
        return getDefensa();
    }
}
