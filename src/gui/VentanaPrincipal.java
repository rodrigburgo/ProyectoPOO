/**
* Conversor de Java a python
* Interfaz Grafica
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez Franco
* @version 0.9, 2023/06/01
*/

package gui;

//Importaciones de librerías y clases
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import logica.TraductorJavaPython;
import persistencia.ManejadorArchivos;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Clase principal que representa la ventana principal del programa
 */
public class VentanaPrincipal {

	JFrame frmConversorDeLenguajes;
	private JTextArea textArea;
	private JLabel errorLabel;
	int xMouse;
	int yMouse;

	
	// Label para la imagen de presentación
	JLabel ImagenPresentacion = new JLabel("");
	// Label para el fondo de la ventana
	JLabel ImagenFondo = new JLabel("");

	/**
	 * Método para ajustar la imagen de fondo al tamaño del JLabel 
	 */
	public void ImagenFondo() {
		ImageIcon Fondo;
		Fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
		Icon FondoIcono = new ImageIcon(Fondo.getImage().getScaledInstance(ImagenFondo.getWidth(),
				ImagenFondo.getHeight(), Image.SCALE_SMOOTH));
		ImagenFondo.setIcon(FondoIcono);
	}

	/**
	 *  Método para ajustar la imagen de presentación al tamaño del JLabel
	 */
	public void ImagenPresentacion() {
		ImageIcon Presentacion;
		Presentacion = new ImageIcon(getClass().getResource("/imagenes/traductor.png"));
		Icon PresentacionIcono = new ImageIcon(Presentacion.getImage().getScaledInstance(ImagenPresentacion.getWidth(),
				ImagenPresentacion.getHeight(), Image.SCALE_SMOOTH));
		ImagenPresentacion.setIcon(PresentacionIcono);
	}

	 /**
     * Método para mostrar mensajes de error en la interfaz gráfica
     * @param mensaje El mensaje de error a mostrar
     */
    private void mostrarError(String mensaje) {
        errorLabel.setText(mensaje);
        errorLabel.setVisible(true);
    }

