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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "permisos")
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer ideper;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean lecper;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean escper;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean modper;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean eliper;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ideper", fetch = FetchType.LAZY)
    private List<Rol> rolList;

    public Permisos() {
    }

    public Permisos(Integer ideper) {
        this.ideper = ideper;
    }

    public Permisos(Integer ideper, boolean lecper, boolean escper, boolean modper, boolean eliper) {
        this.ideper = ideper;
        this.lecper = lecper;
        this.escper = escper;
        this.modper = modper;
        this.eliper = eliper;
    }

    public Integer getIdeper() {
        return ideper;
    }

    public void setIdeper(Integer ideper) {
        this.ideper = ideper;
    }

    public boolean getLecper() {
        return lecper;
    }

    public void setLecper(boolean lecper) {
        this.lecper = lecper;
    }

    public boolean getEscper() {
        return escper;
    }

    public void setEscper(boolean escper) {
        this.escper = escper;
    }

    public boolean getModper() {
        return modper;
    }

    public void setModper(boolean modper) {
        this.modper = modper;
    }

    public boolean getEliper() {
        return eliper;
    }

    public void setEliper(boolean eliper) {
        this.eliper = eliper;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideper != null ? ideper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.ideper == null && other.ideper != null) || (this.ideper != null && !this.ideper.equals(other.ideper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Permisos[ ideper=" + ideper + " ]";
    }
    
}
