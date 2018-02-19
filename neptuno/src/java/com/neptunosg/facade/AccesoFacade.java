/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Acceso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Acceso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Rol;
import com.neptunosg.entity.Usuario;

/**
 *
 * @author pipo0
 */
@Stateless
public class AccesoFacade extends AbstractFacade<Acceso> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccesoFacade() {
        super(Acceso.class);
    }

    public boolean isIderolEmpty(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acceso, entity), cb.isNotNull(acceso.get(Acceso_.iderol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIderol(Acceso entity) {
        return this.getMergedEntity(entity).getIderol();
    }

    public boolean isIdeusrEmpty(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acceso, entity), cb.isNotNull(acceso.get(Acceso_.ideusr)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdeusr(Acceso entity) {
        return this.getMergedEntity(entity).getIdeusr();
    }

    @Override
    public Acceso findWithParents(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Acceso> cq = cb.createQuery(Acceso.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        acceso.fetch(Acceso_.iderol, JoinType.LEFT);
        acceso.fetch(Acceso_.ideusr, JoinType.LEFT);
        cq.select(acceso).where(cb.equal(acceso, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
