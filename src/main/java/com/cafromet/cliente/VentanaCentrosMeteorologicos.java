package com.cafromet.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaCentrosMeteorologicos extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}
	
	@SuppressWarnings("unused")
	public void inicioVentanaCentrosMeteorologicos() {
		
		VentanaCentrosMeteorologicos VentanaCentrosMeteorologicos = new VentanaCentrosMeteorologicos();
		VentanaCentrosMeteorologicos.setVisible(true);
		
		ControladorVentanaCentrosMeteorologicos controladorVentanaCentrosMeteorologicos = new ControladorVentanaCentrosMeteorologicos(VentanaCentrosMeteorologicos);
			
	}
	public VentanaCentrosMeteorologicos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 568, 188);
		getContentPane().add(scrollPane);
		
		String columnas[] = { "NOMBRE","DIRECCION"};

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
	
		tablaContactos.setDefaultEditor(Object.class, null); 

		scrollPane.setViewportView(tablaContactos);
	}

}
