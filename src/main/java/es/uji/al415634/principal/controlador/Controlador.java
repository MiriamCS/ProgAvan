package es.uji.al415634.principal.controlador;


import es.uji.al415634.principal.Grafica;
import es.uji.al415634.principal.modelo.Modelo;
import es.uji.al415634.principal.modelo.distancia.Distance;
import es.uji.al415634.principal.vista.MainFX;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.List;

public class Controlador implements Grafica {

    private final Modelo modelo = new Modelo();

    public Modelo getModelo() {
        return modelo;
    }

    public ObservableList<String> getCancionesRecomendadas(){return modelo.getCancionesRecomendadas();}
    @Override
    public void setAlgoritmo(String alg) {
        modelo.setAlgoritmo(alg);
        }
    @Override
    public void setDistancia(Distance distancia) {
        modelo.setDistancia(distancia);
        }
    @Override
    public void setCancion(String cancion) {
        modelo.setCancion(cancion);
    }
    @Override
    public void setMainFx(MainFX mainFX){
        modelo.setMainFx(mainFX);
    }
    @Override
    public String getAlgoritmo() {return modelo.getAlgoritmo();}
    @Override
    public Distance getDistancia() {return modelo.getDistancia();}
    @Override
    public String getCancion() {return modelo.getCancion();}

    @Override
    public void setNumRecomendaciones(int num) throws Exception {
        modelo.setNumRecomendaciones(num);
    }
    @Override
    public int getNumRecomendaciones() {
        return modelo.getNumRecomendaciones();
    }

    @Override
    public List<String> getRecommendedItems(){
        return modelo.getRecommendedItems();
    }

    public ObservableList<String> getTitleSong(ObservableList<String> lista) throws IOException {
        return modelo.getTitleSong(lista);
    }

    @Override
    public boolean getEstado() {
        return modelo.getEstado();
    }

    @Override
    public int getValueMax() {
        return modelo.getValueMax();
    }
}
