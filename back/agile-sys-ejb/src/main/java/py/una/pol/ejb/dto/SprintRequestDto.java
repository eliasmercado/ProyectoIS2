package py.una.pol.ejb.dto;

public class SprintRequestDto {
    
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private Integer idProyecto;
    private Boolean completado;
    private Boolean iniciado;

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
    public Integer getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }
    public Boolean getCompletado() {
        return completado;
    }
    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    
    public Boolean getIniciado() {
        return iniciado;
    }
    public void setIniciado(Boolean iniciado) {
        this.iniciado = iniciado;
    }
    @Override
    public String toString() {
        return "SprintRequestDto [completado=" + completado + ", descripcion=" + descripcion + ", fechaFin=" + fechaFin
                + ", fechaInicio=" + fechaInicio + ", idProyecto=" + idProyecto + ", iniciado=" + iniciado + ", nombre="
                + nombre + "]";
    }
   
    
    

    
}
