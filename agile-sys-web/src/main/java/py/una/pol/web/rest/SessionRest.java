package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import py.una.pol.ejb.bean.SessionBean;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.dto.ResponseDto;

@Path("/login")
public class SessionRest {

    @EJB
    SessionBean sessionBean;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseDto <LoginResponseDto> login(LoginDto loginDto){
        ResponseDto<LoginResponseDto> response = new ResponseDto<>();
        LoginResponseDto loginResponseDto = sessionBean.login(loginDto);

        if(loginResponseDto != null){
            response.setData(loginResponseDto);
            response.setMessage("Login Exitoso");
            response.setError(false);
        }else{
            response.setMessage("Login Fallido");
            response.setError(true);
        }


        return response;
    }
}
