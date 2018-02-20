package com.neptunosg.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long ideemp;
    @Size(max = 200)
    @Column(length = 200)
    private String nomemp;
    @Size(max = 80)
    @Column(length = 80)
    private String rcoemp;
    @Size(max = 50)
    @Column(length = 50)
    private String nitemp;
    @Size(max = 50)
    @Column(length = 50)
    private String codemp;
    @Size(max = 11)
    @Column(length = 11)
    private String tluemp;
    @Size(max = 11)
    @Column(length = 11)
    private String tldemp;
    @Size(max = 500)
    @Column(length = 500)
    private String diremp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date freemp;
    @Size(max = 200)
    @Column(length = 200)
    private String logemp;
    @OneToMany(mappedBy = "ideemp", fetch = FetchType.LAZY)
    private List<Oficina> oficinaList;
    @JoinColumn(name = "idetem", referencedColumnName = "idetem")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoEmpresa idetem;
    @JoinColumn(name = "idetre", referencedColumnName = "idetre")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoRegimen idetre;

    public Empresa() {
    }

    public Empresa(Long ideemp) {
        this.ideemp = ideemp;
    }

    public Long getIdeemp() {
        return ideemp;
    }

    public void setIdeemp(Long ideemp) {
        this.ideemp = ideemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public String getRcoemp() {
        return rcoemp;
    }

    public void setRcoemp(String rcoemp) {
        this.rcoemp = rcoemp;
    }

    public String getNitemp() {
        return nitemp;
    }

    public void setNitemp(String nitemp) {
        this.nitemp = nitemp;
    }

    public String getCodemp() {
        return codemp;
    }

    public void setCodemp(String codemp) {
        this.codemp = codemp;
    }

    public String getTluemp() {
        return tluemp;
    }

    public void setTluemp(String tluemp) {
        this.tluemp = tluemp;
    }

    public String getTldemp() {
        return tldemp;
    }

    public void setTldemp(String tldemp) {
        this.tldemp = tldemp;
    }

    public String getDiremp() {
        return diremp;
    }

    public void setDiremp(String diremp) {
        this.diremp = diremp;
    }

    public Date getFreemp() {
        return freemp;
    }

    public void setFreemp(Date freemp) {
        this.freemp = freemp;
    }

    public String getLogemp() {
        return logemp;
    }

    public void setLogemp(String logemp) {
        this.logemp = logemp;
    }

    @XmlTransient
    public List<Oficina> getOficinaList() {
        return oficinaList;
    }

    public void setOficinaList(List<Oficina> oficinaList) {
        this.oficinaList = oficinaList;
    }

    public TipoEmpresa getIdetem() {
        return idetem;
    }

    public void setIdetem(TipoEmpresa idetem) {
        this.idetem = idetem;
    }

    public TipoRegimen getIdetre() {
        return idetre;
    }

    public void setIdetre(TipoRegimen idetre) {
        this.idetre = idetre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideemp != null ? ideemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.ideemp == null && other.ideemp != null) || (this.ideemp != null && !this.ideemp.equals(other.ideemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.neptunosg.entity.Empresa[ ideemp=" + ideemp + " ]";
    }
    
}
