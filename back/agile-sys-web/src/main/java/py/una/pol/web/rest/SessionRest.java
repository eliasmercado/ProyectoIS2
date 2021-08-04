package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.bean.SessionBean;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.MessageDto;



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
    public ResponseDto login(LoginDto loginDto){
        ResponseDto response;
       
        try {
            LoginResponseDto loginResponseDto = sessionBean.login(loginDto);
            response = new ResponseDto<LoginResponseDto>();
            response.setData(loginResponseDto);
          
        } catch (AgileSysException e) {
            response = new ResponseDto<Error>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
       
        return response;
    }
}
