package py.una.pol.ejb.dto;

import java.util.List;

public class SprintGetResponseDto {
    private int idSprint;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idProyecto;
    private List<HistoriaUsuarioResponseDto> historiasUsuario;
    private Boolean completado;
    private Boolean iniciado;

    public Boolean getCompletado() {return completado;}
    public void setCompletado(Boolean completado) {this.completado = completado;}
    public int getIdSprint() {
        return idSprint;
    }
    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }
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
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
    public List<HistoriaUsuarioResponseDto> getHistoriasUsuario() {
        return historiasUsuario;
    }
    public void setHistoriasUsuario(List<HistoriaUsuarioResponseDto> historiasUsuario) {
        this.historiasUsuario = historiasUsuario;
    }

    

    public Boolean getIniciado() {
        return iniciado;
    }
    public void setIniciado(Boolean iniciado) {
        this.iniciado = iniciado;
    }
    @Override
    public String toString() {
        return "SprintGetResponseDto [completado=" + completado + ", descripcion=" + descripcion + ", fechaFin="
                + fechaFin + ", fechaInicio=" + fechaInicio + ", historiasUsuario=" + historiasUsuario + ", idProyecto="
                + idProyecto + ", idSprint=" + idSprint + ", iniciado=" + iniciado + ", nombre=" + nombre + "]";
    }
    
    
}
