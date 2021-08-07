package py.una.pol.ejb.dto;

import java.util.Date;

public class ProyectoResponseDto {

    private String nombre;
    private Date fechaInicio;
    private String descripcion;

    public ProyectoResponseDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ProyectoResponseDto [descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", nombre=" + nombre
                + "]";
    }
}