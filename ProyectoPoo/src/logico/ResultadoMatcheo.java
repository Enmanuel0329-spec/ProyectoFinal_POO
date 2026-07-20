package logico;

public class ResultadoMatcheo {

	public ResultadoMatcheo(Solicitud solicitud, Oferta oferta, float porcentaje) {
		super();
		this.solicitud = solicitud;
		this.oferta = oferta;
		this.porcentaje = porcentaje;
	}
	
	private Solicitud solicitud;
    private Oferta oferta;
    private float porcentaje;
    
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
    
}
