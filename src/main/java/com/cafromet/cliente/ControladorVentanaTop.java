package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelodao.MunicipioDAO;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

import antlr.collections.List;

public class ControladorVentanaTop implements ActionListener {

	VentanaTop ventanaTop = new VentanaTop();
	LinkedHashMap<Municipio, Medicion> mapaMediciones;
	ArrayList<Medicion> mediciones;
	Datos datos;
	
	public ControladorVentanaTop() {
		
	}
	
	public ControladorVentanaTop(VentanaTop ventanaTop) {
		
		this.ventanaTop = ventanaTop;
		
		iniciarControlador();
		enviarPeticion("top", Peticiones.p120);
		llenarTabla(mapaMediciones);
		ventanaTop.getCmbBxProvincias().setVisible(false);
		ventanaTop.getBtnProvincias().setText("PROVINCIAS");
	}
	
	private void iniciarControlador() {
		
		ventanaTop.getBtnProvincias().addActionListener(this);
		ventanaTop.getBtnProvincias().setActionCommand("Provincias");
		ventanaTop.getBtnVolver().addActionListener(this);
		ventanaTop.getBtnVolver().setActionCommand("volver");
		ventanaTop.getCmbBxProvincias().addActionListener(this);
		ventanaTop.getCmbBxProvincias().setActionCommand("comboBox");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Provincias":
			if(ventanaTop.getCmbBxProvincias().isVisible()) {
				ventanaTop.getBtnProvincias().setText("PROVINCIAS");
				ventanaTop.getCmbBxProvincias().setVisible(false);
				enviarPeticion("top", Peticiones.p120);
				llenarTabla(mapaMediciones);
			}
			else {
				ventanaTop.getBtnProvincias().setText("MUNICIPIOS");
				mLimpiarTabla();
				ventanaTop.getCmbBxProvincias().setVisible(true);
			}
			break;
		case "comboBox":
			switch (ventanaTop.getCmbBxProvincias().getSelectedIndex()) {
			case 0:
				enviarPeticion(String.valueOf(1), Peticiones.p121);
				break;
			case 1:
				enviarPeticion(String.valueOf(20), Peticiones.p121);
				break;
			case 2:
				enviarPeticion(String.valueOf(48), Peticiones.p121);
				break;
			}
			llenarTabla(mapaMediciones);
			break;
		case "volver":
				VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
				ventanaMenuPrincipal.InicioMenuPrincipal();
				ventanaTop.dispose();
			break;
		}

	}
	
	public boolean llenarTabla(LinkedHashMap<Municipio, Medicion>  mediciones) {

		mLimpiarTabla();
		String matrizInfo[][] = new String[mediciones.size()][2];
		int i = 0;
		for (Map.Entry<Municipio, Medicion> entry : mapaMediciones.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

			matrizInfo[i][0] = entry.getKey().getNombre();
			matrizInfo[i][1] = entry.getValue().getTempAmbiente() + " Cº";

			ventanaTop.getDefaultTableModel().addRow(matrizInfo[i]);
			i++;
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
			mapaMediciones = (LinkedHashMap<Municipio, Medicion>) datos.getObjeto();
			break;

		}
		return true;
	}
}
