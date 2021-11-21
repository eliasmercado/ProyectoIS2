package py.una.pol.ejb.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import py.una.pol.ejb.dao.ProyectoDao;
import py.una.pol.ejb.dao.UsuarioProyectoDao;
import py.una.pol.ejb.dto.ProyectoGenericDto;
import py.una.pol.ejb.dto.DtoProyecto.ProyectoPostResponseDto;
import py.una.pol.ejb.dto.DtoProyecto.ProyectoResponseDto;
import py.una.pol.ejb.dto.DtoProyecto.ProyectoUsuarioDto;
import py.una.pol.ejb.enums.GenericMessage;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.utils.DateHelper;
import py.una.pol.ejb.model.Estado;
import py.una.pol.ejb.model.Proyecto;
import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class ProyectoBean {

    @EJB
    ProyectoDao proyectoDao;
    @EJB
    UsuarioProyectoDao usuarioProyectoDao;

    public ProyectoGenericDto enviarProyecto(Integer idProyecto) throws AgileSysException {
        ProyectoGenericDto response = null;
        Proyecto proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
            if (proyecto.getIdEstado().getIdEstado() == 2)
                throw new AgileSysException(GenericMessage.PROYECTO_FINALIZADO);
            if (proyecto.getIdEstado().getIdEstado() == 3)
                throw new AgileSysException(GenericMessage.PROYECTO_CANCELADO);
            response = new ProyectoGenericDto();
            response.setNombre(proyecto.getNombreProyecto());
            response.setDescripcion(proyecto.getDescripcionProyecto());
            response.setFechaInicio(DateHelper.getDateISO8601(proyecto.getFechaInicio()));
        } else {
            throw new AgileSysException(GenericMessage.PROYECTO_NOT_FOUND);
        }

        return response;
    }

    public List<ProyectoResponseDto> getProyectos() throws AgileSysException {
        List<ProyectoResponseDto> response = new ArrayList<>();
        List<Proyecto> proyectos = proyectoDao.findAll();
        ProyectoResponseDto dto = null;

        System.out.println("Proyectos size: " + proyectos.size());
        if (proyectos != null) {
            for (Proyecto proyecto : proyectos) {
                if (!proyecto.getIdEstado().getDescripcionEstado().equals("Cancelado")) {
                    dto = new ProyectoResponseDto();
                    dto.setIdProyecto(proyecto.getIdProyecto());
                    dto.setNombre(proyecto.getNombreProyecto());
                    dto.setDescripcion(proyecto.getDescripcionProyecto());
                    dto.setFechaInicio(DateHelper.getDateISO8601(proyecto.getFechaInicio()));
                    if (proyecto.getFechaFin() != null)
                        dto.setFechaFin(DateHelper.getDateISO8601(proyecto.getFechaFin()));
                    dto.setEstado(proyecto.getIdEstado().getDescripcionEstado());
                    List<ProyectoUsuarioDto> usuarios = new ArrayList<>();
                    List<UsuarioProyecto> listUsuProy = usuarioProyectoDao
                            .findUsuariosByIdProyecto(proyecto.getIdProyecto());
                    System.out.println("listUsuProy size: " + listUsuProy.size());
                    if (listUsuProy != null) {
                        for (UsuarioProyecto user : listUsuProy) {
                            if (user.getIdUsuario().getEstado()) {
                                ProyectoUsuarioDto upDto = new ProyectoUsuarioDto();
                                upDto.setIdUsuarioProyecto(user.getIdUsuarioProyecto());
                                upDto.setIdUsuario(user.getIdUsuario().getIdUsuario());
                                upDto.setNombres(user.getIdUsuario().getNombres());
                                upDto.setApellidos(user.getIdUsuario().getApellidos());
                                usuarios.add(upDto);
                            }
                        }
                    }
                    dto.setUsuarios(usuarios);
                    response.add(dto);
                }
            }
        }
        return response;
    }

    public List<ProyectoResponseDto> enviarProyectosTerminados(Integer idUsuario) throws AgileSysException {
        List<ProyectoResponseDto> response = new ArrayList<>();
        List<UsuarioProyecto> proyectos = usuarioProyectoDao.findProyectoTerminadoByIdUsuario(idUsuario);
        ProyectoResponseDto dto = null;

        if (proyectos != null) {
            for (UsuarioProyecto proyecto : proyectos) {

                dto = new ProyectoResponseDto();
                dto.setIdProyecto(proyecto.getIdProyecto().getIdProyecto());
                dto.setNombre(proyecto.getIdProyecto().getNombreProyecto());
                dto.setDescripcion(proyecto.getIdProyecto().getDescripcionProyecto());
                if (proyecto.getIdProyecto().getFechaFin() != null)
                    dto.setFechaFin(DateHelper.getDateISO8601(proyecto.getIdProyecto().getFechaFin()));
                response.add(dto);

            }
        }
        return response;
    }

    public ProyectoPostResponseDto postProyecto(ProyectoGenericDto proyectoGenericDto) throws AgileSysException {
        ProyectoPostResponseDto response = new ProyectoPostResponseDto();
        Proyecto proyecto = new Proyecto();

        try {
            proyecto = new Proyecto();
            proyecto.setDescripcionProyecto(proyectoGenericDto.getDescripcion());
            proyecto.setNombreProyecto(proyectoGenericDto.getNombre());
            proyecto.setFechaInicio(DateHelper.stringToDate(proyectoGenericDto.getFechaInicio()));
            proyecto.setIdEstado(new Estado(1));
            proyectoDao.create(proyecto);
            response.setIdProyecto(proyecto.getIdProyecto());
            response.setMessage(GenericMessage.USER_CREATED.getDescripcion());
        } catch (Exception e) {
            throw new AgileSysException(GenericMessage.PROJECT_NOT_CREATED, e.getMessage());
        }
        return response;
    }

    public ProyectoPostResponseDto updateProyecto(Integer idProyecto, ProyectoGenericDto proyectoDto)
            throws AgileSysException {
        ProyectoPostResponseDto response = new ProyectoPostResponseDto();
        Proyecto proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
            try {
                proyecto.setDescripcionProyecto(proyectoDto.getDescripcion());
                proyecto.setNombreProyecto(proyectoDto.getNombre());
                proyecto.setFechaInicio(DateHelper.stringToDate(proyectoDto.getFechaInicio()));
                proyectoDao.edit(proyecto);
                response.setMessage(GenericMessage.PROJECT_UPDATED.getDescripcion());
                return response;
            } catch (Exception e) {
                throw new AgileSysException(GenericMessage.PROJECT_NOT_UPDATED, e.getMessage());
            }
        } else
            throw new AgileSysException(GenericMessage.PROYECTO_NOT_FOUND);
    }

    public ProyectoPostResponseDto deleteProyecto(Integer idProyecto) throws AgileSysException {
        ProyectoPostResponseDto response = new ProyectoPostResponseDto();
        Proyecto proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
            try {
                proyecto.setIdEstado(new Estado(3));
                proyectoDao.edit(proyecto);
                response.setMessage(GenericMessage.PROJECT_DELETED.getDescripcion());
                return response;
            } catch (Exception e) {
                throw new AgileSysException(GenericMessage.PROJECT_NOT_DELETED, e.getMessage());
            }

        } else
            throw new AgileSysException(GenericMessage.PROYECTO_NOT_FOUND);
    }

    public ProyectoPostResponseDto finalizarProyecto(Integer idProyecto, ProyectoGenericDto proyectoDto)
            throws AgileSysException {
        ProyectoPostResponseDto response = new ProyectoPostResponseDto();
        Proyecto proyecto = proyectoDao.findByProyecto(idProyecto);

        if (proyecto != null) {
            try {
                proyecto.setFechaFin(DateHelper.stringToDate(proyectoDto.getFechaFin()));
                proyecto.setIdEstado(new Estado(2));
                proyectoDao.edit(proyecto);
                response.setMessage("Proyecto finalizado con exito");
                return response;
            } catch (Exception e) {
                throw new AgileSysException(GenericMessage.PROJECT_NOT_UPDATED, e.getMessage());
            }
        } else
            throw new AgileSysException(GenericMessage.PROYECTO_NOT_FOUND);
    }

}