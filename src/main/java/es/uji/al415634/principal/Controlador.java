package es.uji.al415634.principal;


import es.uji.al415634.principal.Canciones.SongRecSys;
import es.uji.al415634.principal.Distancia.Distance;

import java.util.List;

public class Controlador implements Grafica{
    private String algoritmo;
    private Distance distancia;
    private String cancion;
    private int numRecomendaciones;
    private List<String> recommended_items;

    @Override
    public void setRecomendacion(String rec) {this.algoritmo = rec;
    }

    @Override
    public void setDistancia(Distance distancia) {

        this.distancia = distancia;
    }

    @Override
    public void setCancion(String cancion) {
        this.cancion=cancion;
    }

    @Override
    public void setNumRecomendaciones(int num){this.numRecomendaciones=num;}

    @Override
    public void buscarCancion() throws Exception {
        SongRecSys songRecSys = new SongRecSys(algoritmo, distancia, cancion, numRecomendaciones);
        recommended_items = songRecSys.getReportRecommendation();

    }

    @Override
    public String getCancion(){
        return cancion;
    }

    @Override
    public List<String> getRecommendations(){
        return  recommended_items;
    }


}
