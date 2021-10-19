package py.una.pol.ejb.bean;



import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;

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

  
}
