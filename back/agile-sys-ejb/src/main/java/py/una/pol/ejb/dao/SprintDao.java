package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.Sprint;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class SprintDao extends GenericDao<Sprint> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintDao() {
        super(Sprint.class);
    }
   
    public Sprint findByIdSprint(Integer idSprint) {
        TypedQuery<Sprint> query = em.createNamedQuery("Sprint.findByIdSprint", Sprint.class)
            .setParameter("idSprint", idSprint);
        List<Sprint> results = query.getResultList();
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }


}