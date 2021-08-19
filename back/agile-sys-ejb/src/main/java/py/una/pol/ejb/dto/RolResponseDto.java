package py.una.pol.ejb.dto;

import java.util.List;

public class RolResponseDto {
    private Integer idRol;
    private String descripcion;
    private List<PermisoResponseDto> permisos;
    public Integer getIdRol() {
        return idRol;
    }
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<PermisoResponseDto> getPermisos() {
        return permisos;
    }
    public void setPermisos(List<PermisoResponseDto> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "RolResponseDto [descripcion=" + descripcion + ", idRol=" + idRol + ", permisos=" + permisos + "]";
    }    
}
