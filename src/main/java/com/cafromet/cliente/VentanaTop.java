package com.cafromet.cliente;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class VentanaTop extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	private JButton btnVolver;
	private JComboBox<String> cmbBxProvincias;
	private JButton btnProvincias;

	
	
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

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JComboBox<String> getCmbBxProvincias() {
		return cmbBxProvincias;
	}

	public JButton getBtnProvincias() {
		return btnProvincias;
	}

	public void inicioVentanaTop() {
		VentanaTop ventanaTop = new VentanaTop();
		ventanaTop.setVisible(true);
		ControladorVentanaTop controladorVentanaTop = new ControladorVentanaTop(ventanaTop);
	}

	public VentanaTop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 464, 208);
		contentPane.add(scrollPane);

		String columnas[] = { "NOMBRE", "TEMPERATURA" };

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

		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(10, 285, 89, 23);
		contentPane.add(btnVolver);
		
		cmbBxProvincias = new JComboBox<String>();
		cmbBxProvincias.setModel(new DefaultComboBoxModel<String>(new String[] {"Araba/Álava", "Gipuzkoa", "Bizkaia"}));
		cmbBxProvincias.setBounds(10, 11, 137, 22);
		contentPane.add(cmbBxProvincias);
		
		btnProvincias = new JButton("New button");
		btnProvincias.setBounds(311, 11, 163, 23);
		contentPane.add(btnProvincias);
	}
}
