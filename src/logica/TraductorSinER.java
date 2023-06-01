/**
* Conversor de Java a python
* Logica usada para poder resolver la traduccion
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.8, 2023/05/31
*/

package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que implementa la interfaz Traductor para traducir código Java a Python.
 */
public class TraductorSinER implements Traductor {
    private static String nombreClass;
    private static String nombreFuncion;
    public static Map<String, String> PalabraJavaHaciaPython;

    static {
        // Mapeo de palabras clave de Java a sus equivalentes en Python
    	
    	 PalabraJavaHaciaPython = new HashMap<>();
         PalabraJavaHaciaPython.put("public", "");
         PalabraJavaHaciaPython.put("String", "");
         PalabraJavaHaciaPython.put("void", "@staticmethod\r\n    def");
         PalabraJavaHaciaPython.put("int[]", "");
         PalabraJavaHaciaPython.put("String[]", "");
         PalabraJavaHaciaPython.put("int[][]", "");
         PalabraJavaHaciaPython.put("new", "");
         PalabraJavaHaciaPython.put("System.out.println", "print");
         PalabraJavaHaciaPython.put("length", "len");
         PalabraJavaHaciaPython.put("toUpperCase", "upper");
         PalabraJavaHaciaPython.put("toLowerCase", "lower");
         PalabraJavaHaciaPython.put(";", "");
       
    }

    /**
     * Traduce el código Java dado a Python.
     *
     * @param codigoJava el código Java a traducir
     * @return el código traducido a Python
     */
    @Override
    public String traducirHaciaPython(String codigoJava) {
        StringBuilder codigoPython = new StringBuilder();

        String[] lineas = codigoJava.split("\\n");

        for (String linea : lineas) {
            linea = linea.trim();

            if (linea.startsWith("import") || linea.startsWith("//")) {
                // Ignorar las declaraciones de importación en Python
                continue;
            }

            for (Map.Entry<String, String> entry : PalabraJavaHaciaPython.entrySet()) {
                String patron = entry.getKey();
                String reemplazo = entry.getValue();



                linea = linea.replace(patron, reemplazo);
            }

          
        
            if (linea.contains("def") && nombreFuncion == null) {
                Pattern pattern = Pattern.compile("\\bdef\\s+(\\w+)\\b");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    nombreFuncion = matcher.group(1);
                }
            }

            if (linea.contains("class") && nombreClass == null) {
                Pattern pattern = Pattern.compile("\\bclass\\s+(\\w+)\\b");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    nombreClass = matcher.group(1);
                }
            }     

            // Reemplazar int
            linea = linea.replaceAll("\\bint\\b", "");
            codigoPython.append(linea).append("\n");
        }

        codigoPython.append("\nif __name__==").append("__" + nombreFuncion + "__:\n");
        codigoPython.append(nombreClass).append("." + nombreFuncion + "([])");

        return codigoPython.toString();
    }
}