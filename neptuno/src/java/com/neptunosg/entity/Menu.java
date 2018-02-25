package com.neptunosg.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemen", nullable = false)
    private Integer idemen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nommen", nullable = false, length = 50)
    private String nommen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "icomen", nullable = false, length = 200)
    private String icomen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estmen", nullable = false)
    private boolean estmen;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idemen", fetch = FetchType.LAZY)
    private Submenu submenu;

    public Menu() {
    }

    public Menu(Integer idemen) {
        this.idemen = idemen;
    }

    public Menu(Integer idemen, String nommen, String icomen, boolean estmen) {
        this.idemen = idemen;
        this.nommen = nommen;
        this.icomen = icomen;
        this.estmen = estmen;
    }

    public Integer getIdemen() {
        return idemen;
    }

    public void setIdemen(Integer idemen) {
        this.idemen = idemen;
    }

    public String getNommen() {
        return nommen;
    }

    public void setNommen(String nommen) {
        this.nommen = nommen;
    }

    public String getIcomen() {
        return icomen;
    }

    public void setIcomen(String icomen) {
        this.icomen = icomen;
    }

    public boolean getEstmen() {
        return estmen;
    }

    public void setEstmen(boolean estmen) {
        this.estmen = estmen;
    }

    public Submenu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Submenu submenu) {
        this.submenu = submenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemen != null ? idemen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idemen == null && other.idemen != null) || (this.idemen != null && !this.idemen.equals(other.idemen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nommen;
    }
    
}
