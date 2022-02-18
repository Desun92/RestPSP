package practica2.castro.adrian.paises.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaisEntity {

	@Id
	private String id;
	private String nombre;
	private String descripcion;
	private String rutaFoto;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String name) {
		this.nombre = name;
	}
	
	public String getDescripcion() {
	return descripcion;
		}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getRutaFoto() {
		return rutaFoto;
	}
	
	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto=rutaFoto;
	}
}
