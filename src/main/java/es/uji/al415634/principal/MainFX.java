package es.uji.al415634.principal;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class MainFX extends Application {
    private String cancionSeleccionada;

    public static void main(String[] args) {
        launch(args);
    }
    public static Controlador controlador = new Controlador();

    @Override
    public void start(Stage primaryStage) throws IOException {
        start1(primaryStage);
        Stage secondaryStage = new Stage();
        start2(secondaryStage);
    }

        public void start1(Stage primaryStage) throws IOException {
            //primera ventana emergente
            primaryStage.setTitle("Song Recommender");

            //primer apartado
            ToggleGroup grupo1 = new ToggleGroup();
            Label titulo1 = new Label("Recommendation Type");
            RadioButton knn = new RadioButton("Recommended based on song features");
            /*knn.setOnAction(e ->{
                controlador.tipoRecomendacion("knn");
            });*/
            RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
            /*kmeans.setOnAction(e ->{
                controlador.tipoRecomendacion("kmeans");
            });*/
            VBox caja1 = new VBox(titulo1, knn, kmeans);
            //Ajustes de la caja1
            caja1.setSpacing(10);
            caja1.setPadding(new Insets(10));
            //Asignarle grupo
            knn.setToggleGroup(grupo1);
            kmeans.setToggleGroup(grupo1);

            //segundo apartado
            ToggleGroup grupo2 = new ToggleGroup();
            Label titulo2 = new Label("Distance Type");
            RadioButton eucl = new RadioButton("Euclidean");
            /*eucl.setOnAction(e ->{
                controlador.tipoDistancia(new EuclideanDistance());
            });*/
            RadioButton manh = new RadioButton("Manhattan");
            /*manh.setOnAction(e ->{
                controlador.tipoDistancia(new ManhattanDistance());
            });*/
            VBox caja2 = new VBox(titulo2, eucl, manh);
            //Ajustes de la caja2
            caja2.setSpacing(10);
            caja2.setPadding(new Insets(10));
            //Asiganarle grupo
            eucl.setToggleGroup(grupo2);
            manh.setToggleGroup(grupo2);

            //tercer apartado
            Label titulo3 = new Label("Song Titles");
            ObservableList<String> canciones = FXCollections.observableArrayList();
            //Añadir titulos de canciones
            addTitleSong(canciones);
            //Poner el scrollPanel
            ListView<String> lista = new ListView<>(canciones);
            /*lista.getSelectionModel().selectedItemProperty().addListener((item, valorInicial, valorActual) -> {
                controlador.seleccionarCancion(valorActual);
                cancionSeleccionada = valorActual;
            });*/
            VBox caja3 = new VBox(titulo3, lista);
            //Ajustes de la caja3
            caja3.setSpacing(10);
            caja3.setPadding(new Insets(10));

            //cuarto apartado
            Button recommend = new Button("Recommend...");
            recommend.setOnAction(new Escuchadora());
            VBox caja4 = new VBox(recommend);
            //Ajustes de la caja4
            caja4.setSpacing(10);
            caja4.setPadding(new Insets(10));


            //Fusionar
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

            //Mostrarlo
            primaryStage.setScene(new Scene(root, 300, 600));
            primaryStage.show();
        }
        public void start2(Stage secondaryStage) throws IOException {

            //segundo ventana emergente
            secondaryStage.setTitle("Recommended titles");

            //primer apartado
            Label titulo1 = new Label("Number of recommendation:");
            SpinnerValueFactory<Integer> valores = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
            Spinner<Integer> spinner = new Spinner<>();
            spinner.setValueFactory(valores);
            HBox caja1 = new HBox(titulo1, spinner);
            //Ajustes caja1
            caja1.setSpacing(10);
            caja1.setPadding(new Insets(10));

            //segundo apartado
            Label titulo2 = new Label("If you like NOSEQUE you might like");
            ObservableList<String> canciones = FXCollections.observableArrayList();
            ListView<String> lista = new ListView<>(canciones);
            VBox caja2 = new VBox(titulo2,lista);
            //Ajustes de la caja2
            caja2.setSpacing(10);
            caja2.setPadding(new Insets(10));

            //tercer apartado
            Button close = new Button("Close");
            VBox caja3 = new VBox(close);
            //Ajustes de la caja4
            caja3.setSpacing(10);
            caja3.setPadding(new Insets(10));

            //Fusionar
            GridPane root = new GridPane();
            root.setPadding(new Insets(10,10,10,10));
            root.setVgap(5);
            root.setHgap(5);
            root.setAlignment(Pos.TOP_LEFT);
            //Posicionar los elementos
            root.add(caja1, 0,0);
            root.add(caja2, 0,1);
            root.add(caja3, 0,2);

            //Mostrarlo
            secondaryStage.setScene(new Scene(root, 350, 400));
            secondaryStage.show();
    }
    private class Escuchadora implements EventHandler<ActionEvent>{
        @Override
        public void handle (ActionEvent actionEvent){
            System.out.println("¡Ouch! Me pulsastes");
        }
    }
    private ObservableList<String> addTitleSong(ObservableList<String> lista) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/es/uji/al415634/Files/songs_train_names.csv"));
        String cadena;
        while((cadena= buffer.readLine())!= null){
            lista.add(cadena);
        }
        return lista;
    }
}
