package logico;

import java.io.Serializable;

public class Universitario extends Persona implements Serializable{
    private String titulo;
    private static final long serialVersionUID = 1L;

    public Universitario(String id, String nombre, String email, String telefono, boolean disponible, String direccion, String provincia, String sexo, boolean tieneLicencia,String titulo) {
        
        super(id, nombre, email, telefono, disponible, direccion, provincia, sexo, tieneLicencia);
        this.titulo = titulo;
    }

    public String getTitulo() 
    { 
    	return titulo;
    }
    public void setTitulo(String titulo) 
    { 
    	this.titulo = titulo; 
    }
    public String getCualificacion() {
        return titulo;
    }
}
//nota: en cada clase hija evalue sus sus requerimientos ej: tecnico tiene que cumplir con una cant de annios de experiencia