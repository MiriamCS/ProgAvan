package es.uji.al415634.principal.controlador;

import es.uji.al415634.principal.modelo.distancia.Distance;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public interface Grafica {
    void setAlgoritmo(String alg);
    void setDistancia(Distance distancia);
    void setCancion(String texto);
    void setNumRecomendaciones(int num) throws Exception;
    //void buscarCancion()throws  Exception;
    int getNumRecomendaciones() throws Exception;
    //Nuevo
    List<String> getRecommendedItems();
    ObservableList<String> getTitleSong(ObservableList<String> lista) throws IOException;
}
