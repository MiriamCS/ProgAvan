package es.uji.al415634.principal;

import es.uji.al415634.principal.modelo.distancia.Distance;
import es.uji.al415634.principal.vista.MainFX;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public interface Grafica {
    void setAlgoritmo(String alg);
    void setDistancia(Distance distancia);
    void setCancion(String texto);
    void setMainFx(MainFX mianFx);
    String getAlgoritmo();
    Distance getDistancia();
    String getCancion();
    int getNumRecomendaciones() throws Exception;
    void setNumRecomendaciones(int num) throws Exception;
    List<String> getRecommendedItems();
    ObservableList<String> getTitleSong(ObservableList<String> lista) throws IOException;
}
