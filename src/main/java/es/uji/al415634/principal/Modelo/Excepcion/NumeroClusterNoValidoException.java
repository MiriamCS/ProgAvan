package es.uji.al415634.principal.Modelo.Excepcion;

public class NumeroClusterNoValidoException extends Exception{
    public NumeroClusterNoValidoException(){
        super("El numero de cluster no puede ser superior que la cantidad de datos.");
    }

}
