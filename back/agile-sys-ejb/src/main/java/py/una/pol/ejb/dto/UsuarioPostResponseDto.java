package py.una.pol.ejb.dto;

public class UsuarioPostResponseDto {


    private int idPermiso;
    private String message;

    public int getIdUsuario() {
        return idPermiso;
    }

    public void setIdUsuario(int idUsuario) {
        this.idPermiso = idUsuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UsuarioPostResponseDto{" +
                "idUsuario=" + idPermiso +
                ", message='" + message + '\'' +
                '}';
    }
}
