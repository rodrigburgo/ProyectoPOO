package gui;

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



public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;

	JLabel ImagenPresentacion = new JLabel("");
	JLabel ImagenFondo = new JLabel("");

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


	public void ImagenFondo() {
		ImageIcon Fondo;
		Fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
		Icon FondoIcono= new ImageIcon(Fondo.getImage().getScaledInstance( ImagenFondo.getWidth(), ImagenFondo.getHeight(), Image.SCALE_SMOOTH));
		ImagenFondo.setIcon(FondoIcono);
	}
	
	public void ImagenPresentacion () {
		ImageIcon Presentacion;
		Presentacion = new ImageIcon(getClass().getResource("/imagenes/traductor.png"));
		Icon PresentacionIcono= new ImageIcon(Presentacion.getImage().getScaledInstance( ImagenPresentacion.getWidth(), ImagenPresentacion.getHeight(), Image.SCALE_SMOOTH));
		ImagenPresentacion.setIcon(PresentacionIcono);
	}
	

	public VentanaPrincipal() {
		initialize();
		ImagenPresentacion ();
		}

	private void initialize() {
	
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 825, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel bg = new JPanel();
		bg.setBounds(0, 0, 809, 461);
		frame.getContentPane().add(bg);
		bg.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 811, 110);
		panel.setBackground(new Color(255, 105, 180));
		bg.add(panel);
		panel.setLayout(null);
		
		
		
		ImagenPresentacion = new JLabel("");
		ImagenPresentacion.setBounds(10, 11, 140, 88);
		panel.add(ImagenPresentacion);
		
		JLabel lblNewLabel = new JLabel("CONVERSOR DE JAVA A PYTHON");
		lblNewLabel.setBounds(241, 27, 417, 56);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(634, 109, 177, 352);
		panel_1.setBackground(new Color(186, 85, 211));
		bg.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnConvertir = new JButton("CONVERTIR");
		btnConvertir.setForeground(new Color(255, 255, 255));
		btnConvertir.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 11));
		btnConvertir.setBorderPainted(false);
		btnConvertir.setBackground(new Color(0, 0, 0));
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String codigoJava = textArea.getText();
	             String codigoPython = TraductorJavaPython.traducirHaciaPython(codigoJava);
	             textArea.setText(codigoPython);
				
			}
		});
		btnConvertir.setBounds(10, 155, 152, 33);
		panel_1.add(btnConvertir);
		
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
		
		JButton btnCargar = new JButton("CARGAR ARCHIVO");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc= new JFileChooser();
				int seleccion = fc.showOpenDialog(frame);
				if (seleccion == JFileChooser.APPROVE_OPTION ) {
					File fichero =fc.getSelectedFile();
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 422, 784, 39);
		panel_2.setBackground(new Color(220, 220, 220));
		bg.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton_2_1_1 = new JButton("FINALIZAR PROGRAMA");
		btnNewButton_2_1_1.setBounds(10, 9, 159, 22);
		panel_2.add(btnNewButton_2_1_1);
		btnNewButton_2_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 11));
		btnNewButton_2_1_1.setBackground(new Color(0, 0, 0));
		btnNewButton_2_1_1.setBorderPainted(false);
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		textArea = new JTextArea();
		textArea.setBounds(0, 109, 636, 313);
		bg.add(textArea);
	}
}
