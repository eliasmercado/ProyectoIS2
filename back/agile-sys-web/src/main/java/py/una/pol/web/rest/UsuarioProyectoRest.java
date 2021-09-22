package py.una.pol.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import py.una.pol.ejb.bean.UsuarioProyectoBean;
import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.ResponseDto;
import py.una.pol.ejb.dto.UsuarioProyectoRequestDto;
import py.una.pol.ejb.dto.MessageDto;
import py.una.pol.ejb.model.UsuarioProyecto;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

@Path("/usuario-proyecto")
public class UsuarioProyectoRest {
    @EJB
    UsuarioProyectoBean usuarioProyectoBean;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseDto asignarUsuario(UsuarioProyectoRequestDto usuarioProyectoRequest) {
        ResponseDto response;
        try {
            MessageUsuarioProyectoDto usuarioProyecto = usuarioProyectoBean.asignarUsuarioProyecto(usuarioProyectoRequest);
            response = new ResponseDto<MessageUsuarioProyectoDto>();
            response.setData(usuarioProyecto);
        } catch (AgileSysException e) {
            response = new ResponseDto<>();
            MessageDto msg = new MessageDto();
            msg.setMessage(e.getDescripcion());
            response.setError(msg);
        }
        return response;
    }

    @DELETE
    @Produces("application/json")
    @Path("{idUsuarioProyecto}")
    public ResponseDto eliminarUsuario(@PathParam("idUsuarioProyecto") Integer idUsuarioProyecto) {
        ResponseDto response;
        MessageDto message;
        try {
            message = usuarioProyectoBean.deleteUsuarioProyecto(idUsuarioProyecto);
            response = new ResponseDto<MessageDto>();
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
