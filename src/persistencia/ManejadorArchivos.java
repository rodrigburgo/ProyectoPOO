/**
* Conversor de Java a python
* Manejador de archivos para la interfaz grafica
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.8, 2023/05/31
*/

package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import logica.TraductorJavaPython;

public class ManejadorArchivos {

	// usar este metodo cuando se vaya a cargar un archivo
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

	// usar este metodo cuando se vaya a guardar un archivo
	public static void escribirArchivo(String outputFile, String contenido) {
		try (BufferedWriter escribir = new BufferedWriter(new FileWriter(outputFile))) {
			escribir.write(contenido);
			System.out.println("Escritura completada. El contenido se ha guardado en " + outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// usar este metodo cuando se vaya a traducir un archivo
	public static void traductorArchivo(String inputFile, String outputFile) {
		TraductorJavaPython traducir = new TraductorJavaPython();
		String codigoJava = leerArchivo(inputFile);
		String codigoPython = traducir.traducirHaciaPython(codigoJava);
		escribirArchivo(outputFile, codigoPython);
	}
}



