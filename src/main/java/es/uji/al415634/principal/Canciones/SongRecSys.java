package es.uji.al415634.principal.Canciones;

import es.uji.al415634.principal.Algoritmos.Algorithm;
import es.uji.al415634.principal.Algoritmos.KMeans;
import es.uji.al415634.principal.Algoritmos.KNN;
import es.uji.al415634.principal.Distancia.Distance;
import es.uji.al415634.principal.Lectura.CSV;
import es.uji.al415634.principal.Tablas.Table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongRecSys {
    private RecSys recsys;
    private Distance distance;
    private List<String> recommended_items;

    public SongRecSys(String method, Distance distance, String cancion, int numRecomendaciones) throws Exception {
        System.out.println(cancion);
        this.distance = distance;
        String sep = System.getProperty("file.separator");
        String ruta = "src/main/java/es/uji/al415634/Files";

        // File names (could be provided as arguments to the constructor to be more general)
        Map<String,String> filenames = new HashMap<>();
        filenames.put("knn"+"train",ruta+sep+"songs_train.csv");
        filenames.put("knn"+"test",ruta+sep+"songs_test.csv");
        filenames.put("kmeans"+"train",ruta+sep+"songs_train_withoutnames.csv");
        filenames.put("kmeans"+"test",ruta+sep+"songs_test_withoutnames.csv");

        // Algorithms
        Map<String, Algorithm> algorithms = new HashMap<>();

        algorithms.put("knn",new KNN(distance));
        algorithms.put("kmeans",new KMeans(15, 200, 4321, distance));

        // Tables
        Map<String, Table> tables = new HashMap<>();
        String [] stages = {"train", "test"};
        CSV csv = new CSV();
        //HAY QUE MODIFICAR ESTO
        for (String stage : stages) {
            tables.put("knn" + stage, csv.readTableLabels(filenames.get(method + stage)));
            //tables.put("kmeans" + stage, csv.readTable(filenames.get("kmeans" + stage)));
        }

        // Names of items
        List<String> names = readNames(ruta+sep+"songs_test_names.csv");

        // Start the RecSys
        this.recsys = new RecSys(algorithms.get(method));
        this.recsys.train(tables.get(method+"train"));
        this.recsys.run(tables.get(method+"test"), names);

        //HAY QUE MODIFICAR ESTO
        // Given a liked item, ask for a number of recomendations
        List<String> recommended_items = this.recsys.recommend(cancion,numRecomendaciones);

        // Display the recommendation text (to be replaced with graphical display with JavaFX implementation)
        //reportRecommendation(cancion,recommended_items);
        reportRecommendation(recommended_items);
    }

    private List<String> readNames(String fileOfItemNames) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileOfItemNames));
        String line;
        List<String> names = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            names.add(line);
        }
        br.close();
        return names;
    }

    //HAY QUE MODIFICAR ESTO, quitar esto y moverlo en controladores :)
    /*private void reportRecommendation(String liked_name, List<String> recommended_items) {
        System.out.println("If you liked \""+liked_name+"\" then you might like:");
        for (String name : recommended_items)
        {
            System.out.println("\t * "+name);
        }
    }*/

    private void reportRecommendation(List<String> recommended_items) {
      this.recommended_items=recommended_items;
    }

    public List<String> getReportRecommendation(){
        return recommended_items;
    }

}