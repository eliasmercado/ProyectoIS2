package py.una.pol.ejb.dao;

import py.una.pol.ejb.model.Fase;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class FaseDao extends GenericDao<Fase> {

    @PersistenceContext(unitName = "py.una.pol_agile-sys-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaseDao() {
        super(Fase.class);
    }
   

}