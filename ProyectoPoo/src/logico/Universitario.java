package logico;

public class Universitario extends Persona {
    private String titulo;

    public Universitario(String id, String usuario, String contrasena, String email, String telefono, boolean disponible, String direccion, String provincia, String sexo, boolean tieneLicencia,String titulo) {
        
        super(id, usuario, contrasena, email, telefono, disponible, direccion, provincia, sexo, tieneLicencia);
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
}
//nota: en cada clase hija evalue sus sus requerimientos ej: tecnico tiene que cumplir con una cant de annios de experiencia