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

public class ControladorVentanaPlayas implements ActionListener {

	VentanaPlayas ventanaPlayas = new VentanaPlayas();

	private Datos datos;
	private ArrayList<Medicion> mediciones;
	private ArrayList<Municipio> municipios;
	private ArrayList<CentroMeteorologico> centroMeteorologicos;
	private ArrayList<Medicion> medicionFiltrado;
	private ArrayList<CentroMeteorologico> centrosFiltrados;
	private ArrayList<EspacioNatural> espacioNatural;

	public ControladorVentanaPlayas() {

	}

	public ControladorVentanaPlayas(VentanaPlayas ventanaPlayas) {
		this.ventanaPlayas = ventanaPlayas;
		enviarPeticion("espacio", Peticiones.p104a);
		llenarComboBoxEspacios(espacioNatural);
		iniciarControlador();
	}

	private void iniciarControlador() {

		ventanaPlayas.getComboBoxEspacio().addActionListener(this);
		ventanaPlayas.getComboBoxEspacio().setActionCommand("espacio");
		
		ventanaPlayas.getComboBoxMunicipio().setEnabled(false);
		
		ventanaPlayas.getComboBoxCentros().setEnabled(false);
		
		ventanaPlayas.getBtnBuscar().addActionListener(this);
		ventanaPlayas.getBtnBuscar().setActionCommand("buscar");
		ventanaPlayas.getBtnBuscar().setEnabled(false);
		
		ventanaPlayas.getBtnVolver().addActionListener(this);
		ventanaPlayas.getBtnVolver().setActionCommand("volver");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "espacio":

			EspacioNatural espacio = (EspacioNatural) ventanaPlayas.getComboBoxEspacio().getSelectedItem();
			ventanaPlayas.getComboBoxMunicipio().setEnabled(true);
			enviarPeticion(String.valueOf(espacio.getIdEspacio()), Peticiones.p117);
			llenarComboBoxMunicipios(municipios);
			ventanaPlayas.getComboBoxMunicipio().addActionListener(this);
			ventanaPlayas.getComboBoxMunicipio().setActionCommand("municipio");
			
			
			Municipio municipio = (Municipio) ventanaPlayas.getComboBoxMunicipio().getSelectedItem();			
			enviarPeticion(String.valueOf(municipio.getIdMunicipio()), Peticiones.p105c);
			llenarComboBoxCentros(filtroCentros(municipio.getIdMunicipio()));
			ventanaPlayas.getComboBoxCentros().setEnabled(true);
		
		break;
		
		case "buscar":

			CentroMeteorologico centro = (CentroMeteorologico) ventanaPlayas.getComboBoxCentros().getSelectedItem();
			mLimpiarTabla();
			ventanaPlayas.getBtnBuscar().setEnabled(true);
			enviarPeticion(String.valueOf(centro.getIdCentroMet()), Peticiones.p106a);
			llenarTabla(filtroMediciones(centro));
			
			break;
			
		case "volver":
			VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
			ventanaMenuPrincipal.InicioMenuPrincipal();
			ventanaPlayas.dispose();
		break;
		
		}

	}

	public boolean llenarComboBoxCentros(ArrayList<CentroMeteorologico> centros) {

		ventanaPlayas.getComboBoxCentros().removeAllItems();

		for (CentroMeteorologico cent : centros) {

			ventanaPlayas.getComboBoxCentros().addItem(cent);
		}
		return true;
	}

	public ArrayList<CentroMeteorologico> filtroCentros(int idMunicipio) {

		centrosFiltrados = new ArrayList<CentroMeteorologico>();

		for (CentroMeteorologico cent : centroMeteorologicos) {

			if (cent.getMunicipio().getIdMunicipio() == idMunicipio) {
				centrosFiltrados.add(cent);
			}
		}
		return centrosFiltrados;
	}

	public void mLimpiarTabla() {

		if (ventanaPlayas.getDefaultTableModel().getRowCount() > 0) {
			ventanaPlayas.getDefaultTableModel().setRowCount(0);
		}
	}

	private boolean llenarComboBoxEspacios(ArrayList<EspacioNatural> espacioNatural) {

		for (EspacioNatural espacio : espacioNatural) {
			espacio.setNombre(espacio.getNombre().toUpperCase());
			ventanaPlayas.getComboBoxEspacio().addItem(espacio);
		}
		return true;
	}

	public boolean llenarComboBoxMunicipios(ArrayList<Municipio> municipios) {
		
		ventanaPlayas.getComboBoxMunicipio().removeAllItems();
		for (Municipio muni : municipios) {
			muni.setNombre(muni.getNombre().toUpperCase());
			ventanaPlayas.getComboBoxMunicipio().addItem(muni);
		}
		return true;
	}

	public ArrayList<Medicion> filtroMediciones(CentroMeteorologico centro) {

		medicionFiltrado = new ArrayList<Medicion>();

		for (Medicion med : mediciones) {
			if (med.getId().getIdCentroMet() == centro.getIdCentroMet()) {
				medicionFiltrado.add(med);
			}
		}
		return medicionFiltrado;
	}

	public boolean llenarTabla(ArrayList<Medicion> mediciones) {

		mLimpiarTabla();
		String matrizInfo[][] = new String[mediciones.size()][10];

		for (int i = 0; i < mediciones.size(); i++) {

			matrizInfo[i][0] = String.valueOf(mediciones.get(i).getId().getFecha());
			matrizInfo[i][1] = String.valueOf(mediciones.get(i).getId().getHora());
			if (mediciones.get(i).getDirViento() == null) {
				matrizInfo[i][2] = "SIN DATOS";
			} else {
				matrizInfo[i][2] = String.valueOf(mediciones.get(i).getDirViento() + "º");
			}
			if (mediciones.get(i).getHRelativa() == null) {
				matrizInfo[i][3] = "SIN DATOS";
			} else {
				matrizInfo[i][3] = String.valueOf(mediciones.get(i).getHRelativa() + "%");
			}
			if (mediciones.get(i).getPAtmosferica() == null) {
				matrizInfo[i][4] = "SIN DATOS";
			} else {
				matrizInfo[i][4] = String.valueOf(mediciones.get(i).getPAtmosferica() + " mbar");
			}
			if (mediciones.get(i).getPrecip() == null) {
				matrizInfo[i][5] = "SIN DATOS";
			} else {
				matrizInfo[i][5] = String.valueOf(mediciones.get(i).getPrecip() + " l/m2");
			}
			if (mediciones.get(i).getRadSolar() == null) {
				matrizInfo[i][6] = "SIN DATOS";
			} else {
				matrizInfo[i][6] = String.valueOf(mediciones.get(i).getRadSolar() + " W/m2");
			}
			if (mediciones.get(i).getTempAmbiente() == null) {
				matrizInfo[i][7] = "SIN DATOS";
			} else {
				matrizInfo[i][7] = String.valueOf(mediciones.get(i).getTempAmbiente() + " Cº");
			}
			if (mediciones.get(i).getVViento() == null) {
				matrizInfo[i][8] = "SIN DATOS";
			} else {
				matrizInfo[i][8] = String.valueOf(mediciones.get(i).getVViento() + " m/s");
			}
			if (mediciones.get(i).getIca() == null) {
				matrizInfo[i][9] = "SIN DATOS";
			} else {
				matrizInfo[i][9] = String.valueOf(mediciones.get(i).getIca());
			}
			ventanaPlayas.getDefaultTableModel().addRow(matrizInfo[i]);
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

	@SuppressWarnings("unchecked")
	public boolean procesarRecepcion() {

		switch (datos.getPeticion().getCodigo()) {
				
		case 4:

			espacioNatural = (ArrayList<EspacioNatural>) datos.getObjeto();
			break;

		case 5:

			centroMeteorologicos = (ArrayList<CentroMeteorologico>) datos.getObjeto();
			break;

		case 6:

			mediciones = (ArrayList<Medicion>) datos.getObjeto();
			break;

		case 17:

			municipios = (ArrayList<Municipio>) datos.getObjeto();
			break;
		}
		return true;
	}

}
