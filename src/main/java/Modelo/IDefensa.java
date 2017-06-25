package Modelo;


public interface IDefensa {

    //Interfaz de tipo defensa que tiene un metodo Defender que luego
    // se redefinira en todas las clases que implementen esta interfaz
    int defender();

    String obtenerDefensa();
}
