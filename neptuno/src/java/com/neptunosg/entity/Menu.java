/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "menu", catalog = "afsolu_neptunosg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByIdemen", query = "SELECT m FROM Menu m WHERE m.idemen = :idemen")
    , @NamedQuery(name = "Menu.findByNommen", query = "SELECT m FROM Menu m WHERE m.nommen = :nommen")
    , @NamedQuery(name = "Menu.findByIcomen", query = "SELECT m FROM Menu m WHERE m.icomen = :icomen")
    , @NamedQuery(name = "Menu.findByEstmen", query = "SELECT m FROM Menu m WHERE m.estmen = :estmen")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idemen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nommen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String icomen;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean estmen;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idemen", fetch = FetchType.LAZY)
    private Submenu submenu;

    public Menu() {
    }

    public Menu(Long idemen) {
        this.idemen = idemen;
    }

    public Menu(Long idemen, String nommen, String icomen, boolean estmen) {
        this.idemen = idemen;
        this.nommen = nommen;
        this.icomen = icomen;
        this.estmen = estmen;
    }

    public Long getIdemen() {
        return idemen;
    }

    public void setIdemen(Long idemen) {
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
        return "com.neptunosg.entity.Menu[ idemen=" + idemen + " ]";
    }
    
}
