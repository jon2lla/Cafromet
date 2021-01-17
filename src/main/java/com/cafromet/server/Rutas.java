package com.cafromet.server;

import java.io.File;

enum Rutas {
	RUTA_RES("src" + File.separator 
			+ "resource" + File.separator 
			+ "com" + File.separator
			+ "cafromet" + File.separator
			+ "files" + File.separator),
	RUTA_JSON(RUTA_RES + "json" + File.separator),
	RUTA_TEMP(RUTA_RES + "temp" + File.separator),
	RUTA_XML(RUTA_RES + "xml" + File.separator),
	DATOS_PUEBLOS(RUTA_JSON + "pueblos.json"),
	DATOS_ESPACIOS_NAT(RUTA_JSON + "espacios-naturales.json"),
	DATOS_ESTACIONES(RUTA_JSON + "estaciones.json"),
	DATOS_INDEX(RUTA_TEMP + "index.json"),
	URL_PUEBLOS("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/pueblos.json"),
	URL_ESPACIOS("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json"),
	URL_ESTACIONES("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json"),
	URL_INDEX("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");
	
    private String ruta;

	Rutas(String ruta) {
		this.ruta = ruta;
	}
	
	 public String getCodigo() {
	        return ruta;
	    }
}
