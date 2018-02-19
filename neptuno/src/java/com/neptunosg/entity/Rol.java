/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "rol", catalog = "afsolu_neptunosg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByIderol", query = "SELECT r FROM Rol r WHERE r.iderol = :iderol")
    , @NamedQuery(name = "Rol.findByNomrol", query = "SELECT r FROM Rol r WHERE r.nomrol = :nomrol")
    , @NamedQuery(name = "Rol.findByDesrol", query = "SELECT r FROM Rol r WHERE r.desrol = :desrol")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long iderol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String nomrol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String desrol;
    @OneToMany(mappedBy = "iderol", fetch = FetchType.LAZY)
    private List<Acceso> accesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iderol", fetch = FetchType.LAZY)
    private List<MenuRol> menuRolList;
    @JoinColumn(name = "ideper", referencedColumnName = "ideper", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permisos ideper;

    public Rol() {
    }

    public Rol(Long iderol) {
        this.iderol = iderol;
    }

    public Rol(Long iderol, String nomrol, String desrol) {
        this.iderol = iderol;
        this.nomrol = nomrol;
        this.desrol = desrol;
    }

    public Long getIderol() {
        return iderol;
    }

    public void setIderol(Long iderol) {
        this.iderol = iderol;
    }

    public String getNomrol() {
        return nomrol;
    }

    public void setNomrol(String nomrol) {
        this.nomrol = nomrol;
    }

    public String getDesrol() {
        return desrol;
    }

    public void setDesrol(String desrol) {
        this.desrol = desrol;
    }

    @XmlTransient
    public List<Acceso> getAccesoList() {
        return accesoList;
    }

    public void setAccesoList(List<Acceso> accesoList) {
        this.accesoList = accesoList;
    }

    @XmlTransient
    public List<MenuRol> getMenuRolList() {
        return menuRolList;
    }

    public void setMenuRolList(List<MenuRol> menuRolList) {
        this.menuRolList = menuRolList;
    }

    public Permisos getIdeper() {
        return ideper;
    }

    public void setIdeper(Permisos ideper) {
        this.ideper = ideper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iderol != null ? iderol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.iderol == null && other.iderol != null) || (this.iderol != null && !this.iderol.equals(other.iderol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Rol[ iderol=" + iderol + " ]";
    }
    
}
