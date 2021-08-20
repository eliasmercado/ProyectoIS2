package py.una.pol.web.rest;


import py.una.pol.ejb.bean.UsuarioBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/usuario")
public class UsuarioRest {
    @EJB
    UsuarioBean usuarioBean;

    @GET
    @Produces("application/json")
    public ResponseDto getUsuarios() {

        ResponseDto response;

        try {
            List<UsuarioResponseDto> listaUsuarios = usuarioBean.getUsuarios();
            response = new ResponseDto<List<UsuarioResponseDto>>();
            response.setData(listaUsuarios);

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
    public ResponseDto postUsuario(UsuarioRequestDto usuarioRequestDto) {

        ResponseDto response;

        try {
            UsuarioPostResponseDto usuarioPostResponseDto = usuarioBean.postUsuario(usuarioRequestDto);
            response = new ResponseDto<UsuarioPostResponseDto>();
            response.setData(usuarioPostResponseDto);


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
    @Path("{idUsuario}")
    public ResponseDto updateUsuario(@PathParam("idUsuario") Integer idUsuario, UsuarioRequestDto usuarioRequestDto) {

        ResponseDto response;
        MessageDto messageDto;

        try {
            messageDto = usuarioBean.updateUsuario(idUsuario, usuarioRequestDto);
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
    @Path("{idUsuario}")
    public ResponseDto deleteUsuario(@PathParam("idUsuario") Integer idUsuario) {

        ResponseDto response;
        MessageDto message;

        try {
            message = usuarioBean.deleteUsuario(idUsuario);
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
