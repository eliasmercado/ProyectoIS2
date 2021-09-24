package py.una.pol.ejb.dto.DtoProyecto;

import java.io.Serializable;
import java.util.List;


public class ProyectoResponseDto implements Serializable {

    private Integer idProyecto;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String descripcion;
    private String estado;
    private List<ProyectoUsuarioDto> usuarios;
   
    public Integer getIdProyecto() {
        return idProyecto;
    }
    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
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
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public List<ProyectoUsuarioDto> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<ProyectoUsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }
    @Override
    public String toString() {
        return "ProyectoResponseDto [descripcion=" + descripcion + ", estado=" + estado + ", fechaFin=" + fechaFin
                + ", fechaInicio=" + fechaInicio + ", idProyecto=" + idProyecto + ", usuarios=" + usuarios
                + ", nombre=" + nombre + "]";
    }

    

}
