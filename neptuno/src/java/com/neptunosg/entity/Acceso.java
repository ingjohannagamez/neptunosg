package com.neptunosg.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "acceso")
public class Acceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long ideacc;
    @Size(max = 50)
    @Column(length = 50)
    private String nikacc;
    @Size(max = 32)
    @Column(length = 32)
    private String pswacc;
    @Temporal(TemporalType.TIMESTAMP)
    private Date freacc;
    private Short estacc;
    @JoinColumn(name = "iderol", referencedColumnName = "iderol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol iderol;
    @JoinColumn(name = "ideusr", referencedColumnName = "ideusr")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario ideusr;

    public Acceso() {
    }

    public Acceso(Long ideacc) {
        this.ideacc = ideacc;
    }

    public Long getIdeacc() {
        return ideacc;
    }

    public void setIdeacc(Long ideacc) {
        this.ideacc = ideacc;
    }

    public String getNikacc() {
        return nikacc;
    }

    public void setNikacc(String nikacc) {
        this.nikacc = nikacc;
    }

    public String getPswacc() {
        return pswacc;
    }

    public void setPswacc(String pswacc) {
        this.pswacc = pswacc;
    }

    public Date getFreacc() {
        return freacc;
    }

    public void setFreacc(Date freacc) {
        this.freacc = freacc;
    }

    public Short getEstacc() {
        return estacc;
    }

    public void setEstacc(Short estacc) {
        this.estacc = estacc;
    }

    public Rol getIderol() {
        return iderol;
    }

    public void setIderol(Rol iderol) {
        this.iderol = iderol;
    }

    public Usuario getIdeusr() {
        return ideusr;
    }

    public void setIdeusr(Usuario ideusr) {
        this.ideusr = ideusr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideacc != null ? ideacc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acceso)) {
            return false;
        }
        Acceso other = (Acceso) object;
        if ((this.ideacc == null && other.ideacc != null) || (this.ideacc != null && !this.ideacc.equals(other.ideacc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Acceso[ ideacc=" + ideacc + " ]";
    }
    
}
