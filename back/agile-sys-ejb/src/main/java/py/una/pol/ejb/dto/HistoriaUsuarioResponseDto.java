package py.una.pol.ejb.dto;

public class HistoriaUsuarioResponseDto {
    private int idHistoriaUsuario;
    private String nombre;
    private String descripcion;
    public int getIdHistoriaUsuario() {
        return idHistoriaUsuario;
    }
    
    public void setIdHistoriaUsuario(int idHistoriaUsuario) {
        this.idHistoriaUsuario = idHistoriaUsuario;
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

    @Override
    public String toString() {
        return "HistoriaUsuarioResponseDto [descripcion=" + descripcion + ", idHistoriaUsuario=" + idHistoriaUsuario
                + ", nombre=" + nombre + "]";
    }
}
