package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanaMenuPrincipal implements ActionListener {

	VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();

	public ControladorVentanaMenuPrincipal(VentanaMenuPrincipal pVentanaMenuPrincipal) {

		ventanaMenuPrincipal = pVentanaMenuPrincipal;

		inicializarControlador();
	}

	public void inicializarControlador() {

		ventanaMenuPrincipal.getBtnMunicipios().addActionListener(this);
		ventanaMenuPrincipal.getBtnMunicipios().setActionCommand("municipios");
		ventanaMenuPrincipal.getBtnCentrosMeteorologicos().addActionListener(this);
		ventanaMenuPrincipal.getBtnCentrosMeteorologicos().setActionCommand("Mediciones");
		ventanaMenuPrincipal.getBtnEspaciosNaturales().addActionListener(this);
		ventanaMenuPrincipal.getBtnEspaciosNaturales().setActionCommand("espaciosNaturales");
		ventanaMenuPrincipal.getBtnTop().addActionListener(this);
		ventanaMenuPrincipal.getBtnTop().setActionCommand("top");
		
		ventanaMenuPrincipal.getBtnVolver().addActionListener(this);
		ventanaMenuPrincipal.getBtnVolver().setActionCommand("volver");
		ventanaMenuPrincipal.getBtnPlayas().addActionListener(this);
		ventanaMenuPrincipal.getBtnPlayas().setActionCommand("playas");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "municipios":
			VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();
			ventanaMunicipio.inicioVentanaMunicipio();
			ventanaMenuPrincipal.dispose();
			break;
		case "Mediciones":
			VentanaMediciones entanaMediciones = new VentanaMediciones();
			entanaMediciones.inicioVentanaMediciones();
			ventanaMenuPrincipal.dispose();
			break;
		case "espaciosNaturales":
			VentanaEspacioNatural ventanaEspacioNatural = new VentanaEspacioNatural();
			ventanaEspacioNatural.inicioVentanaEspacioNatural();
			ventanaMenuPrincipal.dispose();
			break;
		case "playas":
			VentanaPlayas ventanaPlayas = new VentanaPlayas();
			ventanaPlayas.InicioVentanaPlayas();
			ventanaMenuPrincipal.dispose();
			break;
		case "volver":
			VentanaCliente ventanaCliente = new VentanaCliente();
			ventanaCliente.inicioVentaCliente();
			ventanaMenuPrincipal.dispose();
		break;
		case "top":
			VentanaTop ventanaTop = new VentanaTop();
			ventanaTop.inicioVentanaTop();
			ventanaMenuPrincipal.dispose();
		break;
		}

	}

}
