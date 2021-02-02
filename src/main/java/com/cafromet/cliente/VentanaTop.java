package com.cafromet.cliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaTop extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	private JButton btnVolver;

	
	
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

	public static void inicioVentanaTop() {

		VentanaTop ventanaTop = new VentanaTop();
		ventanaTop.setVisible(true);
		ControladorVentanaTop controladorVentanaTop = new ControladorVentanaTop(ventanaTop);
	}

	public VentanaTop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 602, 240);
		contentPane.add(scrollPane);

		String columnas[] = { "NOMBRE", "RAD_SOLAR" };

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
	}
}
