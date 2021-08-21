package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.ProyectoDao;
import py.una.pol.ejb.dto.ProyectoResponseDto;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.model.Proyecto;

@Stateless
public class ProyectoBean {
    @EJB
    ProyectoDao proyectoDao;

    public ProyectoResponseDto enviarProyecto(Integer idProyecto) throws AgileSysException {
        ProyectoResponseDto response = null;
        Proyecto proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
            if (proyecto.getIdEstado().getIdEstado() == 2)
                throw new AgileSysException(GenericMessage.PROYECTO_FINALIZADO);
            if (proyecto.getIdEstado().getIdEstado() == 3)
                throw new AgileSysException(GenericMessage.PROYECTO_CANCELADO);
            response = new ProyectoResponseDto();
            response.setNombre(proyecto.getNombreProyecto());
            response.setDescripcion(proyecto.getDescripcionProyecto());
            response.setFechaInicio(proyecto.getFechaInicio());

        } else {
            throw new AgileSysException(GenericMessage.PROYECTO_NOT_FOUND);
        }

        return response;
    }
}