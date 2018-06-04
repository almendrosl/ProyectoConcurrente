package RdP;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;

public class VectorDeEstado {

    private ArrayRealVector marcado;

    /*
        Este constructor recibe un vector. Esta hecho de esta forma para poder usar bien las interfaces.
     */
    public VectorDeEstado(ArrayRealVector v){
        marcado = v;
    }

    /*
        Este metodo calcula el nuevo marcado usando el disparo y la matriz de incidencia combinada I. Luego acutaliza
        el vector de sensibilizados.
     */
    public void calculoDeVectorEstado(ArrayRealVector disparo, Array2DRowRealMatrix matrizI, VectorSensibilizadas sensibilizadas){
        marcado = marcado.add(matrizI.operate(disparo));
        sensibilizadas.actualiceSensibilizadoT(marcado);
    }

    /*
        Este metodo devuelve el vector de marcado.
     */
    public ArrayRealVector getMarcado(){return marcado;}
}
