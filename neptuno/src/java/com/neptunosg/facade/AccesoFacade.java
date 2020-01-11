/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.facade;

import static com.neptunosg.controller.util.JsfUtil.encriptaDato;
import com.neptunosg.entity.Acceso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import com.neptunosg.entity.Rol;
import com.neptunosg.entity.Usuario;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author pipo0
 */
@Stateless
public class AccesoFacade extends AbstractFacade<Acceso> {

    @PersistenceContext(unitName = "neptunoPU")
    private EntityManager em;
    
    private static final Logger LOG = Logger.getLogger(AccesoFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccesoFacade() {
        super(Acceso.class);
    }

    public boolean isIderolEmpty(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acceso, entity), cb.isNotNull(acceso.get("iderol")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIderol(Acceso entity) {
        return this.getMergedEntity(entity).getIderol();
    }

    public boolean isIdeusrEmpty(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acceso, entity), cb.isNotNull(acceso.get("ideusr")));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Usuario findIdeusr(Acceso entity) {
        return this.getMergedEntity(entity).getIdeusr();
    }

    @Override
    public Acceso findWithParents(Acceso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Acceso> cq = cb.createQuery(Acceso.class);
        Root<Acceso> acceso = cq.from(Acceso.class);
        acceso.fetch("iderol", JoinType.LEFT);
        acceso.fetch("ideusr", JoinType.LEFT);
        cq.select(acceso).where(cb.equal(acceso, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
    public Acceso validarAcceso(Acceso objeto) {
        try {
            Integer emp = objeto.getIdeusr().getIdeofi().getIdeemp().getIdeemp();
            objeto = (Acceso) this.getEntityManager().createQuery("SELECT a FROM Acceso a WHERE a.nikacc = :nik AND a.pswacc = :psw AND a.estacc = :est")
                    .setParameter("nik", objeto.getNikacc())
                    .setParameter("psw", encriptaDato(objeto.getPswacc()))
                    .setParameter("est", 1)
                    .getSingleResult();
            if (objeto != null) {
                if (!Objects.equals(objeto.getIdeusr().getIdeofi().getIdeemp().getIdeemp(), emp)) {
                    objeto = null;
                }
            }
        } catch (HibernateException e) {
            objeto = null;
            LOG.log(Level.SEVERE, null, e);
        } catch (Exception e) {
            objeto = null;
            LOG.log(Level.SEVERE, null, e);
        }
        return objeto;
    }
    
}
