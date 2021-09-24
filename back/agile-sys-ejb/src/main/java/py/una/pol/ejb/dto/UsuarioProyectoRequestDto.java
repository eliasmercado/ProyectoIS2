package py.una.pol.ejb.dto;

public class UsuarioProyectoRequestDto {
    private Integer idProyecto;
    private Integer idUsuario;

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioProyectoRequestDto{idProyecto=" + 
                idProyecto + 
                ", idUsuario=" + 
                idUsuario + 
                "}";
    }
}
