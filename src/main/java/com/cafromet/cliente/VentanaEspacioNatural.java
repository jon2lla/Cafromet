package com.cafromet.cliente;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaEspacioNatural extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxProvincia;
	private JButton btnFiltrar;
	private JButton btnVolver;
	
	
	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxProvincia(JComboBox comboBoxProvincia) {
		this.comboBoxProvincia = comboBoxProvincia;
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}
	
	public void inicioVentanaEspacioNatural() {

		VentanaEspacioNatural ventanaEspacioNatural = new VentanaEspacioNatural();
		ventanaEspacioNatural.setVisible(true);
		
		@SuppressWarnings("unused")
		ControladorVentanaEspacioNatural controladorVentEspacioNatural = new ControladorVentanaEspacioNatural(ventanaEspacioNatural);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaEspacioNatural() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 250, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane;
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 1164, 441);
		getContentPane().add(scrollPane);
		
		String columnas[] = {"NOMBRE", "DESCRIPCION"};

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
		tablaContactos.getColumnModel().getColumn(0).setMaxWidth(200);
		tablaContactos.getColumnModel().getColumn(0).setMinWidth(200);

		
		tablaContactos.setDefaultEditor(Object.class, null); 

		scrollPane.setViewportView(tablaContactos);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Pantanos", "Rios", "Playas"}));
		comboBoxProvincia.setBounds(135, 36, 112, 22);
		contentPane.add(comboBoxProvincia);
		
		JLabel lblNewLabel = new JLabel("CATEGORIA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(25, 35, 100, 20);
		contentPane.add(lblNewLabel);
		
		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setBounds(257, 35, 100, 24);
		contentPane.add(btnFiltrar);
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(10, 526, 100, 24);
		contentPane.add(btnVolver);
		
	}

}
