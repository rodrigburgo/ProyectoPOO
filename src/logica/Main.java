package logica;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private Map<String, String> dataTypeMap;

    public Main() {
        dataTypeMap = new HashMap<>();
        dataTypeMap.put("int", "int");
        dataTypeMap.put("float", "float");
        dataTypeMap.put("double", "float");
        dataTypeMap.put("boolean", "bool");
        dataTypeMap.put("char", "str");
        dataTypeMap.put("String", "str");
    }

    public String convert(String javaCode) {
        StringBuilder pythonCode = new StringBuilder();

        String[] lines = javaCode.split("\\r?\\n");
        for (String line : lines) {
            String convertedLine = convertLine(line);
            pythonCode.append(convertedLine).append("\n");
        }

        return pythonCode.toString();
    }

    private String convertLine(String javaLine) {
        if (javaLine.trim().startsWith("//")) {
            // Comment line, keep it as is
            return javaLine;
        }

        String[] tokens = javaLine.split("\\s+");
        if (tokens.length > 1 && dataTypeMap.containsKey(tokens[0])) {
            // Variable declaration line
            String dataType = dataTypeMap.get(tokens[0]);
            String variableName = tokens[1].replace(";", "");
            return dataType + " " + variableName;
        } else if (tokens.length > 1 && tokens[1].equals("=")) {
            // Assignment line
            String variableName = tokens[0];
            String value = tokens[2].replace(";", "");
            return variableName + " = " + value;
        }

        // Other line, keep it as is
        return javaLine;
    }

    public static void main(String[] args) {
        String javaCode = "int number = 42;\n" +
                "String message = \"Hello, world!\";\n" +
                "System.out.println(message);";

        Main converter = new Main();
        String pythonCode = converter.convert(javaCode);

        System.out.println("Java Code:");
        System.out.println(javaCode);
        System.out.println("\nPython Code:");
        System.out.println(pythonCode);
    }
}