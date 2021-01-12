package modeloDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MunicipioDTO implements java.io.Serializable {

	private Integer idMunicipio;
	private String nombre;
	
	public MunicipioDTO() {
		
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}


