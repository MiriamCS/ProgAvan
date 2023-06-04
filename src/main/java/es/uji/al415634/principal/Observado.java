package es.uji.al415634.principal;

import es.uji.al415634.principal.vista.MainFX;

import java.util.List;

public class Observado {
    private MainFX observador;
    public Observado(){
        super();
    }
    public void suscribirObservador(MainFX observador){
        this.observador=observador;
        System.out.println(observador);
    }
    public void eliminarObservador(MainFX observador){
        this.observador=null;
    }
    public void notificarObservadores(){
        System.out.println(observador);
        observador.notificar();
    }
}
