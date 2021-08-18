package py.una.pol.ejb.dto;

public class RolDto {

    private Integer idRol;
    private String descripcion;

    public RolDto(Integer idRol, String rol) {
        this.idRol = idRol;
        this.descripcion = rol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return descripcion;
    }

    public void setRol(String rol) {
        this.descripcion = rol;
    }

    @Override
    public String toString() {
        return "RolDto{" +
                "idRol=" + idRol +
                ", rol='" + descripcion + '\'' +
                '}';
    }
}
