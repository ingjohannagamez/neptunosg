/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Pais;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Pais_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Continente;
import com.neptunosg.entity.Region;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }

    public boolean isIdeconEmpty(Pais entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Pais> pais = cq.from(Pais.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pais, entity), cb.isNotNull(pais.get(Pais_.idecon)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Continente findIdecon(Pais entity) {
        return this.getMergedEntity(entity).getIdecon();
    }

    public boolean isRegionListEmpty(Pais entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Pais> pais = cq.from(Pais.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pais, entity), cb.isNotEmpty(pais.get(Pais_.regionList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Region> findRegionList(Pais entity) {
        Pais mergedEntity = this.getMergedEntity(entity);
        List<Region> regionList = mergedEntity.getRegionList();
        regionList.size();
        return regionList;
    }

    @Override
    public Pais findWithParents(Pais entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pais> cq = cb.createQuery(Pais.class);
        Root<Pais> pais = cq.from(Pais.class);
        pais.fetch(Pais_.idecon, JoinType.LEFT);
        cq.select(pais).where(cb.equal(pais, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
