package com.cafromet.cliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaMenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnMunicipios;
	private JButton btnEspaciosNaturales;
	private JButton btnCentrosMeteorologicos;
	

	public JButton getBtnMunicipios() {
		return btnMunicipios;
	}

	public void setBtnMunicipios(JButton btnMunicipios) {
		this.btnMunicipios = btnMunicipios;
	}

	public JButton getBtnEspaciosNaturales() {
		return btnEspaciosNaturales;
	}

	public void setBtnEspaciosNaturales(JButton btnEspaciosNaturales) {
		this.btnEspaciosNaturales = btnEspaciosNaturales;
	}

	public JButton getBtnCentrosMeteorologicos() {
		return btnCentrosMeteorologicos;
	}

	public void setBtnCentrosMeteorologicos(JButton btnCentrosMeteorologicos) {
		this.btnCentrosMeteorologicos = btnCentrosMeteorologicos;
	}

	public void InicioMenuPrincipal() {
	
		VentanaMenuPrincipal VentMenuPrincipal = new VentanaMenuPrincipal();
		VentMenuPrincipal.setVisible(true);
		
		ControladorVentanaMenuPrincipal controladorMenuPrincipal = new ControladorVentanaMenuPrincipal(VentMenuPrincipal);
	
	}

	public VentanaMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 293, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMunicipios = new JButton("MUNICIPIOS");
		btnMunicipios.setBounds(52, 49, 152, 23);
		contentPane.add(btnMunicipios);
		
		btnEspaciosNaturales = new JButton("ESPACIOS NATURALES");
		btnEspaciosNaturales.setBounds(52, 156, 152, 23);
		contentPane.add(btnEspaciosNaturales);
		
		btnCentrosMeteorologicos = new JButton("MEDICIONES");
		btnCentrosMeteorologicos.setBounds(52, 105, 152, 23);
		contentPane.add(btnCentrosMeteorologicos);
	}
}
