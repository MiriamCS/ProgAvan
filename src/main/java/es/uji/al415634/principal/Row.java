package es.uji.al415634.principal;

import java.util.List;

public class Row {
    public List<Double> data;

    //Constructor
    public Row(List<Double> data){
        this.data = data;
    }

    public List<Double> getData(){
        return this.data;
    }

}
