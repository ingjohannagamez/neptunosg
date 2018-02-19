/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.TipoRegimen;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.TipoRegimen_;
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
public class TipoRegimenFacade extends AbstractFacade<TipoRegimen> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRegimenFacade() {
        super(TipoRegimen.class);
    }

    public boolean isEmpresaListEmpty(TipoRegimen entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoRegimen> tipoRegimen = cq.from(TipoRegimen.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoRegimen, entity), cb.isNotEmpty(tipoRegimen.get(TipoRegimen_.empresaList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Empresa> findEmpresaList(TipoRegimen entity) {
        TipoRegimen mergedEntity = this.getMergedEntity(entity);
        List<Empresa> empresaList = mergedEntity.getEmpresaList();
        empresaList.size();
        return empresaList;
    }
    
}
