package py.una.pol.ejb.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.ProyectoDao;
import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.LoginDto;
import py.una.pol.ejb.dto.LoginResponseDto;
import py.una.pol.ejb.dto.ProyectoResponseDto;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.model.Usuarios;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.model.Proyectos;
import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class ProyectoBean {
    @EJB
    ProyectoDao proyectoDao;

    public ProyectoResponseDto enviarProyecto(Integer idProyecto) throws AgileSysException {
        ProyectoResponseDto response = null;
        Proyectos proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
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