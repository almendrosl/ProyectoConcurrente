package test.Monitor;

import Monitor.Politicas;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
* Politicas Tester. 
* 
* @author <Authors name> 
* @since <pre>jun 4, 2018</pre> 
* @version 1.0 
*/ 
public class PoliticasTest {

    public static Politicas politicas;
    public static ArrayRealVector realVector;

    @BeforeAll
    public static void before() throws Exception {
        double[] vector = {2,1,3,0};
        realVector = new ArrayRealVector(vector, true);
        politicas = new Politicas(realVector);
    }

    @Test
    public void testArray2DRowRealMatrix() throws Exception {
        double[][] matriz = {{0,0,1,0},{0,1,0,0},{0,0,0,1},{1,0,0,0}};
        Array2DRowRealMatrix matrizDePrioridades = new Array2DRowRealMatrix(matriz, true);
        assertEquals(matrizDePrioridades, politicas.getMatrizdePrioridades());
    }

    @Test
    public void testMasPrioridad() throws Exception {
        double[] vector = {1,0,0,1};
        ArrayRealVector realVecto = new ArrayRealVector(vector, true);
        assertEquals(3, politicas.masPrioridad(realVecto));
    }

} 
