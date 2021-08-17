package py.una.pol.ejb.dto;

public class PermisoRequestDto {

    private String descripcion;
    private int idModulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
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
