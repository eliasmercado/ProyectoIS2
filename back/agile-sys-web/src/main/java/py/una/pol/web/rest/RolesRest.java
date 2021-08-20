package py.una.pol.web.rest;

import py.una.pol.ejb.bean.PermisoBean;
import py.una.pol.ejb.bean.RolBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/rol")
public class RolesRest {
    @EJB
    RolBean rolBean;
    
    @GET
    @Produces("application/json")
    public ResponseDto getRoles() {
        ResponseDto response;
        try {
            List<RolResponseDto> listaRoles = rolBean.getRoles();
            response = new ResponseDto<List<RolResponseDto>>();
            response.setData(listaRoles);
        } catch(AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseDto createRol(RolRequestDto rolRequestDto) {
        ResponseDto response;
        try {
            RolPostResponseDto rol = rolBean.createRol(rolRequestDto);
            response = new ResponseDto<RolPostResponseDto>();
            response.setData(rol);
        } catch(AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{idRol}")
    public ResponseDto updateRol(@PathParam("idRol") Integer idPermiso, RolRequestDto permisoRequestDto) {
        ResponseDto response;
        MessageDto messageDto;
        try {
            messageDto = rolBean.updateRol(idPermiso, permisoRequestDto);
            response = new ResponseDto<MessageDto>();
            response.setData(messageDto);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            messageDto = new MessageDto();
            messageDto.setMessage(e.getDescripcion());
            response.setError(messageDto);
        }
        return response;
    }

    @DELETE
    @Produces("application/json")
    @Path("{idRol}")
    public ResponseDto deleteRol(@PathParam("idRol") Integer idPermiso) {
        ResponseDto response;
        MessageDto messageDto;
        try {
            messageDto = rolBean.deleteRol(idPermiso);
            response = new ResponseDto<MessageDto>();
            response.setData(messageDto);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            messageDto = new MessageDto();
            messageDto.setMessage(e.getDescripcion());
            response.setError(messageDto);
        }
        return response;
    }
}
