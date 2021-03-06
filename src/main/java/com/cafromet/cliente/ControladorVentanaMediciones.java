package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.cafromet.modelo.CentroMeteorologico;
import com.cafromet.modelo.Medicion;
import com.cafromet.modelo.Municipio;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanaMediciones implements ActionListener {

	private Datos datos;

	private ArrayList<Medicion> mediciones;
	private ArrayList<Municipio> municipios;
	private ArrayList<CentroMeteorologico> centroMeteorologicos;

	VentanaMediciones ventanaMediciones = new VentanaMediciones();
	
	public ControladorVentanaMediciones() {
	
	}

	
	public ControladorVentanaMediciones(VentanaMediciones ventanaMediciones) {

		this.ventanaMediciones = ventanaMediciones;
		enviarPeticion("municipio", Peticiones.p103c);
		llenarComboBoxMunicipios(municipios);
		iniciarControlador();
	}

	private void iniciarControlador() {
		
		ventanaMediciones.getcomboBoxMunicipio().addActionListener(this);
		ventanaMediciones.getcomboBoxMunicipio().setActionCommand("municipio");
		ventanaMediciones.getComboBoxCentros().setEnabled(false);
		ventanaMediciones.getBtnBuscar().addActionListener(this);
		ventanaMediciones.getBtnBuscar().setActionCommand("buscar");
		ventanaMediciones.getBtnBuscar().setEnabled(false);
		ventanaMediciones.getBtnVolver().addActionListener(this);
		ventanaMediciones.getBtnVolver().setActionCommand("volver");
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		switch (e.getActionCommand()) {
		
		case "municipio":
			
			Municipio municipio = (Municipio) ventanaMediciones.getcomboBoxMunicipio().getSelectedItem();		
			ventanaMediciones.getBtnBuscar().setEnabled(true);
			mLimpiarTabla();
			enviarPeticion(String.valueOf(municipio.getIdMunicipio()), Peticiones.p105c);
			llenarComboBoxCentros(centroMeteorologicos);
			ventanaMediciones.getComboBoxCentros().setEnabled(true);
			
			break;

		case "buscar":
			
			CentroMeteorologico centro = (CentroMeteorologico) ventanaMediciones.getComboBoxCentros().getSelectedItem();
			enviarPeticion(String.valueOf(centro.getIdCentroMet()), Peticiones.p106a);
			llenarTabla(mediciones);
			
			break;
			
		case "volver":
			VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
			ventanaMenuPrincipal.InicioMenuPrincipal();
			ventanaMediciones.dispose();
		break;
			
		}	
	}

	public boolean llenarComboBoxMunicipios(ArrayList<Municipio> municipios) {

		for (Municipio muni : municipios) {
			muni.setNombre(muni.getNombre().toUpperCase());
			ventanaMediciones.getcomboBoxMunicipio().addItem(muni);
		}
		return true;
	}

	public boolean llenarComboBoxCentros(ArrayList<CentroMeteorologico> centros) {

		ventanaMediciones.getComboBoxCentros().removeAllItems();
	
		for (CentroMeteorologico cent : centros) {
			
			ventanaMediciones.getComboBoxCentros().addItem(cent);
		}
		return true;
	}


	public boolean llenarTabla(ArrayList<Medicion> mediciones) {

		mLimpiarTabla();
		String matrizInfo[][] = new String[mediciones.size()][10];

		for (int i = 0; i < mediciones.size(); i++) {
		
			matrizInfo[i][0] = String.valueOf(mediciones.get(i).getId().getFecha());
			matrizInfo[i][1] = String.valueOf(mediciones.get(i).getId().getHora());
			if(mediciones.get(i).getDirViento() == null) {
				matrizInfo[i][2] = "SIN DATOS";
			}else {
				matrizInfo[i][2] = String.valueOf(mediciones.get(i).getDirViento() + "??");
			}
			if(mediciones.get(i).getHRelativa() == null) {
				matrizInfo[i][3] = "SIN DATOS";
			}else {
				matrizInfo[i][3] = String.valueOf(mediciones.get(i).getHRelativa() + "%");
			}
			if(mediciones.get(i).getPAtmosferica() == null) {
				matrizInfo[i][4] = "SIN DATOS";
			}else {
				matrizInfo[i][4] = String.valueOf(mediciones.get(i).getPAtmosferica() + " mbar");
			}
			if(mediciones.get(i).getPrecip() == null ) {
				matrizInfo[i][5] = "SIN DATOS";
			}else {
				matrizInfo[i][5] = String.valueOf(mediciones.get(i).getPrecip() + " l/m2");
			}
			if(mediciones.get(i).getRadSolar() == null) {
				matrizInfo[i][6] = "SIN DATOS";
			}else {
				matrizInfo[i][6] = String.valueOf(mediciones.get(i).getRadSolar() + " W/m2");
			}
			if(mediciones.get(i).getTempAmbiente() == null) {
				matrizInfo[i][7] = "SIN DATOS";
			}else {
				matrizInfo[i][7] = String.valueOf(mediciones.get(i).getTempAmbiente() + " C??");
			}
			if(mediciones.get(i).getVViento() == null) {
				matrizInfo[i][8] = "SIN DATOS";
			}else {
				matrizInfo[i][8] = String.valueOf(mediciones.get(i).getVViento() + " m/s");
			}
			if(mediciones.get(i).getIca() == null) {
				matrizInfo[i][9] = "SIN DATOS";
			}else {
				matrizInfo[i][9] = String.valueOf(mediciones.get(i).getIca());
			}
			ventanaMediciones.getDefaultTableModel().addRow(matrizInfo[i]);
		}

		return true;
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

	public void mLimpiarTabla() {

		if (ventanaMediciones.getDefaultTableModel().getRowCount() > 0) {
			ventanaMediciones.getDefaultTableModel().setRowCount(0);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {

		case 3:

			municipios = (ArrayList<Municipio>) datos.getObjeto();
			break;
		case 5:

			centroMeteorologicos = (ArrayList<CentroMeteorologico>) datos.getObjeto();
			break;

		case 6:

			mediciones = (ArrayList<Medicion>) datos.getObjeto();
			break;
		}
		return true;
	}

}
