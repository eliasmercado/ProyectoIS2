package py.una.pol.ejb.dto;

public class PermisoPostResponseDto {
   
    private Integer idPermiso;
    private String message;

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
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
                "idPermiso=" + idPermiso +
                ", message='" + message + '\'' +
                '}';
    }
}
