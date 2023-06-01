package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraductorJavaPython {
	private static String nombreClass;
	private static String nombreFuncion;
    private static final Map<String, String> PalabraJavaHaciaPython;

    static {
        PalabraJavaHaciaPython = new HashMap<>();
        PalabraJavaHaciaPython.put("\\bpublic\\b", "");
        PalabraJavaHaciaPython.put("\\bString\\b", "");
        PalabraJavaHaciaPython.put("\\bvoid\\b", "@staticmethod\r\n    def");
        PalabraJavaHaciaPython.put("\\b(int|String)\\s*\\[\\s*\\]", "");
        PalabraJavaHaciaPython.put("\\b(int|String)\\s*\\[\\s*\\]\\s*\\[\\s*\\]", "");
        PalabraJavaHaciaPython.put("\\b(int|String)\\s*\\[\\s*\\]\\s*(\\w+)", "$2");
        PalabraJavaHaciaPython.put("\\bnew\\b", "");
        PalabraJavaHaciaPython.put("\\bSystem.out.println\\b", "print");
        PalabraJavaHaciaPython.put("\\blength\\b", "len");
        PalabraJavaHaciaPython.put("\\b\\w+\\b", "$0");
        PalabraJavaHaciaPython.put("\\bstatic\\b", "");
        PalabraJavaHaciaPython.put("\\bString\\[\\]\\s+args\\b", "object");
        PalabraJavaHaciaPython.put("\\b\\{(.*?)\\}\\s*;", "[$1]");
        PalabraJavaHaciaPython.put(";", "");
        PalabraJavaHaciaPython.put("\\btoUpperCase\\b", "upper");
        PalabraJavaHaciaPython.put("\\btoLowerCase\\b", "lower");
    }

    public static String traducirHaciaPython(String CodigoJava) {
        StringBuilder codigoPython = new StringBuilder();

        String[] lineas = CodigoJava.split("\\n");      

        for (String linea : lineas) {
        	linea = linea.trim();

            if (linea.startsWith("import") || linea.startsWith("//")) {
                // Ignorar las declaraciones de importación en Python
               // codigoPython.append(linea).append("\n");
                continue;
            }

            for (Map.Entry<String, String> entry : PalabraJavaHaciaPython.entrySet()) {
                String patron = entry.getKey();
                String reemplazo = entry.getValue();

                Pattern regex = Pattern.compile(patron);
                Matcher coincide = regex.matcher(linea);
                linea = coincide.replaceAll(reemplazo);
                 if (linea.contains(codigoPython)) {
                	 
                 }
                	 
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
        codigoPython.append("\nif __name__==").append("__"+nombreFuncion+"__:\n");
        codigoPython.append(nombreClass).append("."+nombreFuncion+"([])");
        return codigoPython.toString();
    }
    
   
    }
