/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.neptunosg.entity.Rol_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.MenuRol;
import com.neptunosg.entity.Permisos;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public boolean isAccesoListEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.accesoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Acceso> findAccesoList(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        List<Acceso> accesoList = mergedEntity.getAccesoList();
        accesoList.size();
        return accesoList;
    }

    public boolean isMenuRolListEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.menuRolList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<MenuRol> findMenuRolList(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        List<MenuRol> menuRolList = mergedEntity.getMenuRolList();
        menuRolList.size();
        return menuRolList;
    }

    public boolean isIdeperEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotNull(rol.get(Rol_.ideper)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Permisos findIdeper(Rol entity) {
        return this.getMergedEntity(entity).getIdeper();
    }

    @Override
    public Rol findWithParents(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rol> cq = cb.createQuery(Rol.class);
        Root<Rol> rol = cq.from(Rol.class);
        rol.fetch(Rol_.ideper);
        cq.select(rol).where(cb.equal(rol, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
