package py.una.pol.ejb.dto;

public class PermisoRequestDto {

    private String descripcion;
    private Integer idModulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    @Override
    public String toString() {
        return "PermisoRequestDto{" +
                "descripcion='" + descripcion + '\'' +
                ", idModulo=" + idModulo +
                '}';
    }
}
