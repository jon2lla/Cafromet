package com.cafromet.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VentanaMunicipio extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;
	private JComboBox comboBoxProvincia;
	private JButton btnFiltrar;
	
	
	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JComboBox getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public void setComboBoxProvincia(JComboBox comboBoxProvincia) {
		this.comboBoxProvincia = comboBoxProvincia;
	}

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
		
		ControladorVentanMunicipio controladorVentanMunicipio = new ControladorVentanMunicipio(VentanaMunicipio);
				
	}

	public VentanaMunicipio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane;
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 136, 592, 187);
		getContentPane().add(scrollPane);
		
		String columnas[] = { "NOMBRE", "DESCRIPCION","PROVINCIA"};

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);
	
		tablaContactos.setDefaultEditor(Object.class, null); 

		scrollPane.setViewportView(tablaContactos);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setModel(new DefaultComboBoxModel(new String[] {"Araba/Álava", "Gipuzkoa", "Bizkaia"}));
		comboBoxProvincia.setBounds(135, 33, 112, 22);
		contentPane.add(comboBoxProvincia);
		
		JLabel lblNewLabel = new JLabel("PROVINCIA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(25, 35, 100, 20);
		contentPane.add(lblNewLabel);
		
		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setBounds(281, 33, 89, 23);
		contentPane.add(btnFiltrar);
		
	}
}
