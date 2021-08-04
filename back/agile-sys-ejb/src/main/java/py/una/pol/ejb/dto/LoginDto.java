package py.una.pol.ejb.dto;

public class LoginDto {

    private String usuario;
    private String password;
   
    
    public LoginDto() {
    }
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto [password=" + password + ", usuario=" + usuario + "]";
    }
    
    
    
}
