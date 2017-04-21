package Modelo.Armas;


import Modelo.IAtaque;

public class Ataques implements IAtaque {
    //Clase Padre llamada Ataque que implementa la interfaz de tipo IAtaque

    //Atributos generales de la clase padre, un entero que contiene el valor que ataca
    // que se setea luego en cada clase que hereda de esta, como asi tambien el nombre
    // del ataque
    private int ataque;
    private String nombre;

    //Constructor copia
    public Ataques(int ataque, String nombre) {
        this.ataque = ataque;
        this.nombre = nombre;
    }

    //Respectivos Get y Set de cada atributo
    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodo redefinido de la implementacion de IAtaque, imprime por pantalla el tipo de Ataque y su valor
    //retorna el numero de ataque
    public int atacar() {
        System.out.printf("\n El soldado ataca con %s, su daño es igual a %d", this.nombre, getAtaque());
        return getAtaque();
    }
}
