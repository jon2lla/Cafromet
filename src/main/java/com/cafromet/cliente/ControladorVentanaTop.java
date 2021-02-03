package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.Municipio;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaTop implements ActionListener {

	VentanaTop ventanaTop = new VentanaTop();
	ArrayList<Medicion> mediciones;
	Datos datos;
	
	public ControladorVentanaTop() {
		
	}
	
	public ControladorVentanaTop(VentanaTop ventanaTop) {
		
		this.ventanaTop = ventanaTop;
		
		iniciarControlador();
		enviarPeticion("top", Peticiones.p120);
		llenarTabla(mediciones);
	}
	
	private void iniciarControlador() {

		ventanaTop.getBtnVolver().addActionListener(this);
		ventanaTop.getBtnVolver().setActionCommand("volver");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
			
		case "volver":
				VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
				ventanaMenuPrincipal.InicioMenuPrincipal();
				ventanaTop.dispose();
			break;
		}

	}
	
	public boolean llenarTabla(ArrayList<Medicion> mediciones) {

		mLimpiarTabla();
		String matrizInfo[][] = new String[mediciones.size()][2];

		for (int i = 0; i < mediciones.size(); i++) {
		
			matrizInfo[i][0] = "SIN DATOS";
			if(mediciones.get(i).getPrecip() == null ) {
				matrizInfo[i][1] = "SIN DATOS";
			}else {
				matrizInfo[i][1] = String.valueOf(mediciones.get(i).getPrecip() + " l/m2");
			}

			ventanaTop.getDefaultTableModel().addRow(matrizInfo[i]);
		}

		return true;
	}
	
	public void mLimpiarTabla() {

		if (ventanaTop.getDefaultTableModel().getRowCount() > 0) {
			ventanaTop.getDefaultTableModel().setRowCount(0);
		}
	}
	public boolean enviarPeticion(String contenido, Peticiones peticion) {
		try {
			datos = new Datos();
			datos.setContenido(contenido);
			datos.setPeticion(peticion);
			IOListenerClt IOListenerClt = new IOListenerClt(datos);
			Thread hiloSender = new Thread(IOListenerClt);
			hiloSender.start();
			hiloSender.join();
			datos = IOListenerClt.getDatos();
			procesarRecepcion();
		} catch (Exception e) {
			e.getMessage();
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 20:

			mediciones = (ArrayList<Medicion>) datos.getObjeto();
			break;

		}
		return true;
	}
}
