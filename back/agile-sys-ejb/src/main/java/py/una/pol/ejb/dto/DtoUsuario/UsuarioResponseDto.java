package py.una.pol.ejb.dto.DtoUsuario;


import java.io.Serializable;
import py.una.pol.ejb.dto.RolDto;

public class UsuarioResponseDto implements Serializable {
   
    private Integer idUsuario;
    private String usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private RolDto rol;
    private Integer idProyecto;


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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }

    
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "UsuarioResponseDto [apellidos=" + apellidos + ", email=" + email + ", idProyecto=" + idProyecto
                + ", idUsuario=" + idUsuario + ", nombres=" + nombres + ", rol=" + rol + ", telefono=" + telefono
                + ", usuario=" + usuario + "]";
    }

    
}
