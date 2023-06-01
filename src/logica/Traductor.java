/**
* Conversor de Java a python
* Interfaces para los traductores
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez Franco
* @version 0.6, 2023/06/01
*/

package logica;

/**
 * Interfaz que llevaran los traductores de código.
 */
public interface Traductor {
    /**
     * Traduce el código Java dado a Python usando expresiones regulares.
     *
     * @param codigoJava el código Java a traducir
     * @return el código traducido a Python
     */
    public String traducirHaciaPython(String codigoJava);
 
}

	
