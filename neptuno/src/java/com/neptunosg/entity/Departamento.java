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
@Table(name = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idedep;
    @Size(max = 200)
    @Column(length = 200)
    private String nomdep;
    @OneToMany(mappedBy = "idedep", fetch = FetchType.LAZY)
    private List<Municipio> municipioList;
    @JoinColumn(name = "idereg", referencedColumnName = "idereg")
    @ManyToOne(fetch = FetchType.LAZY)
    private Region idereg;

    public Departamento() {
    }

    public Departamento(Long idedep) {
        this.idedep = idedep;
    }

    public Long getIdedep() {
        return idedep;
    }

    public void setIdedep(Long idedep) {
        this.idedep = idedep;
    }

    public String getNomdep() {
        return nomdep;
    }

    public void setNomdep(String nomdep) {
        this.nomdep = nomdep;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public Region getIdereg() {
        return idereg;
    }

    public void setIdereg(Region idereg) {
        this.idereg = idereg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idedep != null ? idedep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idedep == null && other.idedep != null) || (this.idedep != null && !this.idedep.equals(other.idedep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Departamento[ idedep=" + idedep + " ]";
    }
    
}
