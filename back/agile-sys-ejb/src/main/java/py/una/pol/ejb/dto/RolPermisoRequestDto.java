package py.una.pol.ejb.dto;

public class RolPermisoRequestDto {

    private Integer idRol;
    private Integer idPermiso;


    public RolPermisoRequestDto() {
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    @Override
    public String toString() {
        return "RolPermisoRequestDto{" +
                "idRol='" + idRol + '\'' +
                ", idPermiso='" + idPermiso + '\'' +
                '}';
    }
}
