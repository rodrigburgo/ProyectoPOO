/**
* Conversor de Java a python
* Run para poder inciar la aplicacion.
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.8, 2023/05/31
*/

package gui;

import java.awt.EventQueue;

/**
 * Clase principal que contiene el método main para iniciar la aplicación
 */
public class Main {

    /**
     * Método principal que inicia la aplicación
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal window = new VentanaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}