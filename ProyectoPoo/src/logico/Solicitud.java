package logico;

public class Solicitud {


	private String codigo;
	private Persona solicitante;
	private String area;
	private String cargoDeseado;
	private String tipoJornada; 
	private float salarioMinimo;
	private float salarioMaximo;
	private boolean dispuestoMudarse;
	private String estado;
	
	public Solicitud(String codigo, Persona solicitante, String area, String cargoDeseado, String tipoJornada,
			float salarioMinimo, float salarioMaximo, boolean dispuestoMudarse) {
		super();
		this.codigo = codigo;
		this.solicitante = solicitante;
		this.area = area;
		this.cargoDeseado = cargoDeseado;
		this.tipoJornada = tipoJornada;
		this.salarioMinimo = salarioMinimo;
		this.salarioMaximo = salarioMaximo;
		this.estado = "activa";
		this.dispuestoMudarse = dispuestoMudarse;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCargoDeseado() {
		return cargoDeseado;
	}

	public void setCargoDeseado(String cargoDeseado) {
		this.cargoDeseado = cargoDeseado;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public float getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(float salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public float getSalarioMaximo() {
		return salarioMaximo;
	}

	public void setSalarioMaximo(float salarioMaximo) {
		this.salarioMaximo = salarioMaximo;
	}

	public boolean isDispuestoMudarse() {
		return dispuestoMudarse;
	}

	public void setDispuestoMudarse(boolean dispuestoMudarse) {
		this.dispuestoMudarse = dispuestoMudarse;
	}

	public String getCodigo() {
		return codigo;
	}

	public Persona getSolicitante() {
		return solicitante;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	  
}
