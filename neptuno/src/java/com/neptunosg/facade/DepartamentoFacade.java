/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Departamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Municipio;
import com.neptunosg.entity.Region;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public boolean isMunicipioListEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get("municipioList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Municipio> findMunicipioList(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        List<Municipio> municipioList = mergedEntity.getMunicipioList();
        municipioList.size();
        return municipioList;
    }

    public boolean isIderegEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotNull(departamento.get("idereg")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Region findIdereg(Departamento entity) {
        return this.getMergedEntity(entity).getIdereg();
    }

    @Override
    public Departamento findWithParents(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Departamento> cq = cb.createQuery(Departamento.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        departamento.fetch("idereg", JoinType.LEFT);
        cq.select(departamento).where(cb.equal(departamento, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
