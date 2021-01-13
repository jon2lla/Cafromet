package modeloDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.cafromet.modelo.Provincia;


public class MunicipioDTO implements java.io.Serializable {

	private Integer idMunicipio;
	private String nombre;
	private String provincia;
	
	public MunicipioDTO() {
		
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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


