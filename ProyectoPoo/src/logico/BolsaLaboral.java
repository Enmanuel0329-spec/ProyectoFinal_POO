package logico;

import java.util.ArrayList;

public class BolsaLaboral {

	private ArrayList<Persona> losPersonal;
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
//	private ArrayList<Usuario> losUsuarios;


	public static int generadorIdSolicitud = 1;
	public static int generadorIdOferta = 1;
	public static int generadorIdEmpresa = 1;
	public static int generadorIdPersona = 1;
	public static int generadorIdUsuario = 1;

	private static BolsaLaboral instancia = null;

	private BolsaLaboral() {
		losPersonal = new ArrayList<>();
		lasEmpresas = new ArrayList<>();
		lasOfertas = new ArrayList<>();
//		losUsuarios = new ArrayList<>();
		lasSolicitudes = new ArrayList<>();
	}

	public static BolsaLaboral getInstancia() {
		if (instancia == null) instancia = new BolsaLaboral();
		return instancia;
	}

	public void registrarPersona(Persona p) {
		losPersonal.add(p);
		generadorIdPersona++;
	}

	public void registrarEmpresa(Empresa e) {
		lasEmpresas.add(e);
		generadorIdEmpresa++;
	}

	public void registrarOferta(Oferta o) {
		lasOfertas.add(o);
		generadorIdOferta++;
	}

	public void registrarSolicitud(Solicitud s) {
		lasSolicitudes.add(s);
		generadorIdSolicitud++;
	}

	/*public void registrarUsuario(Usuario u) {
		losUsuarios.add(u);
		generadorIdUsuario++;
	}*/

