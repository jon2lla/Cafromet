package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.cafromet.modelo.EspacioNatural;
import com.cafromet.modelo.Municipio;
import com.cafromet.modelodto.MunicipioDTO;
import com.cafromet.server.Datos;
import com.cafromet.server.Peticiones;

public class ControladorVentanMunicipio implements ActionListener {

	private Datos datos;

	private ArrayList<Municipio> municipios;
	private ArrayList<Municipio> municipioFiltro;

	public VentanaMunicipio ventanaMunicipio = new VentanaMunicipio();

	public ControladorVentanMunicipio(VentanaMunicipio pVentanaMunicipio) {

		ventanaMunicipio = pVentanaMunicipio;

		enviarPeticion("prueba", Peticiones.p103a);
		iniciarControlador();

	}

	private void iniciarControlador() {

		ventanaMunicipio.getBtnFiltrar().addActionListener(this);
		ventanaMunicipio.getBtnFiltrar().setActionCommand("filtrar");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {

		case "filtrar":

			String provincia = (String) ventanaMunicipio.getComboBoxProvincia().getSelectedItem();
			
			if (provincia.equals("Araba/Álava")) {
				filtrar(1);
			}else if (provincia.equals("Gipuzkoa")) {
				filtrar(20);
			}else if (provincia.equals("Bizkaia")) {
				filtrar(48);
			}				
			break;
		}

	}

	public boolean filtrar(int provincia) {
		
		mLimpiarTabla();
		
		municipioFiltro = new ArrayList<Municipio>();

		int x = 0;
		for (Municipio municipioFiltro2 : municipios) {
			if (municipios.get(x).getProvincia().getIdProvincia() == provincia) {
				municipioFiltro.add(municipioFiltro2);
			}
			x++;
		}

		String matrizInfo[][] = new String[municipioFiltro.size()][2];

		for (int i = 0; i < municipioFiltro.size(); i++) {
			matrizInfo[i][0] = municipioFiltro.get(i).getNombre();
			matrizInfo[i][1] = municipioFiltro.get(i).getDescripcion();
			ventanaMunicipio.getDefaultTableModel().addRow(matrizInfo[i]);
		}

		return true;
	}

	public void mLimpiarTabla() {

		if (ventanaMunicipio.getDefaultTableModel().getRowCount() > 0) {
			ventanaMunicipio.getDefaultTableModel().setRowCount(0);
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

			String matrizInfo[][] = new String[municipios.size()][2];

			for (int i = 0; i < municipios.size(); i++) {
				matrizInfo[i][0] = municipios.get(i).getNombre();
				matrizInfo[i][1] = municipios.get(i).getDescripcion();
				ventanaMunicipio.getDefaultTableModel().addRow(matrizInfo[i]);
			}
			break;
		}
		return true;
	}
	
}
