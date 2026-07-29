package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Obrero extends Persona implements Serializable{
    private ArrayList<String> habilidades;
    private static final long serialVersionUID = 1L;
 
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
    public String getCualificacion() {
        String resultado = "";
        int i = 0;
        while (i < habilidades.size()) {
            if (i == 0) {
                resultado = habilidades.get(i);
            } else {
                resultado = resultado + ", " + habilidades.get(i);
            }
            i++;
        }
        return resultado;
    }
}