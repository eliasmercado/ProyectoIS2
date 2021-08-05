package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.GestionProyectoResponseDto;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class GestionProyectoBean {
    @EJB
    UsuarioProyectoDao usuarioProyectoDao;

    public GestionProyectoResponseDto enviarGestionProyecto(Integer idProyecto, Integer idUsuario)
            throws AgileSysException {
        GestionProyectoResponseDto response = null;
        UsuarioProyecto usuarioProyecto = usuarioProyectoDao.findProyectoByIdUsuarioIdProyecto(idProyecto, idUsuario);

        if (usuarioProyecto != null) {
            if (usuarioProyecto.getIdProyecto().getIdEstados().getIdEstados() == 2)
                throw new AgileSysException(GenericMessage.PROYECTO_FINALIZADO);
            else if (usuarioProyecto.getIdProyecto().getIdEstados().getIdEstados() == 3)
                throw new AgileSysException(GenericMessage.PROYECTO_CANCELADO);

            if (!usuarioProyecto.getIdUsuario().getEstado())
                throw new AgileSysException(GenericMessage.USER_DISABLE);
            response = new GestionProyectoResponseDto();
            response.setIdRol(usuarioProyecto.getIdRol().getIdRol());
            response.setDescripcionRol(usuarioProyecto.getIdRol().getDescripcionRol());

        } else {
            throw new AgileSysException(GenericMessage.USER_PROYECTO_NOT_FOUND);
        }

        return response;
    }
}