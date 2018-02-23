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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idepai;
    @Size(max = 200)
    @Column(length = 200)
    private String nompai;
    @JoinColumn(name = "idecon", referencedColumnName = "idecon")
    @ManyToOne(fetch = FetchType.LAZY)
    private Continente idecon;
    @OneToMany(mappedBy = "idepai", fetch = FetchType.LAZY)
    private List<Region> regionList;

    public Pais() {
    }

    public Pais(Long idepai) {
        this.idepai = idepai;
    }

    public Long getIdepai() {
        return idepai;
    }

    public void setIdepai(Long idepai) {
        this.idepai = idepai;
    }

    public String getNompai() {
        return nompai;
    }

    public void setNompai(String nompai) {
        this.nompai = nompai;
    }

    public Continente getIdecon() {
        return idecon;
    }

    public void setIdecon(Continente idecon) {
        this.idecon = idecon;
    }

    @XmlTransient
    public List<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<Region> regionList) {
        this.regionList = regionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idepai != null ? idepai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idepai == null && other.idepai != null) || (this.idepai != null && !this.idepai.equals(other.idepai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nompai;
    }
    
}
