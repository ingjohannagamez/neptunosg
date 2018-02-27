/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import com.neptunosg.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.Oficina;
import com.neptunosg.entity.TipoDocumento;
import java.util.List;

/**
 *
 * @author pipo0
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isAccesoListEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotEmpty(usuario.get("accesoList")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<Acceso> findAccesoList(Usuario entity) {
        Usuario mergedEntity = this.getMergedEntity(entity);
        List<Acceso> accesoList = mergedEntity.getAccesoList();
        accesoList.size();
        return accesoList;
    }

    public boolean isIdeofiEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get("ideofi")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Oficina findIdeofi(Usuario entity) {
        return this.getMergedEntity(entity).getIdeofi();
    }

    public boolean isIdetdoEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get("idetdo")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDocumento findIdetdo(Usuario entity) {
        return this.getMergedEntity(entity).getIdetdo();
    }

    @Override
    public Usuario findWithParents(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        usuario.fetch("ideofi", JoinType.LEFT);
        usuario.fetch("idetdo", JoinType.LEFT);
        cq.select(usuario).where(cb.equal(usuario, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
