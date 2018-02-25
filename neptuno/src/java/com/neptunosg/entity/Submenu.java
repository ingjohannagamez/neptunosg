package com.neptunosg.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pipo0
 */
@Entity
@Table(name = "submenu")
public class Submenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idesub", nullable = false)
    private Integer idesub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "labsub", nullable = false, length = 50)
    private String labsub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "icosub", nullable = false, length = 100)
    private String icosub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "comsub", nullable = false, length = 200)
    private String comsub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titsub", nullable = false, length = 50)
    private String titsub;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rensub", nullable = false)
    private boolean rensub;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idesub", fetch = FetchType.LAZY)
    private List<MenuRol> menuRolList;
    @JoinColumn(name = "idemen", referencedColumnName = "idemen", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Menu idemen;

    public Submenu() {
    }

    public Submenu(Integer idesub) {
        this.idesub = idesub;
    }

    public Submenu(Integer idesub, String labsub, String icosub, String comsub, String titsub, boolean rensub) {
        this.idesub = idesub;
        this.labsub = labsub;
        this.icosub = icosub;
        this.comsub = comsub;
        this.titsub = titsub;
        this.rensub = rensub;
    }

    public Integer getIdesub() {
        return idesub;
    }

    public void setIdesub(Integer idesub) {
        this.idesub = idesub;
    }

    public String getLabsub() {
        return labsub;
    }

    public void setLabsub(String labsub) {
        this.labsub = labsub;
    }

    public String getIcosub() {
        return icosub;
    }

    public void setIcosub(String icosub) {
        this.icosub = icosub;
    }

    public String getComsub() {
        return comsub;
    }

    public void setComsub(String comsub) {
        this.comsub = comsub;
    }

    public String getTitsub() {
        return titsub;
    }

    public void setTitsub(String titsub) {
        this.titsub = titsub;
    }

    public boolean getRensub() {
        return rensub;
    }

    public void setRensub(boolean rensub) {
        this.rensub = rensub;
    }

    @XmlTransient
    public List<MenuRol> getMenuRolList() {
        return menuRolList;
    }

    public void setMenuRolList(List<MenuRol> menuRolList) {
        this.menuRolList = menuRolList;
    }

    public Menu getIdemen() {
        return idemen;
    }

    public void setIdemen(Menu idemen) {
        this.idemen = idemen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idesub != null ? idesub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Submenu)) {
            return false;
        }
        Submenu other = (Submenu) object;
        if ((this.idesub == null && other.idesub != null) || (this.idesub != null && !this.idesub.equals(other.idesub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.getLabsub() != null) {
            return this.getLabsub()
                    .concat(this.getIdemen() == null ? "" : " - " + this.getIdemen().getNommen());
        } else {
            return null;
        }
    }

}