    /**
     * Método para limpiar los mensajes de error en la interfaz gráfica
     */
    private void limpiarErrores() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }
	
    /**
     * Método para validar la selección de un archivo antes de cargarlo
     */
    private void cargarArchivo() {
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showOpenDialog(frmConversorDeLenguajes);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            String inputFile = fichero.getAbsolutePath();

            try {
                String contenido = ManejadorArchivos.leerArchivo(inputFile);
                textArea.setText(contenido);
                limpiarErrores();
            } catch (Exception ex) {
                mostrarError("Error al cargar el archivo seleccionado");
            }
        }
    }
    
    /**
     * Método para guardar el archivo con un JFileChooser
     */
    private void guardarArchivo() {
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showSaveDialog(frmConversorDeLenguajes);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File outputFile = fc.getSelectedFile();
            String codigoPython = textArea.getText();

            try {
                ManejadorArchivos.escribirArchivo(outputFile.getAbsolutePath(), codigoPython);
                limpiarErrores();
                JOptionPane.showMessageDialog(frmConversorDeLenguajes, "Archivo guardado exitosamente");
            } catch (Exception ex) {
                mostrarError("Error al guardar el archivo");
            }
        }
    }

    /**
     * Método para convertir el código de Java a Python y mostrarlo en el textarea
     */
    private void convertirCodigo() {
        TraductorJavaPython traductor = new TraductorJavaPython();
        String codigoJava = textArea.getText();

        try {
            String codigoPython = traductor.traducirHaciaPython(codigoJava);
            textArea.setText(codigoPython);
            limpiarErrores();
        } catch (Exception ex) {
            mostrarError("Error al convertir el código de Java a Python");
        }
    }
    
    /**
	 *  Constructor de la clase VentanaPrincipal
	 */
	public VentanaPrincipal() {
		initialize();
		ImagenPresentacion();
	}

	/**
	 *  Método de inicialización de la ventana principal
	 */
	private void initialize() {

		frmConversorDeLenguajes = new JFrame();
		frmConversorDeLenguajes.setUndecorated(true);
		frmConversorDeLenguajes.setTitle("Conversor de lenguajes");
		frmConversorDeLenguajes.setResizable(false);
		frmConversorDeLenguajes.setBounds(100, 100, 808, 461);
		frmConversorDeLenguajes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorDeLenguajes.getContentPane().setLayout(null);

		// Este panel se crea para fondo.
		JPanel bg = new JPanel();
		bg.setBounds(0, 0, 809, 461);
		frmConversorDeLenguajes.getContentPane().add(bg);
		bg.setLayout(null);

		// Este panel se crea para la imagen de presentacion y un label que indicara una descripcion del programa
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse= e.getX();
				yMouse= e.getY();
			}

		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int Y = e.getYOnScreen();
				int X = e.getXOnScreen();
				frmConversorDeLenguajes.setLocation(X - xMouse, Y - yMouse);
			}
		});
		
		panel.setBounds(0, 0, 811, 121);
		panel.setBackground(new Color(255, 105, 180));
		bg.add(panel);
		panel.setLayout(null);

		// Imagen de presentacion.
		ImagenPresentacion = new JLabel("");
		ImagenPresentacion.setBounds(40, 13, 140, 88);
		panel.add(ImagenPresentacion);

		//Este label tiene la descripcion del programa.
		JLabel lblNewLabel = new JLabel("CONVERSOR DE JAVA A PYTHON");
		lblNewLabel.setBounds(241, 27, 417, 56);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
		panel.add(lblNewLabel);

		// Este panel contiene los botones guardar,cargar y convertir
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(634, 119, 177, 342);
		panel_1.setBackground(new Color(186, 85, 211));
		bg.add(panel_1);
		panel_1.setLayout(null);
		
		// Este textarea contendra el string del archivo a cargar o despues de traducir y es lo tambien contrendra el resultado final al guardar el archivo
		textArea = new JTextArea();
		textArea.setBackground(new Color(215, 215, 215));
		textArea.setLineWrap(true);
		textArea.setBounds(1, 121, 634, 301);
		bg.add(textArea);
		
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setBounds(1, 121, 634, 301);
		bg.add(scroll);

		// Botón para convertir código
		JButton btnConvertir = new JButton("CONVERTIR");
		btnConvertir.setForeground(new Color(255, 255, 255));
		btnConvertir.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 11));
		btnConvertir.setBorderPainted(false);
		btnConvertir.setBackground(new Color(0, 0, 0));
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertirCodigo();
			}
		});
		btnConvertir.setBounds(10, 155, 152, 33);
		panel_1.add(btnConvertir);

		// Botón para guardar archivo
		JButton btnGuardar = new JButton("GUARDAR ARCHIVO");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarArchivo();
			}
		});
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBackground(new Color(0, 0, 0));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 11));
		btnGuardar.setBounds(10, 251, 152, 33);
		panel_1.add(btnGuardar);

		// Botón para cargar archivo
		JButton btnCargar = new JButton("CARGAR ARCHIVO");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarArchivo();				
			}
		});
		btnCargar.setBorderPainted(false);
		btnCargar.setBorder(null);
		btnCargar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnCargar.setForeground(Color.WHITE);
		btnCargar.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 11));
		btnCargar.setBackground(Color.BLACK);
		btnCargar.setBounds(10, 59, 152, 33);
		panel_1.add(btnCargar);

		// Este panel se crea para el pie de la interfaz grafica y dentro contiene el boton para finalizar el programa
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 422, 784, 39);
		panel_2.setBackground(new Color(232, 0, 116));
		bg.add(panel_2);
		panel_2.setLayout(null);

		// Botón para finalizar el programa
		JButton btnFinalizar = new JButton("FINALIZAR PROGRAMA");
		btnFinalizar.setBounds(10, 9, 159, 22);
		panel_2.add(btnFinalizar);
		btnFinalizar.setForeground(new Color(255, 255, 255));
		btnFinalizar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 11));
		btnFinalizar.setBackground(new Color(0, 0, 0));
		btnFinalizar.setBorderPainted(false);
	
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Etiqueta para mostrar mensajes de error
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(21, 286, 453, 20);
        frmConversorDeLenguajes.getContentPane().add(errorLabel);
		
	}

}
