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
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.findByIdereg", query = "SELECT r FROM Region r WHERE r.idereg = :idereg")
    , @NamedQuery(name = "Region.findByNomreg", query = "SELECT r FROM Region r WHERE r.nomreg = :nomreg")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idereg;
    @Size(max = 200)
    @Column(length = 200)
    private String nomreg;
    @OneToMany(mappedBy = "idereg", fetch = FetchType.LAZY)
    private List<Departamento> departamentoList;
    @JoinColumn(name = "idepai", referencedColumnName = "idepai")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais idepai;

    public Region() {
    }

    public Region(Long idereg) {
        this.idereg = idereg;
    }

    public Long getIdereg() {
        return idereg;
    }

    public void setIdereg(Long idereg) {
        this.idereg = idereg;
    }

    public String getNomreg() {
        return nomreg;
    }

    public void setNomreg(String nomreg) {
        this.nomreg = nomreg;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public Pais getIdepai() {
        return idepai;
    }

    public void setIdepai(Pais idepai) {
        this.idepai = idepai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idereg != null ? idereg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idereg == null && other.idereg != null) || (this.idereg != null && !this.idereg.equals(other.idereg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Region[ idereg=" + idereg + " ]";
    }
    
}
