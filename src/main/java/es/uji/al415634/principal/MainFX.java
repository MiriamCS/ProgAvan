package es.uji.al415634.principal;

import es.uji.al415634.principal.Distancia.EuclideanDistance;
import es.uji.al415634.principal.Distancia.ManhattanDistance;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainFX extends Application {
    private final Stage secondaryStage = new Stage();
    public static Controlador controlador = new Controlador();
    private final ObservableList<String> cancionesRecomendadas = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        start1(primaryStage);
    }

    public void start1(Stage primaryStage) throws IOException {
        //primera ventana emergente
        primaryStage.setTitle("Song Recommender");

        //ALGORTIMO
        ToggleGroup grupo1 = new ToggleGroup();
        Label titulo1 = new Label("Recommendation Type");
        RadioButton knn = new RadioButton("Recommended based on song features");
        knn.setOnAction(e -> controlador.setRecomendacion("knn"));
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        kmeans.setOnAction(e -> controlador.setRecomendacion("kmeans"));
        VBox caja1 = new VBox(titulo1, knn, kmeans);
            //Ajustes de la caja1
        caja1.setSpacing(10);
        caja1.setPadding(new Insets(10));
            //Asignarle grupo
        knn.setToggleGroup(grupo1);
        kmeans.setToggleGroup(grupo1);

        //DISTANCIA
        ToggleGroup grupo2 = new ToggleGroup();
        Label titulo2 = new Label("Distance Type");
        RadioButton eucl = new RadioButton("Euclidean");
        eucl.setOnAction(e ->controlador.setDistancia(new EuclideanDistance()));
        RadioButton manh = new RadioButton("Manhattan");
        manh.setOnAction(e -> controlador.setDistancia(new ManhattanDistance()));
        VBox caja2 = new VBox(titulo2, eucl, manh);
            //Ajustes de la caja2
        caja2.setSpacing(10);
        caja2.setPadding(new Insets(10));
            //Asiganarle grupo
        eucl.setToggleGroup(grupo2);
        manh.setToggleGroup(grupo2);

        //ELEGIR CANCIÓN
        Label titulo3 = new Label("Song Titles");
        ObservableList<String> canciones = FXCollections.observableArrayList();
            //Añadir titulos de canciones
        addTitleSong(canciones);
            //Poner el scrollPanel
        ListView<String> lista = new ListView<>(canciones);
        lista.getSelectionModel().selectedItemProperty().addListener((item, valorInicial, valorActual) -> controlador.setCancion(valorActual));
        VBox caja3 = new VBox(titulo3, lista);
            //Ajustes de la caja3
        caja3.setSpacing(10);
        caja3.setPadding(new Insets(10));

        //BOTÓN RECOMMEND
        Button recommend = new Button("Recommend...");
        recommend.setOnAction(e ->{
            start2(secondaryStage);
            secondaryStage.show();
            primaryStage.close();
            try {
                controlador.buscarCancion();
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
            actualizarDatos(controlador.getRecommendations());
        });
        VBox caja4 = new VBox(recommend);
            //Ajustes de la caja4
        caja4.setSpacing(10);
        caja4.setPadding(new Insets(10));

        //FUSIONAR
        GridPane root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(5);
        root.setHgap(5);
        root.setAlignment(Pos.TOP_LEFT);
            //Posicionar los elementos
        root.add(caja1, 0, 0);
        root.add(caja2, 0, 1);
        root.add(caja3, 0, 2);
        root.add(caja4, 0, 3);

        //MOSTRARLO
        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.show();
    }
    public void start2(Stage secondaryStage){

        //segundo ventana emergente
        secondaryStage.setTitle("Recommended titles");

        //NÚMERO RECOMENDACIONES
        Label titulo1 = new Label("Number of recommendation:");
        controlador.setNumRecomendaciones(5);
        Spinner<Integer> spinner = new Spinner<>(1,100,5);
        spinner.valueProperty().addListener((item, valorInicial, valorActual) ->{
            controlador.setNumRecomendaciones(valorActual);
            try {
                controlador.buscarCancion();
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
            actualizarDatos(controlador.getRecommendations());
        });
        HBox caja1 = new HBox(titulo1, spinner);
            //Ajustes caja1
        caja1.setSpacing(10);
        caja1.setPadding(new Insets(10));

        //RECOMENDACIONES
        Label titulo2 = new Label("If you like '"+controlador.getCancion()+"' you might like");
        //ObservableList<String> canciones = FXCollections.observableArrayList();
        ListView<String> lista = new ListView<>(cancionesRecomendadas);
        VBox caja2 = new VBox(titulo2,lista);
        //Ajustes de la caja2
        caja2.setSpacing(10);
        caja2.setPadding(new Insets(10));

        //BOTÓN CLOSE
        Button close = new Button("Close");
        close.setOnAction(e ->secondaryStage.close());
        VBox caja3 = new VBox(close);
        //Ajustes de la caja4
        caja3.setSpacing(10);
        caja3.setPadding(new Insets(10));

        //FUSIONAR
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(5);
        root.setHgap(5);
        root.setAlignment(Pos.TOP_LEFT);
            //Posicionar los elementos
        root.add(caja1, 0,0);
        root.add(caja2, 0,1);
        root.add(caja3, 0,2);

        //MOSTRARLO
        secondaryStage.setScene(new Scene(root,  titulo2.getMaxWidth() , 400));
    }

    private void addTitleSong(ObservableList<String> lista) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/es/uji/al415634/Files/songs_test_names.csv"));
        String cadena;
        while((cadena= buffer.readLine())!= null){
            lista.add(cadena);
        }
    }

    private void actualizarDatos(List<String> lista){
        cancionesRecomendadas.clear();
        cancionesRecomendadas.addAll(lista);
    }
}