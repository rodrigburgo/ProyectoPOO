/**
* Conversor de Java a python
* Interfaces para los traductores
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez Franco
* @version 0.5, 2023/05/31
*/

package logica;

/**
 * Interfaz que llevaran los traductores de código.
 */
public interface Traductor {
    public String traducirHaciaPython(String codigoJava);
    /**
     * Traduce el código Java dado a Python usando expresiones regulares.
     *
     * @param codigoJava el código Java a traducir
     * @return el código traducido a Python
     */
}

	
