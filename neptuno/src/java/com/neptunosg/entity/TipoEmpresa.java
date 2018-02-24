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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "tipo_empresa")
public class TipoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idetem;
    @Size(max = 200)
    @Column(length = 200)
    private String nomtem;
    @Size(max = 500)
    @Column(length = 500)
    private String destem;
    @OneToMany(mappedBy = "idetem", fetch = FetchType.LAZY)
    private List<Empresa> empresaList;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Long idetem) {
        this.idetem = idetem;
    }

    public Long getIdetem() {
        return idetem;
    }

    public void setIdetem(Long idetem) {
        this.idetem = idetem;
    }

    public String getNomtem() {
        return nomtem;
    }

    public void setNomtem(String nomtem) {
        this.nomtem = nomtem;
    }

    public String getDestem() {
        return destem;
    }

    public void setDestem(String destem) {
        this.destem = destem;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idetem != null ? idetem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpresa)) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) object;
        if ((this.idetem == null && other.idetem != null) || (this.idetem != null && !this.idetem.equals(other.idetem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomtem;
    }
    
}
