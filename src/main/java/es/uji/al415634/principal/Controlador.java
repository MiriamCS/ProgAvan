package es.uji.al415634.principal;


import es.uji.al415634.principal.Canciones.SongRecSys;
import es.uji.al415634.principal.Distancia.Distance;

import java.util.List;

public class Controlador implements Grafica{
    public String algoritmo;
    public Distance distancia;
    public String cancion;
    private int numRecomendaciones;
    public List<String> recommended_items;

    @Override
    public void setAlgoritmo(String alg) {this.algoritmo = alg;}
    @Override
    public void setDistancia(Distance distancia) {this.distancia = distancia;}
    @Override
    public void setCancion(String cancion) {
        this.cancion=cancion;
    }

    @Override
    public void setNumRecomendaciones(int num){this.numRecomendaciones=num;}
    @Override
    public int getNumRecomendaciones(){
        return numRecomendaciones;
    }

    @Override
    public void buscarCancion() throws Exception {
        SongRecSys songRecSys = new SongRecSys(algoritmo, distancia, cancion, numRecomendaciones);
        recommended_items = songRecSys.getReportRecommendation();
    }


}
