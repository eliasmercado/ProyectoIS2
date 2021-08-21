package py.una.pol.ejb.dto;

import java.util.List;

public class RolResponseDto {
    private Integer idRol;
    private String rol;
    private List<PermisoResponseDto> permisos;
    public Integer getIdRol() {
        return idRol;
    }
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String descripcion) {
        this.rol = descripcion;
    }
    public List<PermisoResponseDto> getPermisos() {
        return permisos;
    }
    public void setPermisos(List<PermisoResponseDto> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "RolResponseDto [rol=" + rol + ", idRol=" + idRol + ", permisos=" + permisos + "]";
    }    
}
