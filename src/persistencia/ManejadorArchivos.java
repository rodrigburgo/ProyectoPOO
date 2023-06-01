/**
* Conversor de Java a python
* Manejador de archivos para la interfaz grafica
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez Franco
* @version 0.8, 2023/06/01
*/

package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase principal que representa el manejador de archivos
 */
public class ManejadorArchivos {
    /**
     * Lee el contenido de un archivo.
     * 
     * @param rutaArchivo la ruta del archivo a leer
     * @return el contenido del archivo como una cadena de texto
     */
    public static String leerArchivo(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        BufferedReader lector = null;

        try {
            lector = new BufferedReader(new FileReader(rutaArchivo));

            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el lector de archivos: " + e.getMessage());
                }
            }
        }

        return contenido.toString();
    }

    /**
     * Escribe contenido en un archivo.
     * 
     * @param rutaArchivo la ruta del archivo donde se va a escribir
     * @param contenido   el contenido a escribir en el archivo
     */
    public static void escribirArchivo(String rutaArchivo, String contenido) {
        BufferedWriter escritor = null;

        try {
            escritor = new BufferedWriter(new FileWriter(rutaArchivo));
            escritor.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            if (escritor != null) {
                try {
                    escritor.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el escritor de archivos: " + e.getMessage());
                }
            }
        }
    }
}