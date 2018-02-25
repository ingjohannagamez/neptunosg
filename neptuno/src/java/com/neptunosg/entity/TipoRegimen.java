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
@Table(name = "tipo_regimen")
public class TipoRegimen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idetre;
    @Size(max = 1)
    @Column(length = 1)
    private String rgmtre;
    @Size(max = 200)
    @Column(length = 200)
    private String nomtre;
    @Size(max = 500)
    @Column(length = 500)
    private String destre;
    @OneToMany(mappedBy = "idetre", fetch = FetchType.LAZY)
    private List<Empresa> empresaList;

    public TipoRegimen() {
    }

    public TipoRegimen(Integer idetre) {
        this.idetre = idetre;
    }

    public Integer getIdetre() {
        return idetre;
    }

    public void setIdetre(Integer idetre) {
        this.idetre = idetre;
    }

    public String getRgmtre() {
        return rgmtre;
    }

    public void setRgmtre(String rgmtre) {
        this.rgmtre = rgmtre;
    }

    public String getNomtre() {
        return nomtre;
    }

    public void setNomtre(String nomtre) {
        this.nomtre = nomtre;
    }

    public String getDestre() {
        return destre;
    }

    public void setDestre(String destre) {
        this.destre = destre;
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
        hash += (idetre != null ? idetre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRegimen)) {
            return false;
        }
        TipoRegimen other = (TipoRegimen) object;
        if ((this.idetre == null && other.idetre != null) || (this.idetre != null && !this.idetre.equals(other.idetre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nomtre;
    }
    
}
