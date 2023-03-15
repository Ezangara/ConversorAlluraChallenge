package Ezangara.ConversorUnidades;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import conexionAPI.Conexion;
import controlador.Controlador;
import longitudes.Longitud;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;

public class conversorUnidades {

	private JFrame frame;
	private JTextField textFieldMagnitud;
	private JComboBox<String> comboBoxEligeConversion;
	private JComboBox<String> comboBoxEligeMonedaOrigen;
	private JComboBox<String> comboBoxEligeMonedaDestino;
	private DefaultComboBoxModel<String> modelUnidades;
	private DefaultComboBoxModel<String> modelUnidadesDestino;
	private JLabel lblResultadoMostrar = new JLabel("");
	private double resultado;
	public static final String[] unidadesLongitud = new String[] { "m", "km", "ft", "in", "nm", "mi" };
	public static final String[] unidadesVolumen = new String[] { "m3", "lt", "ft3", "ImpGal", "USGal" };
	public static final String[] unidadesTemperatura = new String[] { "Kelvin", "Celsius", "Farenheit" };
	public static final String[] unidadesPeso = new String[] { "kg", "ton", "oz", "lb" };
	public static final String[]  unidadesDivisas=new String[] {"ARS", "AUD","BOB", "BRL", "CAD", "CLP", 
			"CNY", "EUR", "GBP", "MXN", "PYG", "USD", "VES"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conversorUnidades window = new conversorUnidades();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public conversorUnidades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 501);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.modelUnidades = new DefaultComboBoxModel<>();
		this.modelUnidadesDestino = new DefaultComboBoxModel<>();

		JPanel background = new JPanel();
		background.setBackground(new Color(128, 128, 128));
		background.setBounds(0, 0, 686, 462);
		frame.getContentPane().add(background);
		background.setLayout(null);

		JButton btnNewButton = new JButton("CONVERTIR");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override

