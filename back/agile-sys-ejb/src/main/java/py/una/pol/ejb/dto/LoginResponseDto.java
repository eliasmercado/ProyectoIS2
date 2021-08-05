package py.una.pol.ejb.dto;

import javax.persistence.Column;
import javax.validation.constraints.Null;

public class LoginResponseDto {

    private Integer idUsuario;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private Boolean esAdmin;
    @Column(nullable = true)
    private Integer idProyecto;

    
    
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
    public Boolean getEsAdmin() {
        return esAdmin;
    }
    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
    
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "LoginResponseDto [apellidos=" + apellidos + ", email=" + email + ", esAdmin=" + esAdmin
                + ", idProyecto=" + idProyecto + ", idUsuario=" + idUsuario + ", nombres=" + nombres + ", usuario="
                + usuario + "]";
    }    
}
