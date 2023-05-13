package es.uji.al415634.principal.Controlador;

import es.uji.al415634.principal.Modelo.Distancia.Distance;

public interface Grafica {
    void setAlgoritmo(String alg);
    void setDistancia(Distance distancia);
    void setCancion(String texto);
    void setNumRecomendaciones(int num);
    void buscarCancion()throws  Exception;
    int getNumRecomendaciones();

}
