
package py.una.pol.ejb.dto;


public class ResponseDto<T> {
  
    private T data;
    private T error;

    public ResponseDto() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseDto [data=" + data + ", error=" + error + "]";
    }



}
