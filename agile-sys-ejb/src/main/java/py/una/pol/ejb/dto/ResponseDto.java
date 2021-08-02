package py.una.pol.ejb.dto;

public class ResponseDto<T> {
  
    private T data;
    private String message;
    private Boolean error;

    public ResponseDto() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseDto [data=" + data + ", error=" + error + ", message=" + message + "]";
    }

    
    
}
