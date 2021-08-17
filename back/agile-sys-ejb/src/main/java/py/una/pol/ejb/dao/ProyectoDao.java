package py.una.pol.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import py.una.pol.ejb.model.Proyecto;

@Stateless
public class ProyectoDao extends GenericDao<Proyecto> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoDao() {
        super(Proyecto.class);
    }

    public Proyecto findByProyecto(Integer idProyecto) {
        TypedQuery<Proyecto> query = em.createNamedQuery("Proyecto.findByIdProyecto", Proyecto.class)
                .setParameter("idProyecto", idProyecto);
        List<Proyecto> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }
}