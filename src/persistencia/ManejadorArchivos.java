/**
* Conversor de Java a python
* Manejador de archivos para la interfaz grafica
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.7, 2023/05/31
*/

package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import logica.TraductorJavaPython;

/**
 * Clase que proporciona métodos para manejar archivos de entrada y salida, así
 * como realizar la traducción de archivos.
 */
public class ManejadorArchivos {

	/**
	 * Lee el contenido de un archivo.
	 *
	 * @param inputFile la ruta del archivo de entrada a leer.
	 * @return el contenido del archivo como una cadena de caracteres.
	 */
	public static String leerArchivo(String inputFile) {
		StringBuilder contenido = new StringBuilder();

		try (BufferedReader leer = new BufferedReader(new FileReader(inputFile))) {
			String linea;
			while ((linea = leer.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contenido.toString();
	}

	/**
	 * Escribe el contenido en un archivo.
	 *
	 * @param outputFile la ruta del archivo de salida donde se va a escribir.
	 * @param contenido  el contenido a escribir en el archivo.
	 */
	public static void escribirArchivo(String outputFile, String contenido) {
		try (BufferedWriter escribir = new BufferedWriter(new FileWriter(outputFile))) {
			escribir.write(contenido);
			System.out.println("Escritura completada. El contenido se ha guardado en " + outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza la traducción de un archivo de Java a Python.
	 *
	 * @param inputFile  la ruta del archivo de entrada en formato Java.
	 * @param outputFile la ruta del archivo de salida donde se guardará la
	 *                   traducción en formato Python.
	 */
	public static void traductorArchivo(String inputFile, String outputFile) {
		TraductorJavaPython traducir = new TraductorJavaPython();
		String codigoJava = leerArchivo(inputFile);
		String codigoPython = traducir.traducirHaciaPython(codigoJava);
		escribirArchivo(outputFile, codigoPython);
	}
}
