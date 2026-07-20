package logico;

public class Persona {
    private String id;
    private String usuario;
    private String contrasena;
    private String email;
    private String telefono;
    private boolean disponible;
    private String direccion;
    private String provincia;
    private String sexo;
    private boolean tieneLicencia;

    public Persona(String id, String usuario, String contrasena, String email, String telefono, boolean disponible, String direccion, String provincia, String sexo, boolean tieneLicencia) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
        this.telefono = telefono;
        this.disponible = disponible;
        this.direccion = direccion;
        this.provincia = provincia;
        this.sexo = sexo;
        this.tieneLicencia = tieneLicencia;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public boolean isTieneLicencia() { return tieneLicencia; }
    public void setTieneLicencia(boolean tieneLicencia) { this.tieneLicencia = tieneLicencia; }
}