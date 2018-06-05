package RdP;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class VectorSensibilizadas {

    private ArrayRealVector v;
    private boolean esperando;
    private SensibilizadoConTiempo tiempo;
    private Array2DRowRealMatrix mbackI;

    public VectorSensibilizadas(ArrayRealVector v, Array2DRowRealMatrix m){

        this.v = v;
        mbackI = m;
        tiempo = new SensibilizadoConTiempo();
    }

    public boolean estaSensibilizado(ArrayRealVector disp){
        return true;
    }

    public void actualiceSensibilizadoT(ArrayRealVector marcado){

        v = (ArrayRealVector)mbackI.transpose().operate(v); //not sure about this
    }

    public ArrayRealVector getV(){return v;}

}
