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
@Table(name = "oficina", catalog = "afsolu_neptunosg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficina.findAll", query = "SELECT o FROM Oficina o")
    , @NamedQuery(name = "Oficina.findByIdeofi", query = "SELECT o FROM Oficina o WHERE o.ideofi = :ideofi")
    , @NamedQuery(name = "Oficina.findByNomofi", query = "SELECT o FROM Oficina o WHERE o.nomofi = :nomofi")
    , @NamedQuery(name = "Oficina.findByDirofi", query = "SELECT o FROM Oficina o WHERE o.dirofi = :dirofi")
    , @NamedQuery(name = "Oficina.findByObjofi", query = "SELECT o FROM Oficina o WHERE o.objofi = :objofi")
    , @NamedQuery(name = "Oficina.findByEstofi", query = "SELECT o FROM Oficina o WHERE o.estofi = :estofi")})
public class Oficina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long ideofi;
    @Size(max = 200)
    @Column(length = 200)
    private String nomofi;
    @Size(max = 500)
    @Column(length = 500)
    private String dirofi;
    @Size(max = 1000)
    @Column(length = 1000)
    private String objofi;
    private Short estofi;
    @JoinColumn(name = "ideemp", referencedColumnName = "ideemp")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa ideemp;
    @JoinColumn(name = "idemun", referencedColumnName = "idemun")
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio idemun;
    @OneToMany(mappedBy = "ideofi", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Oficina() {
    }

    public Oficina(Long ideofi) {
        this.ideofi = ideofi;
    }

    public Long getIdeofi() {
        return ideofi;
    }

    public void setIdeofi(Long ideofi) {
        this.ideofi = ideofi;
    }

    public String getNomofi() {
        return nomofi;
    }

    public void setNomofi(String nomofi) {
        this.nomofi = nomofi;
    }

    public String getDirofi() {
        return dirofi;
    }

    public void setDirofi(String dirofi) {
        this.dirofi = dirofi;
    }

    public String getObjofi() {
        return objofi;
    }

    public void setObjofi(String objofi) {
        this.objofi = objofi;
    }

    public Short getEstofi() {
        return estofi;
    }

    public void setEstofi(Short estofi) {
        this.estofi = estofi;
    }

    public Empresa getIdeemp() {
        return ideemp;
    }

    public void setIdeemp(Empresa ideemp) {
        this.ideemp = ideemp;
    }

    public Municipio getIdemun() {
        return idemun;
    }

    public void setIdemun(Municipio idemun) {
        this.idemun = idemun;
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
        hash += (ideofi != null ? ideofi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficina)) {
            return false;
        }
        Oficina other = (Oficina) object;
        if ((this.ideofi == null && other.ideofi != null) || (this.ideofi != null && !this.ideofi.equals(other.ideofi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Oficina[ ideofi=" + ideofi + " ]";
    }
    
}
