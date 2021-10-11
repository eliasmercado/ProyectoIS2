package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.*;

import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.bean.SessionBean;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.MessageDto;

import java.net.HttpURLConnection;

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
    public ResponseDto login(LoginDto loginDto) {
        ResponseDto response = null;

        try {
            LoginResponseDto loginResponseDto = sessionBean.login(loginDto);
            response = new ResponseDto<LoginResponseDto>();
            response.setData(loginResponseDto);

        } catch (AgileSysException e) {
            response = new ResponseDto<Error>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }catch(Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
