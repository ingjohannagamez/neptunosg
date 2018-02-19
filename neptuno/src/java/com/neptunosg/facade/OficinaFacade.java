/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Oficina;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Oficina_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Empresa;
import com.neptunosg.entity.Municipio;
import com.neptunosg.entity.Usuario;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class OficinaFacade extends AbstractFacade<Oficina> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OficinaFacade() {
        super(Oficina.class);
    }

    public boolean isIdeempEmpty(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(oficina, entity), cb.isNotNull(oficina.get(Oficina_.ideemp)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empresa findIdeemp(Oficina entity) {
        return this.getMergedEntity(entity).getIdeemp();
    }

    public boolean isIdemunEmpty(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(oficina, entity), cb.isNotNull(oficina.get(Oficina_.idemun)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Municipio findIdemun(Oficina entity) {
        return this.getMergedEntity(entity).getIdemun();
    }

    public boolean isUsuarioListEmpty(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(oficina, entity), cb.isNotEmpty(oficina.get(Oficina_.usuarioList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Usuario> findUsuarioList(Oficina entity) {
        Oficina mergedEntity = this.getMergedEntity(entity);
        List<Usuario> usuarioList = mergedEntity.getUsuarioList();
        usuarioList.size();
        return usuarioList;
    }

    @Override
    public Oficina findWithParents(Oficina entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Oficina> cq = cb.createQuery(Oficina.class);
        Root<Oficina> oficina = cq.from(Oficina.class);
        oficina.fetch(Oficina_.ideemp, JoinType.LEFT);
        oficina.fetch(Oficina_.idemun, JoinType.LEFT);
        cq.select(oficina).where(cb.equal(oficina, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
