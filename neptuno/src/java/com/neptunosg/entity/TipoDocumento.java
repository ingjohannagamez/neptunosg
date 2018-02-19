/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "tipo_documento", catalog = "afsolu_neptunosg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByIdetdo", query = "SELECT t FROM TipoDocumento t WHERE t.idetdo = :idetdo")
    , @NamedQuery(name = "TipoDocumento.findByTiptdo", query = "SELECT t FROM TipoDocumento t WHERE t.tiptdo = :tiptdo")
    , @NamedQuery(name = "TipoDocumento.findByRentdo", query = "SELECT t FROM TipoDocumento t WHERE t.rentdo = :rentdo")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idetdo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String tiptdo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String destdo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean rentdo;
    @OneToMany(mappedBy = "idetdo", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public TipoDocumento() {
    }

    public TipoDocumento(Long idetdo) {
        this.idetdo = idetdo;
    }

    public TipoDocumento(Long idetdo, String tiptdo, String destdo, boolean rentdo) {
        this.idetdo = idetdo;
        this.tiptdo = tiptdo;
        this.destdo = destdo;
        this.rentdo = rentdo;
    }

    public Long getIdetdo() {
        return idetdo;
    }

    public void setIdetdo(Long idetdo) {
        this.idetdo = idetdo;
    }

    public String getTiptdo() {
        return tiptdo;
    }

    public void setTiptdo(String tiptdo) {
        this.tiptdo = tiptdo;
    }

    public String getDestdo() {
        return destdo;
    }

    public void setDestdo(String destdo) {
        this.destdo = destdo;
    }

    public boolean getRentdo() {
        return rentdo;
    }

    public void setRentdo(boolean rentdo) {
        this.rentdo = rentdo;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetdo != null ? idetdo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idetdo == null && other.idetdo != null) || (this.idetdo != null && !this.idetdo.equals(other.idetdo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.TipoDocumento[ idetdo=" + idetdo + " ]";
    }
    
}
