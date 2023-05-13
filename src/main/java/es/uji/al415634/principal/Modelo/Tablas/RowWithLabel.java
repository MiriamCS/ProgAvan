package es.uji.al415634.principal.Modelo.Tablas;

import java.util.List;

public class RowWithLabel extends Row{
    private int numberClass;

    //Constructor
    public RowWithLabel(List<Double> data, int numberClass){
       super(data);
       this.numberClass = numberClass;
    }

    public int getNumberClass() {
        return numberClass;
    }
}
