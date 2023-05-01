package es.uji.al415634.principal;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Song Recommender");

        //primer apartado
        ToggleGroup grupo1 = new ToggleGroup();
        Label titulo1 = new Label("Recommendation Type");
        RadioButton knn = new RadioButton("Recommended based on song features");
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        VBox caja1 = new VBox(titulo1,knn, kmeans);
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
        RadioButton manh = new RadioButton("Manhattan");
        VBox caja2 = new VBox(titulo2,eucl, manh);
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
        VBox caja3 = new VBox(titulo3,lista);
        //Ajustes de la caja3
        caja3.setSpacing(10);
        caja3.setPadding(new Insets(10));

        //cuarto apartado
        Button recommend = new Button("Recommend...");
        VBox caja4 = new VBox(recommend);
        //Ajustes de la caja4
        caja4.setSpacing(10);
        caja4.setPadding(new Insets(10));


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
        root.add(caja4,0,3);

        //Mostrarlo
        primaryStage.setScene(new Scene(root, 400, 800));
        primaryStage.show();

        /*primaryStage.setTitle("Hello World!");
        StackPane root = new StackPane();
        //Button btn = new Button("Hola");
        CheckBox cafe = new CheckBox("café");
        CheckBox cortado = new CheckBox("cortado");
        CheckBox cafeConLeche = new CheckBox("café con leche");
        VBox vBox = new VBox(cafe, cortado,cafeConLeche);
        //root.getChildren().add(btn);
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root, 250, 250));
        primaryStage.show();*/

        /*primaryStage.setTitle("Recommendation Type");
        StackPane root1 = new StackPane();
        CheckBox knn = new CheckBox("Recommended based on song features");
        CheckBox kmeans = new CheckBox("Recommend based on guessed genre");
        VBox vBox1 = new VBox(knn, kmeans);
        root1.getChildren().add(vBox1);
        primaryStage.setScene(new Scene(root1, 250, 250));
        primaryStage.show();*/

        /*ToggleGroup grupo2 = new ToggleGroup();
        primaryStage.setTitle("Distance Type");
        StackPane root2 = new StackPane();
        RadioButton eucl = new RadioButton("Euclidean");
        eucl.setOnAction(e -> System.out.println("Selecciona Eculidea"));
        RadioButton manh = new RadioButton("Manhattan");
        manh.setOnAction(e -> System.out.println("Selecciona Manhattan"));
        VBox vBox1 = new VBox(eucl, manh);
        root2.getChildren().add(vBox1);
        eucl.setToggleGroup(grupo2);
        manh.setToggleGroup(grupo2);
        primaryStage.setScene(new Scene(root2, 250, 250));
        primaryStage.show();*/
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
