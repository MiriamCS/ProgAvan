package es.uji.al415634.principal.Algoritmos;

import es.uji.al415634.principal.Tablas.Table;

public interface Algorithm<V,W>{
    void train(Table data);

   W estimate(V sample);

}
