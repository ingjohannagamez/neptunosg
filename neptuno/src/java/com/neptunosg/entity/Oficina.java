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
@Table(name = "oficina")
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
        return this.nomofi;
    }
    
}
