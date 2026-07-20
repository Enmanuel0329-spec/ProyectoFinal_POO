package logico;
import java.util.ArrayList;
public class Empresa {


	private String rnc;
    private String nombre;
    private String representante;
    private String direccion;
    private String telefono;
    private String correo;
    private String tipo;
    private ArrayList<Oferta> ofertas;
    
    public Empresa(String rnc, String nombre, String representante, String direccion, String telefono, String correo,
			String tipo) {
		super();
		this.rnc = rnc;
		this.nombre = nombre;
		this.representante = representante;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.tipo = tipo;
		this.ofertas = new ArrayList<>();
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRnc() {
		return rnc;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}
    

}

