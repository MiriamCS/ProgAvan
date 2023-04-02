package es.uji.al415634.principal;

import java.util.List;

public class RowWithLabel extends Row{
    public int numberClass;

    //Constructor
    public RowWithLabel(List<Double> data, int numberClass){
       super(data);
       this.numberClass = numberClass;
    }

    public int getNumberClass() {
        return numberClass;
    }
}