			public void mouseClicked(MouseEvent e) {
				// Object[] Datos = new Object[]{"Longitudes", "nm", "m", 1.0};
				// Object[] Datos = new Object[]{"Divisas", "USD", "ARS", 1.0};
				// Object[] Datos = new Object[]{"Volumenes", "m3", "USGal", 1.0};
				// Object[] Datos = new Object[] { "Temperaturas", "Celsius", "Farenheit", 25.0
				// };

				String tipo = comboBoxEligeConversion.getSelectedItem().toString();
				String origen = comboBoxEligeMonedaOrigen.getSelectedItem().toString();
				String destino = comboBoxEligeMonedaDestino.getSelectedItem().toString();
				double magnitud = Double.parseDouble(textFieldMagnitud.getText());
				Object[] Datos = new Object[4];
				Datos[0] = tipo;
				Datos[1] = origen;
				Datos[2] = destino;
				Datos[3] = magnitud;
				System.out.println(Datos[0]);
				Controlador controlador = new Controlador();
				double resultado = controlador.Convertir(Datos);
				lblResultadoMostrar.setText(String.format("%.4f", resultado) +" "+Datos[2]);
				System.out.println(resultado);
			}
		});
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		btnNewButton.setBackground(new Color(102, 153, 153));
		btnNewButton.setBounds(468, 376, 163, 23);
		background.add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 128, 64));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(0, 139, 686, -14);
		background.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(81, 57, 549, 64);
		background.add(panel);
		panel.setLayout(null);

		this.comboBoxEligeConversion = new JComboBox();
		comboBoxEligeConversion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxEligeConversion.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxEligeConversion.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		comboBoxEligeConversion.setAlignmentX(Component.RIGHT_ALIGNMENT);
		DefaultComboBoxModel<String> modelUnidades = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> modelUnidadesDestino = new DefaultComboBoxModel<>();
		this.comboBoxEligeConversion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					updateComboBoxModels();
				}

			}
		});
		comboBoxEligeConversion.setFont(new Font("Roboto Slab", Font.BOLD, 18));
		comboBoxEligeConversion.setModel(
				new DefaultComboBoxModel(new String[] { "Divisas", "Temperaturas", "Volumen", "Distancias", "Pesos" }));
		comboBoxEligeConversion.setBounds(205, 11, 334, 52);
		panel.add(comboBoxEligeConversion);

		JLabel lblNewLabel = new JLabel("Que desea \nconvertir?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(100, 50));
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(0, 11, 157, 52);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Conversor ALLURA ONE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(113, 11, 448, 64);
		background.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setLayout(null);
		panel_1.setBounds(67, 144, 309, 98);
		background.add(panel_1);

		JLabel lblConvertir = new JLabel("Cantidad");
		lblConvertir.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvertir.setPreferredSize(new Dimension(100, 50));
		lblConvertir.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblConvertir.setBounds(43, 0, 222, 34);
		panel_1.add(lblConvertir);

		
		textFieldMagnitud = new JTextField();
		textFieldMagnitud.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldMagnitud.setToolTipText("Ingrese la magnitud a convertir");
		textFieldMagnitud.requestFocus();
		textFieldMagnitud.setFont(new Font("Roboto Slab", Font.PLAIN, 18));
		textFieldMagnitud.setBounds(0, 39, 101, 52);
		panel_1.add(textFieldMagnitud);
		textFieldMagnitud.setColumns(10);
		
		textFieldMagnitud.setCaretColor(Color.RED); 

		this.comboBoxEligeMonedaOrigen = new JComboBox();
		this.comboBoxEligeMonedaOrigen.setBounds(121, 39, 188, 52);
		this.comboBoxEligeMonedaOrigen.setModel(modelUnidades);
		String tipo = this.comboBoxEligeConversion.getSelectedItem().toString();

		panel_1.add(this.comboBoxEligeMonedaOrigen);
		this.comboBoxEligeMonedaOrigen.setFont(new Font("Roboto Slab", Font.BOLD, 18));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBackground(new Color(255, 255, 255));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(67, 253, 309, 98);
		background.add(panel_1_1);

		JLabel lblResultado = new JLabel("Convertir a:");
		lblResultado.setPreferredSize(new Dimension(100, 50));
		lblResultado.setFont(new Font("Roboto Slab", Font.PLAIN, 18));
		lblResultado.setBounds(10, 40, 98, 52);
		panel_1_1.add(lblResultado);

		this.comboBoxEligeMonedaDestino = new JComboBox();
		this.comboBoxEligeMonedaDestino.setFont(new Font("Roboto Slab", Font.BOLD, 18));
		this.comboBoxEligeMonedaDestino.setBounds(118, 40, 188, 52);
		this.comboBoxEligeMonedaOrigen.setModel(modelUnidadesDestino);
		panel_1_1.add(this.comboBoxEligeMonedaDestino);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(119, 136, 153));
		panel_3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(429, 144, 231, 207);
		background.add(panel_3);
		panel_3.setLayout(null);

		this.lblResultadoMostrar = new JLabel("");
		lblResultadoMostrar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.lblResultadoMostrar.setBackground(new Color(192, 192, 192));
		this.lblResultadoMostrar.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblResultadoMostrar.setFont(new Font("Roboto Slab", Font.ITALIC, 20));
		this.lblResultadoMostrar.setBounds(10, 104, 211, 79);
		panel_3.add(this.lblResultadoMostrar);

		JLabel lblResultado_1 = new JLabel("Su resultado:");
		lblResultado_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado_1.setPreferredSize(new Dimension(100, 50));
		lblResultado_1.setFont(new Font("Roboto Slab", Font.BOLD | Font.ITALIC, 24));
		lblResultado_1.setBounds(21, 11, 180, 52);
		panel_3.add(lblResultado_1);
	}

	private void updateComboBoxModels() {
		String selectedOption = (String) comboBoxEligeConversion.getSelectedItem();
		if (selectedOption.equals("Temperaturas")) {
			modelUnidades.removeAllElements();
			for (String unidad : unidadesTemperatura) {
				modelUnidades.addElement(unidad);
			}
			modelUnidadesDestino.removeAllElements();
			for (String unidad : unidadesTemperatura) {
				modelUnidadesDestino.addElement(unidad);
			}
		} else if (selectedOption.equals("Pesos")) {
			modelUnidades.removeAllElements();
			for (String unidad : unidadesPeso) {
				modelUnidades.addElement(unidad);
			}
			modelUnidadesDestino.removeAllElements();
			for (String unidad : unidadesPeso) {
				modelUnidadesDestino.addElement(unidad);
			}
		}else if(selectedOption.equals("Divisas")){
			modelUnidades.removeAllElements();
			for (String unidad : unidadesDivisas) {
				modelUnidades.addElement(unidad);
			}
			modelUnidadesDestino.removeAllElements();
			for (String unidad : unidadesDivisas) {
				modelUnidadesDestino.addElement(unidad);
			}
		}else if (selectedOption.equals("Volumen")) {
			modelUnidades.removeAllElements();
			for (String unidad : unidadesVolumen) {
				modelUnidades.addElement(unidad);
			}
			modelUnidadesDestino.removeAllElements();
			for (String unidad : unidadesVolumen) {
				modelUnidadesDestino.addElement(unidad);
			}
		} else {
			// Longitudes
			modelUnidades.removeAllElements();
			for (String unidad : unidadesLongitud) {
				modelUnidades.addElement(unidad);
			}
			modelUnidadesDestino.removeAllElements();
			for (String unidad : unidadesLongitud) {
				modelUnidadesDestino.addElement(unidad);
			}
		}

		// Actualizar modelos de JComboBox origen y destino
		comboBoxEligeMonedaOrigen.setModel(modelUnidades);
		comboBoxEligeMonedaDestino.setModel(modelUnidadesDestino);
	}

}