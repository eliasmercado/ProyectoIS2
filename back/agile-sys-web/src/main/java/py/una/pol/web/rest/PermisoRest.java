package py.una.pol.web.rest;


import py.una.pol.ejb.bean.PermisoBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/permiso")
public class PermisoRest {
    @EJB
    PermisoBean permisoBean;

    @GET
    @Produces("application/json")
    public ResponseDto getPermisos() {

        ResponseDto response;

        try {
            List<PermisoResponseDto> listaPermisos = permisoBean.getPermisos();
            response = new ResponseDto<List<UsuarioResponseDto>>();
            response.setData(listaPermisos);

        } catch (AgileSysException e) {
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
    public ResponseDto postPermisos(PermisoRequestDto permisoRequestDto) {

        ResponseDto response;

        try {
            PermisoPostResponseDto permisoResponseDto = permisoBean.postPermiso(permisoRequestDto);
            response = new ResponseDto<PermisoPostResponseDto>();
            response.setData(permisoResponseDto);

        } catch (AgileSysException e) {
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
    @Path("{idPermiso}")
    public ResponseDto updateUsuario(@PathParam("idPermiso") Integer idPermiso, PermisoRequestDto permisoRequestDto) {

        ResponseDto response;
        MessageDto messageDto;

        try {
            messageDto = permisoBean.updatePermiso(idPermiso, permisoRequestDto);
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
    @Path("{idPermiso}")
    public ResponseDto deleteUsuario(@PathParam("idPermiso") Integer idPermiso) {

        ResponseDto response;
        MessageDto message;

        try {
            message = permisoBean.deletePermiso(idPermiso);
            response = new ResponseDto<UsuarioPostResponseDto>();
            response.setData(message);

        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            message = new MessageDto();
            message.setMessage(e.getDescripcion());
            response.setError(message);
        }

        return response;
    }
    
    
}
