package es.uji.al415634.principal.vista;

import es.uji.al415634.principal.controlador.Controlador;
import es.uji.al415634.principal.modelo.Modelo;
import es.uji.al415634.principal.modelo.distancia.EuclideanDistance;
import es.uji.al415634.principal.modelo.distancia.ManhattanDistance;
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
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class MainFX extends Application {
    private final Stage secondaryStage = new Stage();
    //Modificado
    private Stage primaryStage;
    private final Controlador controlador = new Controlador();
    private final Modelo modelo= controlador.getModelo();
    private Label siTeGusta;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        modelo.setMainFx(this);
        this.primaryStage=primaryStage;
        //primera ventana emergente
        primaryStage.setTitle("Song Recommender");

        //ALGORTIMO
        VBox caja1 = obtenerAlgorimo();
        //DISTANCIA
        VBox caja2 = obtenerDistancia();
        //ELEGIR CANCIÓN
        VBox caja3 = obtenerCancion();
        //BOTÓN RECOMMEND
        VBox caja4 = botonRecommend(primaryStage);

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
        HBox caja1 = cantRecomendaciones();
        //RECOMENDACIONES
        VBox caja2 = recomendaciones();
        //BOTÓN CLOSE
        VBox caja3 = botonClose();

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
        secondaryStage.setScene(new Scene(root,  siTeGusta.getMaxWidth() , 400));
    }

    public void startAlert(Alert alert, String mensaje){
        alert.setTitle("WARNING");
        alert.setContentText(mensaje);
    }

    public VBox obtenerAlgorimo(){
        ToggleGroup grupo1 = new ToggleGroup();
        Label titulo1 = new Label("Recommendation Type");
        RadioButton knn = new RadioButton("Recommended based on song features");
        knn.setOnAction(e -> modelo.setAlgoritmo("knn"));
        RadioButton kmeans = new RadioButton("Recommend based on guessed genre");
        kmeans.setOnAction(e -> modelo.setAlgoritmo("kmeans"));
        VBox caja1 = new VBox(titulo1, knn, kmeans);
        //Ajustes de la caja1
        caja1.setSpacing(10);
        caja1.setPadding(new Insets(10));
        //Asignarle grupo
        knn.setToggleGroup(grupo1);
        kmeans.setToggleGroup(grupo1);

        return caja1;
    }

    public VBox obtenerDistancia(){
        ToggleGroup grupo2 = new ToggleGroup();
        Label titulo2 = new Label("Distance Type");
        RadioButton eucl = new RadioButton("Euclidean");
        eucl.setOnAction(e -> modelo.setDistancia(new EuclideanDistance()));
        RadioButton manh = new RadioButton("Manhattan");
        manh.setOnAction(e -> modelo.setDistancia(new ManhattanDistance()));
        VBox caja2 = new VBox(titulo2, eucl, manh);
        //Ajustes de la caja2
        caja2.setSpacing(10);
        caja2.setPadding(new Insets(10));
        //Asiganarle grupo
        eucl.setToggleGroup(grupo2);
        manh.setToggleGroup(grupo2);

        return caja2;
    }

    public VBox obtenerCancion() throws IOException {
        Label titulo3 = new Label("Song Titles");
        ObservableList<String> canciones = FXCollections.observableArrayList();
        //Añadir titulos de canciones
        modelo.getTitleSong(canciones);
        //Poner el scrollPanel
        ListView<String> lista = new ListView<>(canciones);
        lista.getSelectionModel().selectedItemProperty().addListener((item, valorInicial, valorActual) -> modelo.setCancion(valorActual));
        VBox caja3 = new VBox(titulo3, lista);
        //Ajustes de la caja3
        caja3.setSpacing(10);
        caja3.setPadding(new Insets(10));
        return caja3;
    }

    public VBox botonRecommend(Stage primaryStage){
        Button recommend = new Button("Recommend...");
        recommend.setOnAction(e ->{
            if( modelo.getAlgoritmo() == null || modelo.getDistancia() == null || modelo.getCancion() == null){
                String faltaSeleccionar;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                if(modelo.getAlgoritmo()==null){
                    faltaSeleccionar="RECOMMENDATION TYPE";
                }else if(modelo.getDistancia()==null){
                    faltaSeleccionar="DISTANCE TYPE";
                }else{
                    faltaSeleccionar="SONG TITLE";
                }
                String mensaje = "¡¡Falta seleccionar el apartado: " + faltaSeleccionar + "!!";
                startAlert(alert, mensaje);
                alert.show();
            }
            else {
                start2(secondaryStage);
                secondaryStage.show();
                primaryStage.close();
                try {
                    modelo.setNumRecomendaciones(5); //incluye la llamada a buscarCancion
                } catch (Exception excepcion) {
                    excepcion.printStackTrace();
                }
                modelo.getRecommendedItems();
            }
        });
        VBox caja4 = new VBox(recommend);
        //Ajustes de la caja4
        caja4.setSpacing(10);
        caja4.setPadding(new Insets(10));
        return caja4;
    }

    public HBox cantRecomendaciones(){
        Label titulo1 = new Label("Number of recommendation:");
        Spinner<Integer> spinner = new Spinner<>(1,modelo.getValueMax(),5, 1);
        spinner.setRepeatDelay(Duration.INDEFINITE);
        spinner.setEditable(true);
        spinner.valueProperty().addListener((item, valorInicial, valorActual) ->{
            try {
                modelo.setNumRecomendaciones(valorActual);//incluye la llamada a buscarCancion
            } catch (Exception excepcion) {
                excepcion.printStackTrace();
            }
            modelo.getRecommendedItems();
            if(modelo.getEstado()) { //pide más recomendaciones de las que hay
                SpinnerValueFactory<Integer> valoresNuevos = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, modelo.getValueMax(), modelo.getValueMax());
                spinner.valueFactoryProperty().setValue(valoresNuevos);
            }
        });
        HBox caja1 = new HBox(titulo1, spinner);
        //Ajustes caja1
        caja1.setSpacing(10);
        caja1.setPadding(new Insets(10));
        return caja1;
    }

    public VBox recomendaciones(){
        siTeGusta = new Label("If you like '"+ modelo.getCancion()+"' you might like");
        ListView<String> lista = new ListView<>(modelo.getCancionesRecomendadas());
        VBox caja2 = new VBox(siTeGusta,lista);
        //Ajustes de la caja2
        caja2.setSpacing(10);
        caja2.setPadding(new Insets(10));
        return caja2;
    }

    public VBox botonClose(){
        Button close = new Button("Close");
        close.setOnAction(e ->{
            secondaryStage.close();
            //Modificado
            primaryStage.show();
        });
        VBox caja3 = new VBox(close);
        //Ajustes de la caja4
        caja3.setSpacing(10);
        caja3.setPadding(new Insets(10));
        return caja3;
    }

    public void notificar(List <String> lista) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String mensaje = "No hay "+ modelo.getNumRecomendaciones() +" recomendaciones, solo hay "+ lista.size() +" recomendaciones disponibles";
        startAlert(alert, mensaje);
        try{
            modelo.setNumRecomendaciones(lista.size());//incluye la llamada a buscarCancion
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        alert.show();
    }
}