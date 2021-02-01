package com.cafromet.cliente;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import javax.swing.JButton;

public class VentanaPlayas extends JFrame {

	private JPanel contentPane;
	JComboBox<EspacioNatural> comboBoxEspacio;
	JComboBox<CentroMeteorologico> comboBoxCentros;
	JComboBox<Municipio> comboBoxMunicipio;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	private JButton btnBuscar;
	private JButton btnVolver;

	//get-set	
	public JComboBox<EspacioNatural> getComboBoxEspacio() {
		return comboBoxEspacio;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public void setComboBoxEspacio(JComboBox<EspacioNatural> comboBoxEspacio) {
		this.comboBoxEspacio = comboBoxEspacio;
	}

	public JComboBox<CentroMeteorologico> getComboBoxCentros() {
		return comboBoxCentros;
	}

	public void setComboBoxCentros(JComboBox<CentroMeteorologico> comboBoxCentros) {
		this.comboBoxCentros = comboBoxCentros;
	}

	public JComboBox<Municipio> getComboBoxMunicipio() {
		return comboBoxMunicipio;
	}

	public void setComboBoxMunicipio(JComboBox<Municipio> comboBoxMunicipio) {
		this.comboBoxMunicipio = comboBoxMunicipio;
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public void setTablaContactos(JTable tablaContactos) {
		this.tablaContactos = tablaContactos;
	}

	
	public void InicioVentanaPlayas() {

		VentanaPlayas ventanaPlayas = new VentanaPlayas();
		ventanaPlayas.setVisible(true);
		
		ControladorVentanaPlayas controladorVentanaPlayas = new ControladorVentanaPlayas(ventanaPlayas);

	}

	public VentanaPlayas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MUNICIPIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(265, 11, 105, 20);
		contentPane.add(lblNewLabel);

		JLabel lblMeteorologico = new JLabel(" CENTRO METEOROLOGICO");
		lblMeteorologico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMeteorologico.setBounds(468, 11, 214, 20);
		contentPane.add(lblMeteorologico);

		JLabel lblEspacioNatural = new JLabel("ESPACIO NATURAL");
		lblEspacioNatural.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEspacioNatural.setBounds(10, 11, 167, 20);
		contentPane.add(lblEspacioNatural);

		comboBoxMunicipio = new JComboBox<Municipio>();
		comboBoxMunicipio.setBounds(265, 42, 133, 22);
		contentPane.add(comboBoxMunicipio);

		comboBoxCentros = new JComboBox<CentroMeteorologico>();
		comboBoxCentros.setBounds(478, 37, 204, 22);
		contentPane.add(comboBoxCentros);

		comboBoxEspacio = new JComboBox<EspacioNatural>();
		comboBoxEspacio.setBounds(10, 37, 194, 22);
		contentPane.add(comboBoxEspacio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 850, 354);
		contentPane.add(scrollPane);

		String columnas[] = { "FECHA", "HORA", "DIR_VIENTO", "H_RELATIVA", "P_ATMOSFERICA", "PRECIP", "RAD_SOLAR",
				"TEMP_AMBIENTE", "V_VIENTO", "ESTACION" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
		tablaContactos.getColumnModel().getColumn(9).setMinWidth(100);

		tablaContactos.setDefaultEditor(Object.class, null);

		scrollPane.setViewportView(tablaContactos);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(722, 37, 89, 23);
		contentPane.add(btnBuscar);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(10, 460, 89, 23);
		contentPane.add(btnVolver);
	}
}
