package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

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
	private ArrayList<Medicion> medicionFiltrado;
	private ArrayList<CentroMeteorologico> centrosFiltrados;

	VentanaMediciones ventanaMediciones = new VentanaMediciones();

	public ControladorVentanaMediciones(VentanaMediciones ventanaCentrosMeteorologicos) {

		this.ventanaMediciones = ventanaCentrosMeteorologicos;
		enviarPeticion("centros", Peticiones.p105c);
		enviarPeticion("municipio", Peticiones.p103c);
		enviarPeticion("mediciones", Peticiones.p106a);
		llenarComboBoxMunicipios(municipios);
		iniciarControlador();
	}

	private void iniciarControlador() {

		ventanaMediciones.getcomboBoxMunicipio().addActionListener(this);
		ventanaMediciones.getcomboBoxMunicipio().setActionCommand("municipio");
		ventanaMediciones.getBtnBuscar().addActionListener(this);
		ventanaMediciones.getBtnBuscar().setActionCommand("buscar");
		ventanaMediciones.getBtnBuscar().setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "municipio":
			
			Municipio municipio = (Municipio) ventanaMediciones.getcomboBoxMunicipio().getSelectedItem();		
			ventanaMediciones.getBtnBuscar().setEnabled(true);
			mLimpiarTabla();
			llenarComboBoxCentros(filtroCentros(municipio.getIdMunicipio()));
			
			break;

		case "buscar":
			
			CentroMeteorologico centro = (CentroMeteorologico) ventanaMediciones.getComboBoxCentros().getSelectedItem();		
			llenarTabla(filtroMediciones(centro));
			
			break;
			
		}	
	}

	private void llenarComboBoxMunicipios(ArrayList<Municipio> municipios) {

		for (Municipio muni : municipios) {

			ventanaMediciones.getcomboBoxMunicipio().addItem(muni);
		}
	}

	private void llenarComboBoxCentros(ArrayList<CentroMeteorologico> centros) {

		ventanaMediciones.getComboBoxCentros().removeAllItems();
	
		for (CentroMeteorologico cent : centros) {

			ventanaMediciones.getComboBoxCentros().addItem(cent);
		}
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
//			matrizInfo[i][2] = String.valueOf(mediciones.get(i).getComgm3());
//			matrizInfo[i][3] = String.valueOf(mediciones.get(i).getNogm3());
//			matrizInfo[i][4] = String.valueOf(mediciones.get(i).getNo2());
//			matrizInfo[i][5] = String.valueOf(mediciones.get(i).getNo2ica());
//			matrizInfo[i][6] = String.valueOf(mediciones.get(i).getSo2());
//			matrizInfo[i][7] = String.valueOf(mediciones.get(i).getSo2ica());
//			matrizInfo[i][8] = String.valueOf(mediciones.get(i).getPm25());
//			matrizInfo[i][9] = String.valueOf(mediciones.get(i).getIcaEstacion());
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
