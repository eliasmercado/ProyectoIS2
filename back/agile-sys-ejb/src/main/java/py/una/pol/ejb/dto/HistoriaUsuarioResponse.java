package py.una.pol.ejb.dto;

public class HistoriaUsuarioResponse {
    private int idHistoriaUsuario;
    private String message;
    public int getIdHistoriaUsuario() {
        return idHistoriaUsuario;
    }
    public void setIdHistoriaUsuario(int idHistoriaUsuario) {
        this.idHistoriaUsuario = idHistoriaUsuario;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "HistoriaUsuarioResponse [idHistoriaUsuario=" + idHistoriaUsuario + ", message=" + message + "]";
    }

     
}
