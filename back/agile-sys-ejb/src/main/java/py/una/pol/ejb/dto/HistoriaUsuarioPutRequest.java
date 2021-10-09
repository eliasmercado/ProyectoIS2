package py.una.pol.ejb.dto;

public class HistoriaUsuarioPutRequest {

    private String nombre;
    private String descripcion;
    private Integer idSprint;
    private Integer idUsuarioResponsable;
    private Integer idFase;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getIdSprint() {
        return idSprint;
    }
    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }
    public Integer getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }
    public void setIdUsuarioResponsable(Integer idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }
    public Integer getIdFase() {
        return idFase;
    }
    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }
    @Override
    public String toString() {
        return "HistoriaUsuarioPutRequest [descripcion=" + descripcion + ", idFase=" + idFase + ", idSprint=" + idSprint
                + ", idUsuarioResponsable=" + idUsuarioResponsable + ", nombre=" + nombre + "]";
    }

    

    
    
    
}
