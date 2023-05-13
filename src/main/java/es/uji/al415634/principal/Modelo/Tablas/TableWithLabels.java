package es.uji.al415634.principal.Modelo.Tablas;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableWithLabels extends Table {
    public Map<String, Integer> labelsToIndex;

    //Constructor
    public TableWithLabels(){
        listaRows = new ArrayList<>();
        labelsToIndex = new HashMap<>();
    }
    @Override
    public RowWithLabel getRowAt(int rowNumber){
        return (RowWithLabel) listaRows.get(rowNumber);
    }

    public int addToMap(String etiqueta) {
        if (!labelsToIndex.containsKey(etiqueta)) {
            labelsToIndex.put(etiqueta, labelsToIndex.size());
        }
        return labelsToIndex.get(etiqueta);
    }
}