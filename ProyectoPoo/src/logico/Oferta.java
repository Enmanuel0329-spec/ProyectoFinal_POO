package logico;

import java.util.Date;

public class Oferta {

	private String codigo;
	private String descripcionPuesto;
	private int cantidadPuestos;
	private String sexo;
	private String tipo;
	private boolean requiereLicencia;
	private boolean requiereDispMudarse;
	private String modalidad;
	private String tipoJornada;
	private String provincia;
	private float salarioMinimo;
	private float salarioMaximo;
	private float porcentajeMinimo;
	private Date fecha;
	private Empresa empresa;
	
	private boolean activa;

	public Oferta(String codigo, String descripcionPuesto, int cantidadPuestos,
			String sexo, String tipo, boolean requiereLicencia,
			boolean requiereDispMudarse, String modalidad, String tipoJornada, String provincia,
			float salarioMinimo, float salarioMaximo, float porcentajeMinimo, Date fecha, Empresa empresa) {
		this.codigo = codigo;
		this.descripcionPuesto = descripcionPuesto;
		this.cantidadPuestos = cantidadPuestos;
		this.sexo = sexo;
		this.tipo = tipo;
		this.requiereLicencia = requiereLicencia;
		this.requiereDispMudarse = requiereDispMudarse;
		this.modalidad = modalidad;
		this.tipoJornada = tipoJornada;
		this.provincia = provincia;
		this.salarioMinimo = salarioMinimo;
		this.salarioMaximo = salarioMaximo;
		this.porcentajeMinimo = porcentajeMinimo;
		this.fecha = fecha;
		this.empresa = empresa;
		this.activa = true;
	}

	public String getCodigo() { return codigo; }
	public String getDescripcionPuesto() { return descripcionPuesto; }
	public void setDescripcionPuesto(String descripcionPuesto) { this.descripcionPuesto = descripcionPuesto; }
	public int getCantidadPuestos() { return cantidadPuestos; }
	public void setCantidadPuestos(int cantidadPuestos) { this.cantidadPuestos = cantidadPuestos; }
	public String getSexo() { return sexo; }
	public String getTipo() { return tipo; }
	public boolean isRequiereLicencia() { return requiereLicencia; }
	public boolean isRequiereDispMudarse() { return requiereDispMudarse; }
	public String getModalidad() { return modalidad; }
	public String getProvincia() { return provincia; }
	public float getSalarioMinimo() { return salarioMinimo; }
	public float getSalarioMaximo() { return salarioMaximo; }
	public Date getFecha() { return fecha; }
	public Empresa getEmpresa() { return empresa; }
	public boolean isActiva() { return activa; }
	public void setActiva(boolean activa) { this.activa = activa; }

	public float getPorcentajeMinimo() {
		return porcentajeMinimo;
	}

	public void setPorcentajeMinimo(float porcentajeMinimo) {
		this.porcentajeMinimo = porcentajeMinimo;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

}
