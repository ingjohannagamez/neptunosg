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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "tipo_documento")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idetdo", nullable = false)
    private Integer idetdo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tiptdo", nullable = false, length = 2)
    private String tiptdo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "destdo", nullable = false, length = 65535)
    private String destdo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rentdo", nullable = false)
    private boolean rentdo;
    @OneToMany(mappedBy = "idetdo", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idetdo) {
        this.idetdo = idetdo;
    }

    public TipoDocumento(Integer idetdo, String tiptdo, String destdo, boolean rentdo) {
        this.idetdo = idetdo;
        this.tiptdo = tiptdo;
        this.destdo = destdo;
        this.rentdo = rentdo;
    }

    public Integer getIdetdo() {
        return idetdo;
    }

    public void setIdetdo(Integer idetdo) {
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
        return this.tiptdo;
    }
    
}
