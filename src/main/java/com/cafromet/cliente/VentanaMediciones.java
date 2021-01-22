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
	
	//
	public JButton getBtnBuscar() {
		return btnBuscar;
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
	public void inicioVentanaCentrosMeteorologicos() {
		
		VentanaMediciones VentanaMediciones = new VentanaMediciones();
		VentanaMediciones.setVisible(true);
		
		ControladorVentanaMediciones controladorVentanaMediciones = new ControladorVentanaMediciones(VentanaMediciones);
			
	}
	public VentanaMediciones() {
		setTitle("MEDICIONES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 634, 188);
		getContentPane().add(scrollPane);
		
		String columnas[] = { "FECHA","HORA","COMGM3","NOGM3","NO2","NO2ICA","SO2","SO2ICA","PM25","ESTACION"};
												
		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
	
		tablaContactos.setDefaultEditor(Object.class, null); 

		scrollPane.setViewportView(tablaContactos);
		
		comboBoxMunicipio = new JComboBox();
		comboBoxMunicipio.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un municipio"}));
		comboBoxMunicipio.setBounds(135, 33, 112, 22);
		contentPane.add(comboBoxMunicipio);
		
		JLabel lblNewLabel = new JLabel("MUNICIPIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(25, 35, 100, 20);
		contentPane.add(lblNewLabel);
		
		comboBoxCentros = new JComboBox();	
		comboBoxCentros.setBounds(417, 31, 112, 22);
		contentPane.add(comboBoxCentros);
		
		JLabel lblCentro = new JLabel("CENTRO");
		lblCentro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCentro.setBounds(293, 19, 75, 20);
		contentPane.add(lblCentro);
		
		JLabel lblMeteorologico = new JLabel("METEOROLOGICO");
		lblMeteorologico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMeteorologico.setBounds(271, 35, 136, 20);
		contentPane.add(lblMeteorologico);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(508, 71, 89, 23);
		contentPane.add(btnBuscar);
	
	}
}
