/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.TipoEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.TipoEmpresa_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Empresa;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class TipoEmpresaFacade extends AbstractFacade<TipoEmpresa> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEmpresaFacade() {
        super(TipoEmpresa.class);
    }

    public boolean isEmpresaListEmpty(TipoEmpresa entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoEmpresa> tipoEmpresa = cq.from(TipoEmpresa.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoEmpresa, entity), cb.isNotEmpty(tipoEmpresa.get(TipoEmpresa_.empresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Empresa> findEmpresaList(TipoEmpresa entity) {
        TipoEmpresa mergedEntity = this.getMergedEntity(entity);
        List<Empresa> empresaList = mergedEntity.getEmpresaList();
        empresaList.size();
        return empresaList;
    }
    
}
