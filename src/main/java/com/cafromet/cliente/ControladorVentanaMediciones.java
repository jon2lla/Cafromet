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
		iniciarControlador();
		enviarPeticion("centros", Peticiones.p105c);
		enviarPeticion("municipio", Peticiones.p103c);
		enviarPeticion("mediciones", Peticiones.p106a);
		llenarComboBoxMunicipios(municipios);
	}

	private void iniciarControlador() {

		ventanaMediciones.getcomboBoxMunicipio().addActionListener(this);
		ventanaMediciones.getcomboBoxMunicipio().setActionCommand("muni");
		ventanaMediciones.getComboBoxCentros().addActionListener(this);
		ventanaMediciones.getComboBoxCentros().setActionCommand("centro");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "muni":
			
			Municipio municipio = (Municipio) ventanaMediciones.getcomboBoxMunicipio().getSelectedItem();

			llenarComboBoxCentros(filtroCentros(municipio.getIdMunicipio()));
			
			break;
			
		case "centro":
			
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

//		int x = 0;
//		for (Medicion med : mediciones) {
//			if (med.getId().getIdCentroMet() == centrosFiltrados.get(x).getIdCentroMet()) {
//				medicionFiltrado.add(med);
//			}
//		}

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

	public boolean llenarTabla(ArrayList<Medicion> mediciones2) {

		mLimpiarTabla();
		String matrizInfo[][] = new String[mediciones.size()][3];

		for (int i = 0; i < mediciones2.size(); i++) {

			matrizInfo[i][0] = String.valueOf(mediciones2.get(i).getId().getFecha());
			matrizInfo[i][1] = String.valueOf(mediciones2.get(i).getId().getHora());
			matrizInfo[i][2] = String.valueOf(mediciones2.get(i).getIca());
			ventanaMediciones.getDefaultTableModel().addRow(matrizInfo[i]);
		}

		return true;
	}
	
	public void mLimpiarTabla() {

		if (ventanaMediciones.getDefaultTableModel().getRowCount() > 0) {
			ventanaMediciones.getDefaultTableModel().setRowCount(0);
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
