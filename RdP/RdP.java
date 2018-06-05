package RdP;
import java.util.*;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;

public class RdP {

    private VectorDeEstado vectorDeEstado;
    private Array2DRowRealMatrix matrizI;
    private VectorSensibilizadas vectorSensibilizadas;
    private int transiciones;
    private int plazas;

    /*
        Este construtor toma la configuracion inicial, que es un objeto que tiene el conjunto de matrices (I+, I-, I, H)
        y el conjunto de vectores de marco y trnasiciones sensibilizadas del estado inicial, y los transforma en las
        variables correspondientes.
     */
    public RdP(EstadoInicial conjuntoInicial)
    {
        HashMap<String,Array2DRowRealMatrix> matrices = conjuntoInicial.getMatrices();
        HashMap<String,ArrayRealVector> vectores = conjuntoInicial.getVectores();
        vectorDeEstado = new VectorDeEstado(vectores.get("Marking"));
        matrizI =  matrices.get("Combined incidence matrix I");
        vectorSensibilizadas = new VectorSensibilizadas(vectores.get("Enabled transitions"),matrizI,matrices.get("Inhibition matrix H"));
        transiciones = matrizI.getColumnDimension();
        plazas = matrizI.getRowDimension();
    }

    /*
        Este metodo se encarga de hacer el disparo pedido por el actor. Primero se fija si la transicion esta sensibili-
        zada; si lo esta, calculamos el nuevo marcado y devolvemos un true, avisando que el disparo se pudo realizar.
     */
    public boolean disparar(int transicionDeseada){
        ArrayRealVector disparo = new ArrayRealVector(transiciones);
        disparo.set(0);
        disparo.setEntry(transicionDeseada,1);
        if(vectorSensibilizadas.estaSensibilizado(disparo)){
            vectorDeEstado.calculoDeVectorEstado(disparo, matrizI, vectorSensibilizadas);
            return true;
        }
        else{
            return false;
        }
    }

    /*
        Este metodo devuelve el vector de las transiciones sensibilizadas.
     */
    public ArrayRealVector sensibilizadas(){
        return vectorSensibilizadas.getV();
    }
}
