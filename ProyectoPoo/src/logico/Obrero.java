package logico;

import java.util.ArrayList;

public class Obrero extends Persona {
    private ArrayList<String> habilidades;
 
    public Obrero(String id, String usuario, String contrasena, String email, String telefono, boolean disponible, String direccion,String provincia, String sexo, boolean tieneLicencia) {
        super(id, usuario, contrasena, email, telefono, disponible, direccion, provincia, sexo, tieneLicencia);
        this.habilidades = new ArrayList<String>(); 
    }
 
    public ArrayList<String> getHabilidades() 
    { 
    	return habilidades; 
    }
    public void setHabilidades(ArrayList<String> habilidades) 
    { 
    	this.habilidades = habilidades; 
    }
}