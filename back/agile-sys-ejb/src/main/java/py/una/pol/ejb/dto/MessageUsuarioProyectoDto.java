package py.una.pol.ejb.dto;

import java.io.Serializable;

public class MessageUsuarioProyectoDto implements Serializable {
    private Integer idUsuarioProyecto;
    private String mensaje;

    public Integer getIdUsuarioProyecto() {
        return idUsuarioProyecto;
    }
    public void setIdUsuarioProyecto(Integer idUsuarioProyecto) {
        this.idUsuarioProyecto = idUsuarioProyecto;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    @Override
    public String toString() {
        return "MessageUsuarioProyectoDto{idUsuarioProyecto=" + 
                idUsuarioProyecto + 
                ", mensaje='" + 
                mensaje + '\'' +
                "}";
    }
}
