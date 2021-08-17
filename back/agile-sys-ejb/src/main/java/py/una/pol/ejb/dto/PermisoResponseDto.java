package py.una.pol.ejb.dto;

public class PermisoResponseDto {
   
    private Integer idPermiso;
    private String descripcion;
    private ModuloDto modulo;

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ModuloDto getModulo() {
        return modulo;
    }

    public void setModulo(ModuloDto modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "PermisoResponseDto{" +
                "idPermiso=" + idPermiso +
                ", descripcion='" + descripcion + '\'' +
                ", modulo=" + modulo +
                '}';
    }
}
