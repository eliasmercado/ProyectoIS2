package py.una.pol.ejb.dto.DtoProyecto;

public class ProyectoUsuarioDto {

    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
    @Override
    public String toString() {
        return "ProyectoUsuarioDto [apellidos=" + apellidos + ", idUsuario=" + idUsuario + ", nombres=" + nombres + "]";
    }

    
    
}
