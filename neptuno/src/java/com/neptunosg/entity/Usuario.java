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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideusr", nullable = false)
    private Integer ideusr;
    @Size(max = 50)
    @Column(name = "epsusr", length = 50)
    private String epsusr;
    @Size(max = 11)
    @Column(name = "docusr", length = 11)
    private String docusr;
    @Size(max = 50)
    @Column(name = "pnousr", length = 50)
    private String pnousr;
    @Size(max = 50)
    @Column(name = "snousr", length = 50)
    private String snousr;
    @Size(max = 50)
    @Column(name = "papusr", length = 50)
    private String papusr;
    @Size(max = 50)
    @Column(name = "sapusr", length = 50)
    private String sapusr;
    @Size(max = 200)
    @Column(name = "emlusr", length = 200)
    private String emlusr;
    @Size(max = 1)
    @Column(name = "sexusr", length = 1)
    private String sexusr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fnausr")
    private Date fnausr;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fafusr")
    private Date fafusr;
    @Size(max = 500)
    @Column(name = "dirusr", length = 500)
    private String dirusr;
    @Size(max = 10)
    @Column(name = "telusr", length = 10)
    private String telusr;
    @Size(max = 10)
    @Column(name = "extusr", length = 10)
    private String extusr;
    @Size(max = 200)
    @Column(name = "fotusr", length = 200)
    private String fotusr;
    @Size(max = 500)
    @Column(name = "notusr", length = 500)
    private String notusr;
    @Column(name = "estusr")
    private Short estusr;
    @OneToMany(mappedBy = "ideusr", fetch = FetchType.LAZY)
    private List<Acceso> accesoList;
    @JoinColumn(name = "ideofi", referencedColumnName = "ideofi")
    @ManyToOne(fetch = FetchType.LAZY)
    private Oficina ideofi;
    @JoinColumn(name = "idetdo", referencedColumnName = "idetdo")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDocumento idetdo;

    public Usuario() {
    }

    public Usuario(Integer ideusr) {
        this.ideusr = ideusr;
    }

    public Integer getIdeusr() {
        return ideusr;
    }

    public void setIdeusr(Integer ideusr) {
        this.ideusr = ideusr;
    }

    public String getEpsusr() {
        return epsusr;
    }

    public void setEpsusr(String epsusr) {
        this.epsusr = epsusr;
    }

    public String getDocusr() {
        return docusr;
    }

    public void setDocusr(String docusr) {
        this.docusr = docusr;
    }

    public String getPnousr() {
        return pnousr;
    }

    public void setPnousr(String pnousr) {
        this.pnousr = pnousr;
    }

    public String getSnousr() {
        return snousr;
    }

    public void setSnousr(String snousr) {
        this.snousr = snousr;
    }

    public String getPapusr() {
        return papusr;
    }

    public void setPapusr(String papusr) {
        this.papusr = papusr;
    }

    public String getSapusr() {
        return sapusr;
    }

    public void setSapusr(String sapusr) {
        this.sapusr = sapusr;
    }

    public String getEmlusr() {
        return emlusr;
    }

    public void setEmlusr(String emlusr) {
        this.emlusr = emlusr;
    }

    public String getSexusr() {
        return sexusr;
    }

    public void setSexusr(String sexusr) {
        this.sexusr = sexusr;
    }

    public Date getFnausr() {
        return fnausr;
    }

    public void setFnausr(Date fnausr) {
        this.fnausr = fnausr;
    }

    public Date getFafusr() {
        return fafusr;
    }

    public void setFafusr(Date fafusr) {
        this.fafusr = fafusr;
    }

    public String getDirusr() {
        return dirusr;
    }

    public void setDirusr(String dirusr) {
        this.dirusr = dirusr;
    }

    public String getTelusr() {
        return telusr;
    }

    public void setTelusr(String telusr) {
        this.telusr = telusr;
    }

    public String getExtusr() {
        return extusr;
    }

    public void setExtusr(String extusr) {
        this.extusr = extusr;
    }

    public String getFotusr() {
        return fotusr;
    }

    public void setFotusr(String fotusr) {
        this.fotusr = fotusr;
    }

    public String getNotusr() {
        return notusr;
    }

    public void setNotusr(String notusr) {
        this.notusr = notusr;
    }

    public Short getEstusr() {
        return estusr;
    }

    public void setEstusr(Short estusr) {
        this.estusr = estusr;
    }

    @XmlTransient
    public List<Acceso> getAccesoList() {
        return accesoList;
    }

    public void setAccesoList(List<Acceso> accesoList) {
        this.accesoList = accesoList;
    }

    public Oficina getIdeofi() {
        return ideofi;
    }

    public void setIdeofi(Oficina ideofi) {
        this.ideofi = ideofi;
    }

    public TipoDocumento getIdetdo() {
        return idetdo;
    }

    public void setIdetdo(TipoDocumento idetdo) {
        this.idetdo = idetdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideusr != null ? ideusr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.ideusr == null && other.ideusr != null) || (this.ideusr != null && !this.ideusr.equals(other.ideusr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.getPnousr() != null) {
            return this.getPnousr()
                    .concat(this.getSnousr() == null ? "" : " " + this.getSnousr())
                    .concat(" " + this.getPapusr().concat(this.getSapusr() == null ? "" : " " + this.getSapusr()));
        } else {
            return null;
        }
    }
    
}
