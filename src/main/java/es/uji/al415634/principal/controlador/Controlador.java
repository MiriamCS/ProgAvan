package es.uji.al415634.principal.controlador;


import es.uji.al415634.principal.modelo.canciones.SongRecSys;
import es.uji.al415634.principal.modelo.distancia.Distance;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Controlador implements Grafica {
    public String algoritmo;
    public Distance distancia;
    public String cancion;
    private int numRecomendaciones;
    private List<String> recommendedItems;

    @Override
    public void setAlgoritmo(String alg) {this.algoritmo = alg;}
    @Override
    public void setDistancia(Distance distancia) {this.distancia = distancia;}
    @Override
    public void setCancion(String cancion) {
        this.cancion=cancion;
    }

    @Override
    public void setNumRecomendaciones(int num) throws Exception {
        this.numRecomendaciones=num;
        buscarCancion();
    }
    @Override
    public int getNumRecomendaciones() {
        return numRecomendaciones;
    }

    @Override
    public List<String> getRecommendedItems(){
        return recommendedItems;
    }

    private void buscarCancion() throws Exception {
        SongRecSys songRecSys = new SongRecSys(algoritmo, distancia, cancion, numRecomendaciones);
        recommendedItems = songRecSys.getReportRecommendation();
    }

    public ObservableList<String> getTitleSong(ObservableList<String> lista) throws IOException {
        String sep = System.getProperty("file.separator");
        String ruta = "src/main/java/es/uji/al415634/Files";
        BufferedReader buffer = new BufferedReader(new FileReader(ruta+sep+"songs_test_names.csv"));
        String cadena;
        while((cadena= buffer.readLine())!= null){
            lista.add(cadena);
        }
        return lista;
    }
}
