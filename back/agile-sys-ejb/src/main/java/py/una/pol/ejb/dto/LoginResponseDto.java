package py.una.pol.ejb.dto;

public class LoginResponseDto {

    private Integer idUsuario;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private Integer idProyecto;
    private Integer idRol;
    private String rol;

    public LoginResponseDto() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idRol) {
        this.idProyecto = idRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "LoginResponseDto [apellidos=" + apellidos + ", email=" + email + ", idProyecto=" + idProyecto + ", idUsuario=" + idUsuario + ", nombres=" + nombres + ", usuario="
                + usuario + "]";
    }
}
