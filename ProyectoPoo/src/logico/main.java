package logico;

import java.util.ArrayList;
import java.util.Date;

public class main {

	public static void main(String[] args) {

		BolsaLaboral bolsa = BolsaLaboral.getInstancia(); 
		Empresa emp = new Empresa("RNC-12345", "TechDominicana", "Juan Perez", "Av. Pedro A. Rivera, La Vega",
				"809-555-5555", "rrhh@techdom.com", "Tecnologia");
		bolsa.registrarEmpresa(emp);
		Oferta oferta = new Oferta("OF-1", "Tecnologia", "Desarrollador de Software", "Ingenieria de Sistemas", 2, "cualquiera", "Universitario", false, false, "Remoto", 
				"Tiempo Completo", "Santiago", 30000f, 60000f, 60f, new Date(), emp);
		bolsa.registrarOferta(oferta);



		Universitario uni = new Universitario("P-1", "MariaLopez", "maria@gmail.com", "829-555-1234", true, "Centro", "La Vega", 
				"Femenino", true, "Ingenieria de Sistemas");
		bolsa.registrarPersona(uni);
		Tecnico tec = new Tecnico("P-2", "CarlosRamirez", "carlos@gmail.com", "809-555-4321", true, "Palmarito", "Santiago", 
				"Masculino", false, "Redes");

		bolsa.registrarPersona(tec); 
		Solicitud solMaria = new Solicitud("SOL-1", uni, "Tecnologia", "Desarrollador", "Tiempo Completo", 40000f, 70000f, false);
		bolsa.registrarSolicitud(solMaria);
		Solicitud solCarlos = new Solicitud("SOL-2", tec, "Tecnologia", "Desarrollador de Soporte", "Medio Tiempo", 20000f, 30000f, true);
		bolsa.registrarSolicitud(solCarlos);

		System.out.println("==================================================");
		System.out.println(" BUSCANDO CANDIDATOS PARA: " + oferta.getDescripcionPuesto());
		System.out.println("==================================================");

		ArrayList<ResultadoMatcheo> matches = bolsa.matcheoCandidatosParaOferta(oferta);

		if (matches.isEmpty()) {
			System.out.println("No se encontraron candidatos que cumplan con el minimo de coincidencia.");
		} else {
			for (ResultadoMatcheo rm : matches) {
				System.out.println("-> Candidato: " + rm.getSolicitud().getSolicitante().getNombre());
				System.out.println("   Nivel: " + rm.getSolicitud().getSolicitante().getClass().getSimpleName());
				System.out.println("   Porcentaje de Match: " + String.format("%.2f", rm.getPorcentaje()) + "%");
				System.out.println("--------------------------------------------------");
			}
		}

		if(!matches.isEmpty()) {
			System.out.println("\n*** Contratando al mejor candidato (" + matches.get(0).getSolicitud().getSolicitante().getNombre() + ") ***");
			bolsa.contratarCandidato(oferta, matches.get(0).getSolicitud());

			System.out.println("Estado de la solicitud: " + matches.get(0).getSolicitud().getEstado());
			System.out.println("Disponibilidad de la persona: " + matches.get(0).getSolicitud().getSolicitante().isDisponible());
			System.out.println("Puestos restantes en la empresa: " + oferta.getCantidadPuestos());
		}
	}
}