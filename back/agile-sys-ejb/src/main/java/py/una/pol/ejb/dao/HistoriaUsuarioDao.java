package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import py.una.pol.ejb.model.HistoriaUsuario;



@Stateless
public class HistoriaUsuarioDao extends GenericDao<HistoriaUsuario> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaUsuarioDao() {
        super(HistoriaUsuario.class);
    }

    public HistoriaUsuario findByIdHistoriaUsuario(Integer idHistoriaUsuario) {
        TypedQuery<HistoriaUsuario> query = em.createNamedQuery("HistoriaUsuario.findByIdHistoriaUsuario", HistoriaUsuario.class)
                .setParameter("idHistoriaUsuario", idHistoriaUsuario);
        List<HistoriaUsuario> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    public List<HistoriaUsuario> findByIdProyectoSprint(Integer idProyecto) {
        TypedQuery<HistoriaUsuario> query = em.createNamedQuery("HistoriaUsuario.findByIdProyectoSprint", HistoriaUsuario.class)
            .setParameter("idProyecto", idProyecto);
        List<HistoriaUsuario> results = query.getResultList();
        if (results.size() > 0)
            return results;
        else
            return null;
    }
}