package com.cafromet.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.cafromet.modelo.Medicion;

public class ControladorVentanaTop implements ActionListener {

	VentanaTop ventanaTop = new VentanaTop();

	public ControladorVentanaTop(VentanaTop ventanaTop) {
		
		this.ventanaTop = ventanaTop;
		
		iniciarControlador();
//		llenarTabla(mediciones);
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
		String matrizInfo[][] = new String[mediciones.size()][10];

		for (int i = 0; i < mediciones.size(); i++) {
		
			if(mediciones.get(i).getRadSolar() == null) {
				matrizInfo[i][6] = "SIN DATOS";
			}else {
				matrizInfo[i][6] = String.valueOf(mediciones.get(i).getRadSolar() + " W/m2");
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
	
}
