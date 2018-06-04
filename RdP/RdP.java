package RdP;
import java.util.*;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;

public class RdP {

    private VectorDeEstado vectorDeEstado;
    private MatrizI matrizI;
    private VectorSensibilizadas vectorSensibilizadas;
    private int transiciones;
    private int plazas;

    public RdP(EstadoInicial conjuntoInicial)
    {
        HashMap<String,RealMatrix> matrices = conjuntoInicial.getMatrices();
        HashMap<String,ArrayRealVector> vectores = conjuntoInicial.getVectores();
        ArrayRealVector aux = vectores.get("Marking");
        this.vectorDeEstado = new VectorDeEstado(aux);
        //this.matrizI = (MatrizI) matrices.get("Combined incidence matrix I");
        aux = vectores.get("Enabled transitions");
        this.vectorSensibilizadas = new VectorSensibilizadas(aux);
        //this.transiciones = matrizI.getColumnDimension();
        //this.plazas = matrizI.getRowDimension();
    }

    public boolean disparar(int transicionDeseada){
        ArrayRealVector disparo = new ArrayRealVector(transiciones);
        disparo.set(0);
        disparo.setEntry(transicionDeseada,1);
        if(vectorSensibilizadas.estaSensibilizado(disparo)){
            vectorDeEstado.calculoDeVectorEstado();
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayRealVector getVectorDeEstado() {
        return vectorDeEstado.getVec();
    }

    public VectorSensibilizadas getSensibilizadas(){
        return vectorSensibilizadas;
    }
}
