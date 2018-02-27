/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Submenu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.MenuRol;
import com.neptunosg.entity.Menu;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class SubmenuFacade extends AbstractFacade<Submenu> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubmenuFacade() {
        super(Submenu.class);
    }

    public boolean isMenuRolListEmpty(Submenu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Submenu> submenu = cq.from(Submenu.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(submenu, entity), cb.isNotEmpty(submenu.get("menuRolList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<MenuRol> findMenuRolList(Submenu entity) {
        Submenu mergedEntity = this.getMergedEntity(entity);
        List<MenuRol> menuRolList = mergedEntity.getMenuRolList();
        menuRolList.size();
        return menuRolList;
    }

    public boolean isIdemenEmpty(Submenu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Submenu> submenu = cq.from(Submenu.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(submenu, entity), cb.isNotNull(submenu.get("idemen")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Menu findIdemen(Submenu entity) {
        return this.getMergedEntity(entity).getIdemen();
    }
    
}
