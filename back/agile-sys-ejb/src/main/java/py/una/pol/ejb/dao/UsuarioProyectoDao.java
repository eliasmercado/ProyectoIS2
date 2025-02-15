package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import py.una.pol.ejb.model.UsuarioProyecto;

@Stateless
public class UsuarioProyectoDao extends GenericDao<UsuarioProyecto> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioProyectoDao() {
        super(UsuarioProyecto.class);
    }

    public UsuarioProyecto findProyectoIniciadoByIdUsuario(Integer idUsuario) {
        TypedQuery<UsuarioProyecto> query = em
                .createNamedQuery("UsuarioProyecto.findProyectoIniciadoByIdUsuario", UsuarioProyecto.class)
                .setParameter("idUsuario", idUsuario);
        List<UsuarioProyecto> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public List<UsuarioProyecto> findProyectoTerminadoByIdUsuario(Integer idUsuario) {
        TypedQuery<UsuarioProyecto> query = em
                .createNamedQuery("UsuarioProyecto.findProyectoTerminadoByIdUsuario", UsuarioProyecto.class)
                .setParameter("idUsuario", idUsuario);
        List<UsuarioProyecto> results = query.getResultList();
        if (results.size() > 0)
            return results;
        else
            return null;
    }

    public UsuarioProyecto findProyectoByIdUsuarioIdProyecto(Integer idProyecto, Integer idUsuario) {
        TypedQuery<UsuarioProyecto> query = em
                .createNamedQuery("UsuarioProyecto.findProyectoByIdUsuarioIdProyecto", UsuarioProyecto.class)
                .setParameter("idUsuario", idUsuario).setParameter("idProyecto", idProyecto);
        List<UsuarioProyecto> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public List<UsuarioProyecto> findUsuariosByIdProyecto(Integer idProyecto) {
        TypedQuery<UsuarioProyecto> query = em
                .createNamedQuery("UsuarioProyecto.findUsuariosByIdProyecto", UsuarioProyecto.class)
                .setParameter("idProyecto", idProyecto);
        List<UsuarioProyecto> results = query.getResultList();
        return results;
    }
}
