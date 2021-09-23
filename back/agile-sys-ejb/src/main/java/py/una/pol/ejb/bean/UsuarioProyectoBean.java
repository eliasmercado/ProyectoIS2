package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.MessageUsuarioProyectoDto;
import py.una.pol.ejb.model.Proyecto;
import py.una.pol.ejb.model.UsuarioProyecto;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dao.ProyectoDao;
import py.una.pol.ejb.dto.UsuarioProyectoRequestDto;
import py.una.pol.ejb.dto.MessageDto;
import py.una.pol.ejb.model.*;

@Stateless
public class UsuarioProyectoBean {
    @EJB
    UsuarioProyectoDao usuarioProyectoDao;
    @EJB
    UsuarioDao usuarioDao;
    @EJB
    ProyectoDao proyectoDao;
    
    public MessageUsuarioProyectoDto asignarUsuarioProyecto(UsuarioProyectoRequestDto usuarioProyectoRequest) throws AgileSysException {
        MessageUsuarioProyectoDto mensaje;
        UsuarioProyecto usuarioProyecto = usuarioProyectoDao.findProyectoByIdUsuarioIdProyecto(
            usuarioProyectoRequest.getIdProyecto(), usuarioProyectoRequest.getIdUsuario()
        );
        if (usuarioProyecto == null) {
            try {
                Usuario usuarioAsignar = usuarioDao.find(usuarioProyectoRequest.getIdUsuario());
                Proyecto proyectoAsignar = proyectoDao.find(usuarioProyectoRequest.getIdProyecto());
                if (usuarioAsignar == null)
                    throw new AgileSysException("El usuario no existe");
                if (proyectoAsignar == null)
                    throw new AgileSysException("El proyecto no existe");
                usuarioProyecto = new UsuarioProyecto();
                usuarioProyecto.setIdUsuario(usuarioAsignar);
                usuarioProyecto.setIdProyecto(proyectoAsignar);
                usuarioProyectoDao.create(usuarioProyecto);
                mensaje = new MessageUsuarioProyectoDto();
                mensaje.setIdUsuarioProyecto(
                    usuarioProyectoDao.findProyectoByIdUsuarioIdProyecto(
                        usuarioProyectoRequest.getIdProyecto(), usuarioProyectoRequest.getIdUsuario()
                    ).getIdUsuarioProyecto()
                );
                mensaje.setMensaje("Usuario asignado con exito");
            } catch (Exception e) {
                throw new AgileSysException("El usuario ya fue asignado");
            }
        } else {
            throw new AgileSysException("El usuario ya fue asignado");
        }
        return mensaje;
    }

    public MessageDto deleteUsuarioProyecto(Integer idUsuarioProyecto) throws AgileSysException {
        MessageDto response = new MessageDto();
        UsuarioProyecto usuarioProyecto = usuarioProyectoDao.find(idUsuarioProyecto);
        if (usuarioProyecto != null) {
            usuarioProyectoDao.remove(usuarioProyecto);
            response.setMessage("Usuario eliminado con exito del proyecto");
        } else {
            throw new AgileSysException("No se pudo eliminar al usuario, no existe la relacion");
        }
        return response;
    }
}
