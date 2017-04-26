package Modelo;


public class Soldado {

    public IAtaque iAtaque;
    public IDefensa iDefensa;
    //Clase que obtiene comportamientos de ataque y defensas previamente cargados, se pueden
    //iniciar sin comportamientos como tambien inicializarlos ya asignandole interfaces
    private int vida;
    private int defensa;
    private int estado; //1 vivo y 0 muerto


    //Constructor sin parametros
    public Soldado() {
        vida = 10;
        defensa = 5;
        estado=1;
    }

    //Constructor con interfaces de ataque y defensa
    public Soldado(IAtaque ia, IDefensa id) {
        vida = 10;
        defensa = 5;
        iAtaque = ia;
        iDefensa = id;
        estado=1;
    }

    //Respectivos Get y Set para cada Atributo de la clase
    public int getiAtaque() {
        return iAtaque.atacar();
    }

    public void setiAtaque(IAtaque iAtaque) {
        this.iAtaque = iAtaque;
    }

    public int getiDefensa() {
        return iDefensa.defender();
    }

    public void setiDefensa(IDefensa iDefensa) {
        this.iDefensa = iDefensa;
    }

    public int getVida() {
        return vida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
