package es.uji.al415634.principal;

import es.uji.al415634.principal.Distancia.Distance;

public interface Grafica {
    void setAlgoritmo(String alg);
    void setDistancia(Distance distancia);
    void setCancion(String texto);
    void setNumRecomendaciones(int num);
    void buscarCancion()throws  Exception;
    int getNumRecomendaciones();

}
