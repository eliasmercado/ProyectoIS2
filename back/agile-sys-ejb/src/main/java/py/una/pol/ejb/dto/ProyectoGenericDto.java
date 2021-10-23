package py.una.pol.ejb.dto;


public class ProyectoGenericDto {

    private String nombre;
    private String fechaInicio;
    private String descripcion;
    private String fechaFin;

    public ProyectoGenericDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "ProyectoGenericDto [descripcion=" + descripcion + ", fechaFin=" + fechaFin + ", fechaInicio="
                + fechaInicio + ", nombre=" + nombre + "]";
    }

    
}