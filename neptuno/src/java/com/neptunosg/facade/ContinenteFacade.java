/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Continente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Pais;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class ContinenteFacade extends AbstractFacade<Continente> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContinenteFacade() {
        super(Continente.class);
    }

    public boolean isPaisListEmpty(Continente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Continente> continente = cq.from(Continente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(continente, entity), cb.isNotEmpty(continente.get("paisList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Pais> findPaisList(Continente entity) {
        Continente mergedEntity = this.getMergedEntity(entity);
        List<Pais> paisList = mergedEntity.getPaisList();
        paisList.size();
        return paisList;
    }
    
}
