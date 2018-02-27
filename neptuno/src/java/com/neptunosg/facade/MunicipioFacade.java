/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Municipio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Departamento;
import com.neptunosg.entity.Oficina;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class MunicipioFacade extends AbstractFacade<Municipio> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }

    public boolean isIdedepEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotNull(municipio.get("idedep")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findIdedep(Municipio entity) {
        return this.getMergedEntity(entity).getIdedep();
    }

    public boolean isOficinaListEmpty(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(municipio, entity), cb.isNotEmpty(municipio.get("oficinaList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Oficina> findOficinaList(Municipio entity) {
        Municipio mergedEntity = this.getMergedEntity(entity);
        List<Oficina> oficinaList = mergedEntity.getOficinaList();
        oficinaList.size();
        return oficinaList;
    }

    @Override
    public Municipio findWithParents(Municipio entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Municipio> cq = cb.createQuery(Municipio.class);
        Root<Municipio> municipio = cq.from(Municipio.class);
        municipio.fetch("idedep", JoinType.LEFT);
        cq.select(municipio).where(cb.equal(municipio, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
