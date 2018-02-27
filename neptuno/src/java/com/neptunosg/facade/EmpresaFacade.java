/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Empresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Oficina;
import com.neptunosg.entity.TipoEmpresa;
import com.neptunosg.entity.TipoRegimen;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class EmpresaFacade extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresaFacade() {
        super(Empresa.class);
    }

    public boolean isOficinaListEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotEmpty(empresa.get("oficinaList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Oficina> findOficinaList(Empresa entity) {
        Empresa mergedEntity = this.getMergedEntity(entity);
        List<Oficina> oficinaList = mergedEntity.getOficinaList();
        oficinaList.size();
        return oficinaList;
    }

    public boolean isIdetemEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotNull(empresa.get("idetem")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoEmpresa findIdetem(Empresa entity) {
        return this.getMergedEntity(entity).getIdetem();
    }

    public boolean isIdetreEmpty(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empresa, entity), cb.isNotNull(empresa.get("idetre")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoRegimen findIdetre(Empresa entity) {
        return this.getMergedEntity(entity).getIdetre();
    }

    @Override
    public Empresa findWithParents(Empresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empresa> cq = cb.createQuery(Empresa.class);
        Root<Empresa> empresa = cq.from(Empresa.class);
        empresa.fetch("idetem", JoinType.LEFT);
        empresa.fetch("idetre", JoinType.LEFT);
        cq.select(empresa).where(cb.equal(empresa, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
