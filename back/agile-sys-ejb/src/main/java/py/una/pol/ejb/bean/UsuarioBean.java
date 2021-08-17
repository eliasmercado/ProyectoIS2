package py.una.pol.ejb.bean;


import py.una.pol.ejb.dao.UsuarioDao;
import py.una.pol.ejb.dto.*;
import py.una.pol.ejb.enums.GenericMessage;

import py.una.pol.ejb.model.Rol;
import py.una.pol.ejb.model.Usuario;
import py.una.pol.ejb.utils.AgileSysException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class UsuarioBean {
    @EJB
    UsuarioDao usuarioDao;

    public List<UsuarioResponseDto> getUsuarios() throws AgileSysException {
        List<UsuarioResponseDto> response = new ArrayList<>();
        List<Usuario> usuarios = usuarioDao.findUsuarios();

        if (usuarios != null) {
            for (Usuario usuario : usuarios){
               if(usuario.getEstado()){
                   UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
                   usuarioResponseDto.setUsuario(usuario.getUsuario());
                   usuarioResponseDto.setIdUsuario(usuario.getIdUsuario());
                   usuarioResponseDto.setNombres(usuario.getNombres());
                   usuarioResponseDto.setApellidos(usuario.getApellidos());
                   usuarioResponseDto.setEmail(usuario.getEmail());
                   if(usuario.getIdRol() == null)
                       usuarioResponseDto.setRol(null);
                   else
                       usuarioResponseDto.setRol(new RolDto(usuario.getIdRol().getIdRol(), usuario.getIdRol().getDescripcionRol()));
                   usuarioResponseDto.setTelefono(usuario.getTelefono());
                   response.add(usuarioResponseDto);
               }
            }

        } else {
            throw new AgileSysException(GenericMessage.USERS_LIST_EMPTY);
        }

        return response;
    }

    public UsuarioPostResponseDto postUsuario(UsuarioRequestDto usuarioRequestDto) throws AgileSysException {
        UsuarioPostResponseDto response = new UsuarioPostResponseDto();
        Usuario usuario = usuarioDao.findByUsuario(usuarioRequestDto.getUsuario());


        if(usuario == null){
            try{
                Usuario usuarioNuevo = new Usuario();
                usuarioNuevo.setUsuario(usuarioRequestDto.getUsuario());
                usuarioNuevo.setNombres(usuarioRequestDto.getNombres());
                usuarioNuevo.setApellidos(usuarioRequestDto.getApellidos());
                usuarioNuevo.setEmail(usuarioRequestDto.getEmail());
                usuarioNuevo.setTelefono(usuarioRequestDto.getTelefono());
                usuarioNuevo.setIdRol(new Rol(usuarioRequestDto.getIdRol()));
                usuarioNuevo.setFechaCreacion(new Date());
                usuarioNuevo.setPassword("123456");
                usuarioNuevo.setEstado(true);
                usuarioDao.create(usuarioNuevo);
                response.setIdUsuario(usuarioNuevo.getIdUsuario());
                response.setMessage(GenericMessage.USER_CREATED.getDescripcion());

            }catch (Exception e){
                throw new AgileSysException(GenericMessage.USER_NOT_CREATED);
            }
        }else{
            throw new AgileSysException(GenericMessage.USER_ALREADY_EXISTS);
        }

        return response;
    }
    public MessageDto updateUsuario(Integer idUduario, UsuarioRequestDto usuarioRequestDto) throws AgileSysException {
        MessageDto response = new MessageDto();
        Usuario usuario = usuarioDao.findByIDUsuario(idUduario);
        if(usuario != null){
           try{
               usuario.setUsuario(usuarioRequestDto.getUsuario());
               usuario.setNombres(usuarioRequestDto.getNombres());
               usuario.setApellidos(usuarioRequestDto.getApellidos());
               usuario.setEmail(usuarioRequestDto.getEmail());
               usuario.setTelefono(usuarioRequestDto.getTelefono());
               usuario.setIdRol(new Rol(usuarioRequestDto.getIdRol()));
               usuarioDao.edit(usuario);
               response.setMessage(GenericMessage.USER_UPDATED.getDescripcion());
           }catch (Exception e){
               throw new AgileSysException(GenericMessage.USER_NOT_UPDATED);
           }

        }else{
            throw new AgileSysException(GenericMessage.USER_NOT_FOUND);
        }


        return response;
    }

    public MessageDto deleteUsuario(Integer idUduario) throws AgileSysException {
        MessageDto response = new MessageDto();
        Usuario usuario = usuarioDao.findByIDUsuario(idUduario);
        if(usuario != null){

            try{
               usuario.setFechaBaja(new Date());
               usuario.setEstado(false);
               usuarioDao.edit(usuario);
            }catch (Exception e){
                throw new AgileSysException(GenericMessage.USER_NOT_DELETED);
            }

        }else{
            throw new AgileSysException(GenericMessage.USER_NOT_FOUND);
        }
        response.setMessage(GenericMessage.USER_DELETED.getDescripcion());

        return response;
    }

}