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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(catalog = "afsolu_neptunosg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")
    , @NamedQuery(name = "Municipio.findByIdemun", query = "SELECT m FROM Municipio m WHERE m.idemun = :idemun")
    , @NamedQuery(name = "Municipio.findByNommun", query = "SELECT m FROM Municipio m WHERE m.nommun = :nommun")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idemun;
    @Size(max = 200)
    @Column(length = 200)
    private String nommun;
    @JoinColumn(name = "idedep", referencedColumnName = "idedep")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento idedep;
    @OneToMany(mappedBy = "idemun", fetch = FetchType.LAZY)
    private List<Oficina> oficinaList;

    public Municipio() {
    }

    public Municipio(Long idemun) {
        this.idemun = idemun;
    }

    public Long getIdemun() {
        return idemun;
    }

    public void setIdemun(Long idemun) {
        this.idemun = idemun;
    }

    public String getNommun() {
        return nommun;
    }

    public void setNommun(String nommun) {
        this.nommun = nommun;
    }

    public Departamento getIdedep() {
        return idedep;
    }

    public void setIdedep(Departamento idedep) {
        this.idedep = idedep;
    }

    @XmlTransient
    public List<Oficina> getOficinaList() {
        return oficinaList;
    }

    public void setOficinaList(List<Oficina> oficinaList) {
        this.oficinaList = oficinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemun != null ? idemun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idemun == null && other.idemun != null) || (this.idemun != null && !this.idemun.equals(other.idemun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Municipio[ idemun=" + idemun + " ]";
    }
    
}
