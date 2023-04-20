package es.uji.al415634.principal;

import java.util.*;

public class KMeans implements Algorithm<List<Double>, Integer>, DistanceClient {
    private final long seed;
    private final int numIterations;
    private final int numClusters;
    public List<List<Double>> repre;
    private Distance distance;
    private Table tabla;

    //Constructor
    public KMeans(int numClusters, int numIterations, long seed, Distance distance){
        this.seed=seed;
        this.numClusters=numClusters;
        this.numIterations=numIterations;
        repre = new ArrayList<>();
        this.distance = distance;
    }

    //Método que lanza excepcion si número de clusters es mayor que datos
    private void lanzarExcepcion(int numClusters, int umbral){
        if(numClusters>=umbral){
            throw new IndexOutOfBoundsException();
        }
    }

    public  void train(Table data){
        this.tabla = data;
        Set<Integer> indexRepr = new HashSet<>(); //Colección para evitar repetir el cluster aleatorio
        //Elegir aleatoriamente un semilla
        int i=0;
        //Comprobar si el número de grupo es mayor que el de datos
        lanzarExcepcion(numClusters, tabla.listaRows.size());
        Random random =new Random(seed);
        while (i<numClusters){
            //La semilla devolverá un número aleatorio positivo < núm elementos tabla
            int aleatorio = random.nextInt(0, tabla.listaRows.size());
            if(!indexRepr.contains(aleatorio)){
                indexRepr.add(aleatorio);
                //Añadir el cluster
                repre.add(tabla.getRowAt(aleatorio).getData());
                i++;
            }
        }

        List<Integer> de_que_cluster;
        //Repetir un número de iteraciones
        for(int j= 0; j<numIterations; j++){
            //Nuevas asignaciones. Asignar cada dato de la tabla al grupo de cuyo representante está más cerca.
            de_que_cluster= asignar();
            //Nuevos centroides. Calcular el centroide de cada grupo (según las nuevas asignaciones), que pasará a ser su nuevo representante.
            repre = nuevosCentroides(de_que_cluster);
        }
    }

    public Integer estimate(List<Double> dato) {
        int closestIndex = -1;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (int j = 0; j < repre.size(); j++) {
            double d_euclidea = distance.calculateDistance(repre.get(j), dato);
            if (d_euclidea < closestDistance) {
                closestIndex = j;
                closestDistance = d_euclidea;
            }
        }
        return closestIndex;
    }

    //Por cada elemento de la tabla, se llama a estimate() --> devuelve una lista con las estimaciones
    public List<Integer> asignar(){
        List<Integer> de_que_cluster = new ArrayList<>();
        //Por cada elemento de la tabla, que representante tiene más cerca
        for (Row row : tabla.listaRows) {
            de_que_cluster.add(estimate(row.getData()));
        }
        return de_que_cluster;
    }

    private List<Double> nuevoCentroide (List<List<Double>> elemGrupo){ //Le llegan solo los elementos de un grupo
        List<Double> puntoCentral = new ArrayList<>();
        //Para obtener tantos sumatorios como elementos de data en un dato
        List<Double> sumatorios = new ArrayList<>();
        for(int i = 0; i<elemGrupo.get(0).size(); i++){
            sumatorios.add(0.0);
        }
        int contadorElementosGrupo = elemGrupo.size();
        for(List<Double> data : elemGrupo){
            //Se hace un sumatorio de cada uno de los 3 tipos de datos
            for(int j= 0; j<data.size(); j++){
                sumatorios.set(j, sumatorios.get(j)+ data.get(j));
            }
        }
        for(int j= 0; j<tabla.listaRows.get(0).getData().size(); j++){ //Por cada elemento de un punto
            puntoCentral.add(sumatorios.get(j)/contadorElementosGrupo);
        }

        return puntoCentral;
    }

    private List<List<Double>> obtenerGrupo(int numGrupo, List<Integer> de_que_cluster){
        List<List<Double>> grupo = new ArrayList<>();
        for(int i =0; i<tabla.listaRows.size(); i++){
            if(de_que_cluster.get(i) == numGrupo){
                grupo.add(tabla.listaRows.get(i).getData());
            }
        }
        return grupo;
    }

    public List<List<Double>> nuevosCentroides(List<Integer> de_que_cluster){
        List<List<Double>> nuevosRepre = new ArrayList<>();
        List<List<Double>> grupo;
        int numGrupo;
        for(numGrupo=0; numGrupo < numClusters; numGrupo++){
            grupo = obtenerGrupo(numGrupo, de_que_cluster);
            nuevosRepre.add(nuevoCentroide(grupo));
        }
        return nuevosRepre;
    }

    public void SetDistance(Distance distance) {
        this.distance = distance;
    }
}
