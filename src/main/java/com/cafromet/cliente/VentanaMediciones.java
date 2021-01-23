package com.cafromet.cliente;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
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
import com.cafromet.modelo.Municipio;
import javax.swing.JButton;

public class VentanaMediciones extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	private JComboBox<Municipio> comboBoxMunicipio;
	private JComboBox<CentroMeteorologico> comboBoxCentros;
	private JButton btnBuscar;
	private JButton btnVolver;
	
	//
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JComboBox<CentroMeteorologico> getComboBoxCentros() {
		return comboBoxCentros;
	}

	public void setComboBoxCentros(JComboBox<CentroMeteorologico> comboBoxCentros) {
		this.comboBoxCentros = comboBoxCentros;
	}

	public JComboBox<Municipio> getcomboBoxMunicipio() {
		return comboBoxMunicipio;
	}

	public void setcomboBoxMunicipio(JComboBox<Municipio> comboBoxMunicipio) {
		this.comboBoxMunicipio = comboBoxMunicipio;
	}
	
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}
	
	
	@SuppressWarnings("unused")
	public void inicioVentanaMediciones() {
		
		VentanaMediciones VentanaMediciones = new VentanaMediciones();
		VentanaMediciones.setVisible(true);
		
		ControladorVentanaMediciones controladorVentanaMediciones = new ControladorVentanaMediciones(VentanaMediciones);
			
	}
	public VentanaMediciones() {
		setTitle("MEDICIONES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JScrollPane scrollPane;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 1166, 608);
		getContentPane().add(scrollPane);
		
		String columnas[] = { "FECHA","HORA","DIR_VIENTO","H_RELATIVA","P_ATMOSFERICA","PRECIP","RAD_SOLAR","TEMP_AMBIENTE","V_VIENTO","ESTACION"};
												
		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
		tablaContactos.getColumnModel().getColumn(9).setMinWidth(100);
	
		tablaContactos.setDefaultEditor(Object.class, null); 

		scrollPane.setViewportView(tablaContactos);
		
		comboBoxMunicipio = new JComboBox<Municipio>();
		comboBoxMunicipio.setBounds(135, 33, 200, 22);
		contentPane.add(comboBoxMunicipio);
		
		JLabel lblNewLabel = new JLabel("MUNICIPIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(25, 35, 100, 20);
		contentPane.add(lblNewLabel);
		
		comboBoxCentros = new JComboBox<CentroMeteorologico>();	
		comboBoxCentros.setBounds(594, 33, 200, 22);
		contentPane.add(comboBoxCentros);
		
		JLabel lblMeteorologico = new JLabel(" CENTRO METEOROLOGICO");
		lblMeteorologico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMeteorologico.setBounds(370, 35, 214, 20);
		contentPane.add(lblMeteorologico);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(837, 33, 89, 23);
		contentPane.add(btnBuscar);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(1067, 33, 89, 23);
		contentPane.add(btnVolver);
	
	}
}
