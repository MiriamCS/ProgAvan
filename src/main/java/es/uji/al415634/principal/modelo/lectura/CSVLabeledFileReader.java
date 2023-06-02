package es.uji.al415634.principal.modelo.lectura;

import es.uji.al415634.principal.modelo.tablas.RowWithLabel;
import es.uji.al415634.principal.modelo.tablas.Table;
import es.uji.al415634.principal.modelo.tablas.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends CSVUnlabeledFileReader{
    public CSVLabeledFileReader(String nombreFichero) {
        super(nombreFichero);
    }
    @Override
    public void processdata(String data){
        String etiqueta = data.split(",")[tam-1];
        int index =((TableWithLabels) tabla).addToMap(etiqueta);
        tabla.addRow(new RowWithLabel(data(data), index));
    }

    @Override
    //método auxiliar
    public List<Double> data(String data){
        String[] vectorDatos = new String[tam];
        for (int i = 0; i< tam-1; i++){
            vectorDatos[i] = data.split(",")[i];
        }
        List<Double> datos = new ArrayList<>();
        for(int j = 0; j<tam-1; j++){
            Double num = Double.parseDouble(vectorDatos[j]);
            datos.add(num);
        }
        return datos;
    }

    @Override
    public Table createTable(){
        return new TableWithLabels();
    }

}
