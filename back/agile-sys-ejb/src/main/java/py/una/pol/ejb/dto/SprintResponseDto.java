package py.una.pol.ejb.dto;

public class SprintResponseDto {

    private String message;
    private Integer idSprint;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Integer getIdSprint() {
        return idSprint;
    }
    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }
    @Override
    public String toString() {
        return "SprintResponseDto [idSprint=" + idSprint + ", message=" + message + "]";
    }

    
    
}
