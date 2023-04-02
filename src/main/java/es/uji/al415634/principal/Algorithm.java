package es.uji.al415634.principal;

public interface Algorithm<V,W>{
    void train(Table data);

   W estimate(V sample);

}
