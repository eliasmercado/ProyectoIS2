package py.una.pol.ejb.dto;

public class HistoriaUsuarioRequest {

    private String nombre;
    private String descripcion;
    private int idProyecto;
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
    public int getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
    @Override
    public String toString() {
        return "HistoriaUsuarioRequest [descripcion=" + descripcion + ", idProyecto=" + idProyecto + ", nombre="
                + nombre + "]";
    }

    
    
}
