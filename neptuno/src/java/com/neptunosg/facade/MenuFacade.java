/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Menu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Menu_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Submenu;

/**
 *
 * @author pipo0
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    public boolean isSubmenuEmpty(Menu entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Menu> menu = cq.from(Menu.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(menu, entity), cb.isNotNull(menu.get(Menu_.submenu)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Submenu findSubmenu(Menu entity) {
        return this.getMergedEntity(entity).getSubmenu();
    }
    
}
