package py.una.pol.ejb.dto;

public class RolDto {

    private Integer idRol;
    private String rol;

    public RolDto(Integer idRol, String rol) {
        this.idRol = idRol;
        this.rol = rol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "RolDto{" +
                "idRol=" + idRol +
                ", rol='" + rol + '\'' +
                '}';
    }
}
