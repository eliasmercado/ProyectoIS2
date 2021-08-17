package py.una.pol.ejb.dto;

public class RolDto {

    private int idRol;
    private String descripcion;

    public RolDto(int idRol, String rol) {
        this.idRol = idRol;
        this.descripcion = rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
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
