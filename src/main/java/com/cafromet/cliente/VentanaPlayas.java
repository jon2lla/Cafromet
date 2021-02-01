package com.cafromet.cliente;

import java.awt.EventQueue;
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

public class VentanaPlayas extends JFrame {

	private JPanel contentPane;
	JComboBox<EspacioNatural> comboBoxEspacio;
	JComboBox<CentroMeteorologico> comboBoxCentros;
	JComboBox<Municipio> comboBoxMunicipio;
	private DefaultTableModel defaultTableModel;
	private JTable tablaContactos;

	public static void InicioVentanaPlayas() {

		VentanaPlayas ventanaPlayas = new VentanaPlayas();
		ventanaPlayas.setVisible(true);

	}

	public VentanaPlayas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MUNICIPIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(239, 11, 105, 20);
		contentPane.add(lblNewLabel);

		JLabel lblMeteorologico = new JLabel(" CENTRO METEOROLOGICO");
		lblMeteorologico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMeteorologico.setBounds(490, 11, 214, 20);
		contentPane.add(lblMeteorologico);

		JLabel lblEspacioNatural = new JLabel("ESPACIO NATURAL");
		lblEspacioNatural.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEspacioNatural.setBounds(10, 11, 167, 20);
		contentPane.add(lblEspacioNatural);

		comboBoxMunicipio = new JComboBox<Municipio>();
		comboBoxMunicipio.setBounds(500, 37, 147, 22);
		contentPane.add(comboBoxMunicipio);

		comboBoxCentros = new JComboBox<CentroMeteorologico>();
		comboBoxCentros.setBounds(239, 37, 147, 22);
		contentPane.add(comboBoxCentros);

		comboBoxEspacio = new JComboBox<EspacioNatural>();
		comboBoxEspacio.setBounds(10, 37, 147, 22);
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
	}
}