	public Persona buscarPersona(String id) {
		Persona aux = null;
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < losPersonal.size()) {
			if (losPersonal.get(i).getId().equalsIgnoreCase(id)) {
				aux = losPersonal.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Empresa buscarEmpresa(String rnc) {
		Empresa aux = null;
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < lasEmpresas.size()) {
			if (lasEmpresas.get(i).getRnc().equalsIgnoreCase(rnc)) {
				aux = lasEmpresas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public Oferta buscarOferta(String codigo) {
		Oferta aux = null;
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < lasOfertas.size()) {
			if (lasOfertas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux = lasOfertas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	/*public Usuario buscarUsuario(String username) {
		Usuario aux = null;
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < losUsuarios.size()) {
			if (losUsuarios.get(i).getUsername().equalsIgnoreCase(username)) {
				aux = losUsuarios.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}*/

	public Solicitud buscarSolicitud(String codigo) {
		Solicitud aux = null;
		int i = 0;
		boolean encontrado = false;
		while (!encontrado && i < lasSolicitudes.size()) {
			if (lasSolicitudes.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux = lasSolicitudes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public ArrayList<Solicitud> solicitudesPorPersona(Persona p) {
		ArrayList<Solicitud> resultado = new ArrayList<>();
		for (Solicitud solicitud : lasSolicitudes) {
			if (solicitud.getSolicitante().getId().equals(p.getId())) {
				resultado.add(solicitud);
			}
		}
		return resultado;
	}

	public ArrayList<Oferta> ofertasPorEmpresa(Empresa e) {
		ArrayList<Oferta> resultado = new ArrayList<>();
		for (Oferta oferta : lasOfertas) {
			if (oferta.getEmpresa().getRnc().equals(e.getRnc())) {
				resultado.add(oferta);
			}
		}
		return resultado;
	}

	public ArrayList<Persona> getLosPersonal() { return losPersonal; }
	public ArrayList<Empresa> getLasEmpresas() { return lasEmpresas; }
	public ArrayList<Oferta> getLasOfertas() { return lasOfertas; }
	public ArrayList<Solicitud> getLasSolicitudes() { return lasSolicitudes; }
//	public ArrayList<Usuario> getLosUsuarios() { return losUsuarios; }

	public ArrayList<ResultadoMatcheo> matcheoCandidatosParaOferta(Oferta oferta) {
		ArrayList<ResultadoMatcheo> resultados = new ArrayList<>();

		for (Solicitud solicitud : lasSolicitudes) {
			
			if (!solicitud.getSolicitante().isDisponible()) continue;
			if (solicitud.getEstado().equalsIgnoreCase("hold")) continue;
			if (solicitud.getEstado().equalsIgnoreCase("completada")) continue;
			
			int puntos = 0;
			float total = 6;
			
			
			if (solicitud.getSolicitante() instanceof Universitario && 
					oferta.getTipo().equalsIgnoreCase("universitario")) puntos++;
			else if (solicitud.getSolicitante() instanceof Tecnico && 
					oferta.getTipo().equalsIgnoreCase("tecnico")) puntos++;
			else if (solicitud.getSolicitante() instanceof Obrero && 
					oferta.getTipo().equalsIgnoreCase("obrero")) puntos++;

			if(solicitud.getTipoJornada().equalsIgnoreCase(oferta.getTipoJornada()))puntos++;
			if (solicitud.getSolicitante().getSexo().equalsIgnoreCase(oferta.getSexo())) puntos++;

			if (!oferta.isRequiereLicencia() ||
					solicitud.getSolicitante().isTieneLicencia()) {
				puntos++;
			}
			
			if (!oferta.isRequiereDispMudarse() ||
					solicitud.isDispuestoMudarse()) {
				puntos++;
			}

			if (solicitud.getSolicitante().getProvincia().equalsIgnoreCase(oferta.getProvincia())) puntos++;

			float porcentaje = (  (float)puntos / total) * 100;

			if (porcentaje >= oferta.getPorcentajeMinimo()) {
				resultados.add(new ResultadoMatcheo(solicitud, oferta, porcentaje));
			}
		}

		resultados.sort((r1, r2) ->
		Float.compare(r2.getPorcentaje(), r1.getPorcentaje()));

		if (resultados.size() > 3) {
			return new ArrayList<>(resultados.subList(0, 3));
		}

		return resultados;
	}

	public void ejecutarMatcheoEmpresa(Empresa e) {
		for (Oferta oferta : ofertasPorEmpresa(e)) {
			if (oferta.isActiva()) {
				ArrayList<ResultadoMatcheo> candidatos = matcheoCandidatosParaOferta(oferta);
				// pantalla
			}
		}
	}

	public void contratarCandidato(Oferta oferta, Solicitud solicitudContratada){
		Persona persona = solicitudContratada.getSolicitante();
		persona.setDisponible(false);
		solicitudContratada.setEstado("completada");
		oferta.setCantidadPuestos(oferta.getCantidadPuestos() - 1);
		if (oferta.getCantidadPuestos() == 0) {
			oferta.setActiva(false);
		}
		for (Solicitud s : solicitudesPorPersona(persona)) {
			if (!s.getCodigo().equals(solicitudContratada.getCodigo())) {
				s.setEstado("hold");
			}
		}
	}

	/*public Usuario crearUsuarioDesdeEmpresa(Empresa empresa) {
		String correo = empresa.getCorreo();
		String username = correo.substring(0, correo.indexOf("@"));
		String password = String.valueOf(1000 + new java.util.Random().nextInt(9000));
		String id = "U-" + generadorIdUsuario;
		Usuario u = new Usuario(id, username, password, empresa, null, true);
		registrarUsuario(u);
		return u;
	}*/

//	public Usuario crearUsuarioDesdePersona(Persona persona) {
//		String correo = persona.getEmail();
//		String username = correo.substring(0, correo.indexOf("@"));
//		String password = String.valueOf(1000 + new java.util.Random().nextInt(9000));
//		String id = "U-" + generadorIdUsuario;
//		Usuario u = new Usuario(id, username, password, null, persona, false);
//		registrarUsuario(u);
//		return u;
//	}

}
