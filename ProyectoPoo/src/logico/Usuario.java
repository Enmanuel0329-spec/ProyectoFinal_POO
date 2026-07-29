package logico;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String id;
    private String username;
    private String password;
    private Empresa empresa;
    private Persona persona;
    private boolean esEmpresa;

    private static final long serialVersionUID = 1L;

    public Usuario(String id, String username, String password, Empresa empresa, Persona persona, boolean esEmpresa) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.empresa = empresa;
        this.persona = persona;
        this.esEmpresa = esEmpresa;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Empresa getEmpresa() { return empresa; }
    public Persona getPersona() { return persona; }
    public boolean isEsEmpresa() { return esEmpresa; }
    
}