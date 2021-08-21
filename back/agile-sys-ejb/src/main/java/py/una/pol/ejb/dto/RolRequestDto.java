package py.una.pol.ejb.dto;

public class RolRequestDto {
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "RolRequestDto [descripcion=" + descripcion + "]";
    }
}
