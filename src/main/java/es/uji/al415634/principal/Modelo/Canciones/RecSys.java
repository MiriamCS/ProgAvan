package es.uji.al415634.principal.Modelo.Canciones;

import es.uji.al415634.principal.Modelo.Algoritmos.Algorithm;
import es.uji.al415634.principal.Modelo.Excepcion.NumeroClusterNoValidoException;
import es.uji.al415634.principal.Modelo.Tablas.Table;

import java.util.*;

//Al instanciar RecSye le pasaremos al constructor una instancia de un algoritmo concreto
//Es decir objeto de la clase KMeans o KNN
//Cuando se usa train() o estimate(), son los correspondientes a KMeans o KNN

public class RecSys{
    private final Algorithm algorithm;
    private final HashMap<Integer, List<Integer>> etiquetaEstimada;
    private Table testData;
    private List<String> testItemNames;

    //Constructor
    public RecSys(Algorithm algorithm){
        this.algorithm = algorithm;
        etiquetaEstimada = new HashMap<>();
    }

    public void train(Table trainData) throws NumeroClusterNoValidoException {
        //Entrenar al algoritmo correspondiente
        algorithm.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames){
        this.testData = testData;
        this.testItemNames = testItemNames;
        //Predecir (Estimar) la clase de los elementos de testData
        //Es decir, invocar a estimate para cada una de las filas de testData
        for (int i = 0; i< testData.listaRows.size(); i++){
            List<Integer> estimaciones = new ArrayList<>(); //Inicializo values por cada elemento

            List<Double> dato = testData.listaRows.get(i).getData();//Obtengo el dato
            Integer estimacion = (Integer) algorithm.estimate(dato);//Obtengo etiqueta

            if(etiquetaEstimada.containsKey(estimacion)){//Si ya había elementos con esa etiqueta los obtengo
                estimaciones = etiquetaEstimada.get(estimacion);
            }
            estimaciones.add(i);//Añado el índice del elemento

            etiquetaEstimada.put(estimacion, estimaciones);//Relaciono la etiqueta con todos sus elementos
        }
    }

    private int findName(String nameItem){
        //Devuelve el identificador de clase de nameItem para el paso 1a de recommend()
        for(int i = 0; i<testItemNames.size(); i++){
            if (testItemNames.get(i).equals(nameItem))
                return i;
        }
        return -1;
    }

    private List<Integer> selectItems(int idxLikedItem, Integer labelLikedItem, int numRec){
        //Para el paso 3a de recommend()
        List<Integer> listaIndRec = new ArrayList<>();
        List<Integer> estimaciones = etiquetaEstimada.get(labelLikedItem);
        //int cant= estimaciones.size();
        //El for será numRec a no ser que hayan menos elementos que numRec, si hay menos serán el número de elementos
        for (int i = 0; i<numRec; i++){
            try{
                if(estimaciones.get(i) == idxLikedItem){ //Si es la original, la salta y buscará una más
                    numRec++;
                }
                else {
                    listaIndRec.add(estimaciones.get(i)); //Añado a listaIndRec todos los índices de los elementos con la misma etiqueta
                }
            }catch (IndexOutOfBoundsException excepcion){
                System.out.println();
            }
        }

        return listaIndRec;
    }

    private List<String> getNamesSelectedItems(List<Integer> listaIndRec){
        //Para el paso 3b de recommend()
        List<String> recomendaciones = new ArrayList<>();
        for(int indRec : listaIndRec){
            recomendaciones.add(testItemNames.get(indRec));
        }
        return recomendaciones;
    }

    private List<Double> obtenerDato(int idx, Table tabla){
        return tabla.listaRows.get(idx).getData();
    }

    public List<String> recommend(String nameLinkedItem, int numRecommendations){

        //1) Averiguar las características del elemento

        //1a) Obtener el índice de nameLinkedItem en testItemNames
        int idx = findName(nameLinkedItem);
        //1b) Obtener el dato en testData en la posición idx

        List<Double> dato = obtenerDato(idx,testData);
        //List<Double> dato = testData.listaRows.get(idx).getData();

        //2) Obtener la etiqueta lbl estimada para dicho elemento
        Integer lbl = (Integer) algorithm.estimate(dato);

        //3) Localizar en testData elementos que tengan el mismo lbl, obtener sus nombres

        //3a) Obtener una lista de hasta numRecommendations indices con dicha etiqueta
        List<Integer> listaIndRec = selectItems(idx, lbl, numRecommendations);

        //3b) Utilizar listaIndRec para averiguar la lista de nombres (elementos a recomendar)
        return getNamesSelectedItems(listaIndRec);

    }


}