package es.uji.al415634.principal;

import es.uji.al415634.principal.Distancia.Distance;

import java.util.List;

public interface Grafica {
    void setRecomendacion(String rec);
    void setDistancia(Distance distancia);
    void setCancion(String texto);
    void setNumRecomendaciones(int num);
    void buscarCancion()throws  Exception;
    String getCancion();
    List<String> getRecommendation();

}
