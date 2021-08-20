package py.una.pol.web.rest;


import py.una.pol.ejb.bean.PermisoBean;
import py.una.pol.ejb.bean.RolPermisoBean;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/rolPermiso")
public class RolPermisoRest {
    @EJB
    RolPermisoBean rolPermisoBean;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseDto asignarPermisos(RolPermisoRequestDto rolPermisoRequestDto) {

        ResponseDto response;

        try {
            MessageDto rolpermiso = rolPermisoBean.asignarPermiso(rolPermisoRequestDto);
            response = new ResponseDto<MessageDto>();
            response.setData(rolpermiso);

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
    @Path("{idRol}/{idPermiso}")
    public ResponseDto deleteUsuario(@PathParam("idRol") Integer idRol, @PathParam("idPermiso") Integer idPermiso) {

        ResponseDto response;
        MessageDto message;

        try {
            message = rolPermisoBean.deleteRolPermiso(idRol, idPermiso);
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
