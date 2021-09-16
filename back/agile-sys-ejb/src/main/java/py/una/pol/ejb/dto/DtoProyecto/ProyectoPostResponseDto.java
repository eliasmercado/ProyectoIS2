package py.una.pol.ejb.dto.DtoProyecto;

public class ProyectoPostResponseDto {
   
    private Integer idProyecto;
    private String message;

    public Integer getProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idPermiso) {
        this.idProyecto = idPermiso;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PermisoPostResponseDto{" +
                "idPermiso=" + idProyecto +
                ", message='" + message + '\'' +
                '}';
    }
}
