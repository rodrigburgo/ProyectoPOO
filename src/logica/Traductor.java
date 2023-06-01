/**
* Conversor de Java a python
* Interfaces para los traductores
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.5, 2023/05/31
*/

package logica;

/**
 * Interfaz que llevaran los traductores de código.
 */
public interface Traductor {
    public String traducirHaciaPython(String codigoJava);
    /*
     * Traduce el código Java dado a Python.
     */
}

	
