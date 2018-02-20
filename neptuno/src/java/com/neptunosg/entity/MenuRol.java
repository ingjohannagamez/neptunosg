package com.neptunosg.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "menu_rol")
public class MenuRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idemro;
    @JoinColumn(name = "iderol", referencedColumnName = "iderol", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol iderol;
    @JoinColumn(name = "idesub", referencedColumnName = "idesub", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Submenu idesub;

    public MenuRol() {
    }

    public MenuRol(Long idemro) {
        this.idemro = idemro;
    }

    public Long getIdemro() {
        return idemro;
    }

    public void setIdemro(Long idemro) {
        this.idemro = idemro;
    }

    public Rol getIderol() {
        return iderol;
    }

    public void setIderol(Rol iderol) {
        this.iderol = iderol;
    }

    public Submenu getIdesub() {
        return idesub;
    }

    public void setIdesub(Submenu idesub) {
        this.idesub = idesub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemro != null ? idemro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuRol)) {
            return false;
        }
        MenuRol other = (MenuRol) object;
        if ((this.idemro == null && other.idemro != null) || (this.idemro != null && !this.idemro.equals(other.idemro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.MenuRol[ idemro=" + idemro + " ]";
    }
    
}
