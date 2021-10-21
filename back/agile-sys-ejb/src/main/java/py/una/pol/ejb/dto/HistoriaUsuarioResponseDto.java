package py.una.pol.ejb.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoriaUsuarioResponseDto {
    private int idHistoriaUsuario;
    private String nombre;
    private String descripcion;
    private String fechaCreacion;
    private int idUsuarioResponsable;
    private int idSprint;
    private int idFase;

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

    public String getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(int idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    @Override
    public String toString() {
        return "HistoriaUsuarioResponseDto [descripcion=" + descripcion + ", idHistoriaUsuario=" + idHistoriaUsuario
                + ", nombre=" + nombre + "]";
    }
}
