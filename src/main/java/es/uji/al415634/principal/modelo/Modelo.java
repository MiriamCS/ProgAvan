package es.uji.al415634.principal.modelo;

import es.uji.al415634.principal.Grafica;
import es.uji.al415634.principal.modelo.canciones.SongRecSys;
import es.uji.al415634.principal.modelo.distancia.Distance;
import javafx.collections.FXCollections;
import es.uji.al415634.principal.vista.MainFX;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Modelo implements Grafica {
    private String algoritmo;
    private Distance distancia;
    private String cancion;
    private int numRecomendaciones;
    private List<String> recommendedItems;
    private int numBuscadas=0;
    private List<String> recommendedBuscadas;
    private MainFX mainFX;
    private boolean estado=false;
    private int valueMax=100;

    private final ObservableList<String> cancionesRecomendadas = FXCollections.observableArrayList();

    public ObservableList<String> getCancionesRecomendadas(){return cancionesRecomendadas;}

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
    public void setMainFx(MainFX mainFX){
        this.mainFX=mainFX;
    }
    @Override
    public String getAlgoritmo() {return algoritmo;}
    @Override
    public Distance getDistancia() {return distancia;}
    @Override
    public String getCancion() {return cancion;}
    @Override
    public int getNumRecomendaciones() {
        return numRecomendaciones;
    }
    @Override
    public List<String> getRecommendedItems(){
        actualizarDatos(recommendedItems);
        return recommendedItems;
    }
    public void buscarCancion() throws Exception {
        //Busca 10 de más, pero solo muestra las pedidas, así tiene un margen en el que el cliente
        //puede aumentar 10 más sin que tenga que volver a calcularlas, cuando se pasa de ese margen vuelve a
        // calcular 10 de más del número pedido, para volver a tener ese margen
        SongRecSys songRecSys;
        if (numRecomendaciones > numBuscadas) {
            songRecSys = new SongRecSys(algoritmo, distancia, cancion, numRecomendaciones+10);
            recommendedBuscadas = songRecSys.getReportRecommendation();
        }
        recommendedItems = new ArrayList<>();
        if(numRecomendaciones > recommendedBuscadas.size()){
            numRecomendaciones = recommendedBuscadas.size();
        }
        for (int i= 0; i<numRecomendaciones; i++){
            recommendedItems.add(recommendedBuscadas.get(i));
        }
        numBuscadas= numRecomendaciones +10;
    }
    @Override
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

    @Override
    public boolean getEstado() {
        return estado;
    }

    @Override
    public int getValueMax() {
        return valueMax;
    }

    private void actualizarDatos(List<String> lista){
        cancionesRecomendadas.clear();
        cancionesRecomendadas.addAll(lista);
        //Si el número de elementos es menor que numRec...
        if(lista.size() < numRecomendaciones){
            estado=true;
            valueMax=lista.size();
            mainFX.notificar(lista);
        }else{
            estado=false;
            valueMax=100;
        }
    }
}
