package py.una.pol.ejb.bean;



import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;

import java.util.ArrayList;
import java.util.List;
import py.una.pol.ejb.model.Proyecto;

import py.una.pol.ejb.model.Sprint;
import py.una.pol.ejb.utils.AgileSysException;
import py.una.pol.ejb.utils.DateHelper;
import py.una.pol.ejb.dao.SprintDao;


import javax.ejb.EJB;
import javax.ejb.Stateless;



@Stateless
public class SprintBean {
    @EJB
    SprintDao sprintDao;

    @EJB
    HistoriaUsuarioBean historiaUsuarioBean;

    public List<SprintGetResponseDto> getSprints() throws AgileSysException {
        List<Sprint> sprints = sprintDao.findAll();
        List<SprintGetResponseDto> sprintsResponse = new ArrayList<>();
        if (sprints != null) {
            for (Sprint sprint : sprints) {
                SprintGetResponseDto sprintGetResponseDto = new SprintGetResponseDto();
                sprintGetResponseDto.setIdSprint(sprint.getIdSprint());   
                sprintGetResponseDto.setNombre(sprint.getNombreSprint());
                sprintGetResponseDto.setDescripcion(sprint.getDescripcion());
                sprintGetResponseDto.setFechaInicio(sprint.formatDateISO(sprint.getFechaInicio()));
                sprintGetResponseDto.setFechaFin(sprint.formatDateISO(sprint.getFechaFin()));
                sprintGetResponseDto.setIdProyecto(sprint.getIdProyecto().getIdProyecto());
                sprintGetResponseDto.setHistoriasUsuario(
                    historiaUsuarioBean.getHistoriaUsuarioProyectoAndSprint(
                        sprint.getIdProyecto().getIdProyecto(), sprint.getIdSprint()
                    )
                );

                if(sprint.getCompletado() != null)
                    sprintGetResponseDto.setCompletado(sprint.getCompletado());
                else
                    sprintGetResponseDto.setCompletado(false);

                sprintsResponse.add(sprintGetResponseDto);
            }
        } else {
            throw new AgileSysException("No existen sprints");
        }
        return sprintsResponse;
    }

    public SprintResponseDto createSprint(SprintRequestDto sprintRequestDto) throws AgileSysException {
        SprintResponseDto response = new SprintResponseDto();
        try {
            Sprint sprint = new Sprint();
            sprint.setDescripcion(sprintRequestDto.getDescripcion());
            sprint.setNombreSprint(sprintRequestDto.getNombre());
            sprint.setFechaInicio(DateHelper.stringToDate(sprintRequestDto.getFechaInicio()));
            sprint.setFechaFin(DateHelper.stringToDate(sprintRequestDto.getFechaFin()));
            sprint.setIdProyecto(new Proyecto(sprintRequestDto.getIdProyecto()));
            sprintDao.create(sprint);
            response.setIdSprint(sprint.getIdSprint());
            response.setMessage("Sprint creado con éxito.");
        } catch (Exception e) {
            throw new AgileSysException(GenericMessage.SPRINT_NOT_CREATED);
        }
        return response;
    }

    public MessageDto updateSprint(Integer idSprint, SprintRequestDto sprintRequestDto) throws AgileSysException {
        MessageDto response = new MessageDto();
        Sprint sprint = sprintDao.findByIdSprint(idSprint);

        if (sprint != null) {
            try {
                sprint.setDescripcion(sprintRequestDto.getDescripcion());
                sprint.setNombreSprint(sprintRequestDto.getNombre());
                sprint.setFechaInicio(DateHelper.stringToDate(sprintRequestDto.getFechaInicio()));
                sprint.setFechaFin(DateHelper.stringToDate(sprintRequestDto.getFechaFin()));
                sprint.setCompletado(sprintRequestDto.getCompletado());
                sprintDao.edit(sprint);
                response.setMessage("Sprint modificado con éxito");
            } catch (Exception e) {
                throw new AgileSysException(GenericMessage.SPRINT_NOT_UPDATED);
            }
        } else {
            throw new AgileSysException(GenericMessage.SPRINT_NOT_FOUND);
        }
        return response;
    }

    public MessageDto deleteSprint(int idSprint) throws AgileSysException {
        MessageDto response = new MessageDto();
        Sprint sprint = sprintDao.findByIdSprint(idSprint);
        if (sprint != null) {
            try {
                sprintDao.remove(sprint);
                response.setMessage("Sprint eliminado con exito");
            } catch (Exception e) {
                throw new AgileSysException("No se puede eliminar el sprint: " + e.getMessage());
            }
        } else {
            throw new AgileSysException("No se encontro el sprint");
        }
        return response;
    }
}
