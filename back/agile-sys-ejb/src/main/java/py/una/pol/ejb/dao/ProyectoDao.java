package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import py.una.pol.ejb.model.Proyectos;

@Stateless
public class ProyectoDao extends GenericDao<Proyectos> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoDao() {
        super(Proyectos.class);
    }

    public Proyectos findByProyecto(Integer idProyecto) {
        TypedQuery<Proyectos> query = em.createNamedQuery("Proyectos.findByIdProyecto", Proyectos.class)
                .setParameter("idProyecto", idProyecto);
        List<Proyectos> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}