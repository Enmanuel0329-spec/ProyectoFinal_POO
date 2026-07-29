package logico;

import java.io.Serializable;

public class Tecnico extends Persona implements Serializable{
    private String especialidad;
    private static final long serialVersionUID = 1L;

    public Tecnico(String id, String usuario, String contrasena, String email, String telefono, boolean disponible, String direccion, String provincia, String sexo, boolean tieneLicencia, 
                   String especialidad) {
        
        super(id, usuario, contrasena, email, telefono, disponible, direccion, provincia, sexo, tieneLicencia);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() 
    { 
    	return especialidad; 
    }
    public void setEspecialidad(String especialidad) 
    { 
    	this.especialidad = especialidad; 
    }
    public String getCualificacion() {
        return especialidad;
    }
}