package RdP;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class VectorDeEstado {

    private ArrayRealVector v;

    public VectorDeEstado(ArrayRealVector v){
        this.v = v;
    }

    public void calculoDeVectorEstado(){

    }

    public ArrayRealVector getVec() {
        return v;
    }
}
