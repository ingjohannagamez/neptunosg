/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.MenuRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Rol;
import com.neptunosg.entity.Submenu;

/**
 *
 * @author pipo0
 */
@Stateless
public class MenuRolFacade extends AbstractFacade<MenuRol> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuRolFacade() {
        super(MenuRol.class);
    }

    public boolean isIderolEmpty(MenuRol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MenuRol> menuRol = cq.from(MenuRol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(menuRol, entity), cb.isNotNull(menuRol.get("iderol")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIderol(MenuRol entity) {
        return this.getMergedEntity(entity).getIderol();
    }

    public boolean isIdesubEmpty(MenuRol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<MenuRol> menuRol = cq.from(MenuRol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(menuRol, entity), cb.isNotNull(menuRol.get("idesub")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Submenu findIdesub(MenuRol entity) {
        return this.getMergedEntity(entity).getIdesub();
    }

    @Override
    public MenuRol findWithParents(MenuRol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MenuRol> cq = cb.createQuery(MenuRol.class);
        Root<MenuRol> menuRol = cq.from(MenuRol.class);
        menuRol.fetch("iderol");
        menuRol.fetch("idesub");
        cq.select(menuRol).where(cb.equal(menuRol, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
