package es.uji.al415634.principal;

import es.uji.al415634.principal.vista.MainFX;

public class Observado {
    private MainFX observado;
    public Observado(){
        super();
    }
    public void suscribirObservador(MainFX observado){
        this.observado=observado;
        System.out.println(observado);
    }
    public void eliminarObservador(MainFX observado){
        this.observado=null;
    }
    public void notificarObservadores(){
        System.out.println(observado);
        observado.notificar();
    }
}
