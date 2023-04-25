package es.uji.al415634.principal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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

        ToggleGroup grupo2 = new ToggleGroup();
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
        primaryStage.show();

    }
}
