package py.una.pol.ejb.dto;

public class RolPostResponseDto {
    private Integer idRol;
    private String message;

    public Integer getIdRol() {
        return idRol;
    }
    
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RolPostResponseDto [idRol=" + idRol + ", message=" + message + "]";
    }
}
