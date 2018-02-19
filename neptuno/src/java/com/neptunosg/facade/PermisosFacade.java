/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Permisos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Permisos_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Rol;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class PermisosFacade extends AbstractFacade<Permisos> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisosFacade() {
        super(Permisos.class);
    }

    public boolean isRolListEmpty(Permisos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Permisos> permisos = cq.from(Permisos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(permisos, entity), cb.isNotEmpty(permisos.get(Permisos_.rolList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Rol> findRolList(Permisos entity) {
        Permisos mergedEntity = this.getMergedEntity(entity);
        List<Rol> rolList = mergedEntity.getRolList();
        rolList.size();
        return rolList;
    }
    
}
