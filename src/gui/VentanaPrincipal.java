/**
* Conversor de Java a python
* Interfaz Grafica
* @author Rodrigo Alonso Figueroa Burgos / Thomas Gomez
* @version 0.8, 2023/05/31
*/

package gui;

//Importaciones de librerías y clases
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
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

/**
 * Clase principal que representa la ventana principal del programa
 */
public class VentanaPrincipal {

	JFrame frame;
	private JTextArea textArea;

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

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 825, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Este panel se crea para fondo.
		JPanel bg = new JPanel();
		bg.setBounds(0, 0, 809, 461);
		frame.getContentPane().add(bg);
		bg.setLayout(null);

		// Este panel se crea para la imagen de presentacion y un label que indicara una descripcion del programa
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 811, 121);
		panel.setBackground(new Color(255, 105, 180));
		bg.add(panel);
		panel.setLayout(null);

		// Imagen de presentacion.
		ImagenPresentacion = new JLabel("");
		ImagenPresentacion.setBounds(10, 11, 140, 88);
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
		textArea.setBounds(0, 125, 632, 293);
		bg.add(textArea);

		// Botón para convertir el código de Java a Python y luego lo imprime en el textarea
		JButton btnConvertir = new JButton("CONVERTIR");
		btnConvertir.setForeground(new Color(255, 255, 255));
		btnConvertir.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 11));
		btnConvertir.setBorderPainted(false);
		btnConvertir.setBackground(new Color(0, 0, 0));
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TraductorJavaPython traducir = new TraductorJavaPython();
				String codigoJava = textArea.getText();
				String codigoPython = traducir.traducirHaciaPython(codigoJava);
				textArea.setText(codigoPython);
			}
		});
		btnConvertir.setBounds(10, 155, 152, 33);
		panel_1.add(btnConvertir);

		// Botón para guardar el archivo con un JFileChooser para eligir el destino donde se guardara el archivo
		JButton btnGuardar = new JButton("GUARDAR ARCHIVO");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showSaveDialog(frame);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File outputFile = fc.getSelectedFile();
					String codigoPython = textArea.getText();
					ManejadorArchivos.escribirArchivo(outputFile.getAbsolutePath(), codigoPython);
				}
			}
		});
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBackground(new Color(0, 0, 0));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 11));
		btnGuardar.setBounds(10, 251, 152, 33);
		panel_1.add(btnGuardar);

		// Botón para cargar el archivo con un JFileChooser para eligir el destino donde se cargara el archivo
		JButton btnCargar = new JButton("CARGAR ARCHIVO");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(frame);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					String inputFile = fichero.getAbsolutePath();

					ManejadorArchivos.leerArchivo(inputFile);
					textArea.setText(ManejadorArchivos.leerArchivo(inputFile));
				}
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
		panel_2.setBackground(new Color(220, 220, 220));
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

		
	}
}
