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
		ventanaMenuPrincipal.getBtnCentrosMeteorologicos().setActionCommand("centrosMeteorologicos");
		ventanaMenuPrincipal.getBtnEspaciosNaturales().addActionListener(this);
		ventanaMenuPrincipal.getBtnEspaciosNaturales().setActionCommand("espaciosNaturales");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "municipios":
			VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();
			ventanaMunicipio.inicioVentanaMunicipio();
			break;
		case "centrosMeteorologicos":

			break;
		case "espaciosNaturales":

			break;

		}

	}

}
