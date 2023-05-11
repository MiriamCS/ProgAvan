package es.uji.al415634.principal.Excepcion;

public class NumeroClusterNoValidoException extends Exception{
    public NumeroClusterNoValidoException(){
        super("El numero de cluster no puede ser superior que la cantidad de datos.");
    }

}
