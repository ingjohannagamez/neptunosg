/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Region;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Region_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Departamento;
import com.neptunosg.entity.Pais;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class RegionFacade extends AbstractFacade<Region> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }

    public boolean isDepartamentoListEmpty(Region entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Region> region = cq.from(Region.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(region, entity), cb.isNotEmpty(region.get(Region_.departamentoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Departamento> findDepartamentoList(Region entity) {
        Region mergedEntity = this.getMergedEntity(entity);
        List<Departamento> departamentoList = mergedEntity.getDepartamentoList();
        departamentoList.size();
        return departamentoList;
    }

    public boolean isIdepaiEmpty(Region entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Region> region = cq.from(Region.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(region, entity), cb.isNotNull(region.get(Region_.idepai)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Pais findIdepai(Region entity) {
        return this.getMergedEntity(entity).getIdepai();
    }

    @Override
    public Region findWithParents(Region entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Region> cq = cb.createQuery(Region.class);
        Root<Region> region = cq.from(Region.class);
        region.fetch(Region_.idepai, JoinType.LEFT);
        cq.select(region).where(cb.equal(region, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
