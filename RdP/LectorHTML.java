package RdP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LectorHTML {
    private EstadoInicial estadoInicial = new EstadoInicial();

    public LectorHTML(String ruta)throws IOException {
        try {
            File input = new File(ruta);
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements todasLasMatrices = doc.select("body>table");
            for (Element matriz : todasLasMatrices) {
                cargarMatriz(matriz);
            }



        } catch (Exception e){
            System.err.println("Caught Exception: " + e.getStackTrace());
        }

    }


    public void cargarMatriz(Element matriz){
        String nombre = matriz.select("tr").first().text();
        double datos[][] = parsearMatriz(matriz);
        estadoInicial.cargarMatriz(nombre,datos);
    }

   public double[][] parsearMatriz(Element element){


        int cantidadFilas =   element.select("tbody tbody tr").size()-1;
        int cantidadColumnas =   element.select("tbody tbody tr:nth-child(2) td.cell").size();
        double datos[][] = new double[cantidadFilas][cantidadColumnas];
        int indexFila = 0;

        Elements filas = element.select("tbody tbody tr");

        for (Element fila : filas) {

            Elements celdas = fila.select(".cell");

            int indexDato = 0;
            for (Element cell : celdas) {
                try
                {
                    datos[indexFila][indexDato] = Double.parseDouble(cell.text().toString());
                }
                catch(Exception e)
                {
                    datos[indexFila][indexDato] = cell.text().matches("yes")?1.0:0.0;
                }


                indexDato ++;

                if(indexDato ==cantidadColumnas)
                    indexFila++;
            }

        }

      return datos;


    }

    public EstadoInicial getMatricesEstadoInicial() {
        return estadoInicial;
    }
}
