package com.cafromet.cliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnMunicipios;
	private JButton btnEspaciosNaturales;
	private JButton btnCentrosMeteorologicos;
	private JButton btnVolver;
		
	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

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
		
		@SuppressWarnings("unused")
		ControladorVentanaMenuPrincipal controladorMenuPrincipal = new ControladorVentanaMenuPrincipal(VentMenuPrincipal);
	
	}

	public VentanaMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 404, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMunicipios = new JButton("MUNICIPIOS");
		btnMunicipios.setBounds(10, 39, 169, 46);
		contentPane.add(btnMunicipios);
		
		btnEspaciosNaturales = new JButton("ESPACIOS NATURALES");
		btnEspaciosNaturales.setBounds(10, 121, 169, 46);
		contentPane.add(btnEspaciosNaturales);
		
		btnCentrosMeteorologicos = new JButton("MEDICIONES");
		btnCentrosMeteorologicos.setBounds(209, 39, 169, 46);
		contentPane.add(btnCentrosMeteorologicos);
		
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(10, 255, 89, 33);
		contentPane.add(btnVolver);
		
	}
}
