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
@Table(name = "continente")
public class Continente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idecon;
    @Size(max = 200)
    @Column(length = 200)
    private String nomcon;
    @OneToMany(mappedBy = "idecon", fetch = FetchType.LAZY)
    private List<Pais> paisList;

    public Continente() {
    }

    public Continente(Integer idecon) {
        this.idecon = idecon;
    }

    public Integer getIdecon() {
        return idecon;
    }

    public void setIdecon(Integer idecon) {
        this.idecon = idecon;
    }

    public String getNomcon() {
        return nomcon;
    }

    public void setNomcon(String nomcon) {
        this.nomcon = nomcon;
    }

    @XmlTransient
    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idecon != null ? idecon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Continente)) {
            return false;
        }
        Continente other = (Continente) object;
        if ((this.idecon == null && other.idecon != null) || (this.idecon != null && !this.idecon.equals(other.idecon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomcon;
    }
    
}
