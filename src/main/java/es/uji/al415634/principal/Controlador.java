package es.uji.al415634.principal;


import es.uji.al415634.principal.Canciones.SongRecSys;
import es.uji.al415634.principal.Distancia.Distance;

import static es.uji.al415634.principal.MainFX.controlador;

public class Controlador implements Grafica{
    public String algoritmo;
    public Distance distancia;
    public String cancion;


    @Override
    public void tipoRecomendacion(String rec) {
        this.algoritmo = rec;
        System.out.println(algoritmo);
    }

    @Override
    public void tipoDistancia(Distance distancia) {
        this.distancia = distancia;
        System.out.println(distancia);
    }

    @Override
    public void seleccionarCancion(String texto) {
        this.cancion = texto;
        System.out.println(cancion);

    }

    public void buscarCancion() throws Exception {
        new SongRecSys(controlador.algoritmo, controlador.distancia, controlador.cancion);
    }

}
