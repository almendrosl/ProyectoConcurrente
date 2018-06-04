package RdP;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class VectorSensibilizadas {

    private ArrayRealVector v;

    public VectorSensibilizadas(ArrayRealVector v){
        this.v = v;
    }

    public boolean estaSensibilizado(ArrayRealVector disp){
        return true;
    }

    public void actualiceSensibilizadoT(ArrayRealVector marcado){}

    public ArrayRealVector getV(){return v;}

}
