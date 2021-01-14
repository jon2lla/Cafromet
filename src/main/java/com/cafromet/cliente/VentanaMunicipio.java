package com.cafromet.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaMunicipio extends JFrame {

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
	public void inicioVentanaMunicipio() {
		
		VentanaMunicipio VentanaMunicipio = new VentanaMunicipio();
		VentanaMunicipio.setVisible(true);
		
		try {
			ControladorVentanMunicipio controladorVentanMunicipio = new ControladorVentanMunicipio(VentanaMunicipio);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public VentanaMunicipio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 592, 187);
		getContentPane().add(scrollPane);
		
		String columnas[] = { "NOMBRE", "PROVINCIA" };

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
