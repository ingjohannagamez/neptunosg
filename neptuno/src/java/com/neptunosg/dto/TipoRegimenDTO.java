package com.neptunosg.dto;

import com.neptunosg.entity.*;
import java.io.Serializable;

public class TipoRegimenDTO implements Serializable {

    private Integer idetre;
    private String rgmtre;
    private String nomtre;
    private String destre;

    public TipoRegimenDTO() {
    }

    public TipoRegimenDTO(TipoRegimen tipoRegimen) {
        this.idetre = tipoRegimen.getIdetre();
        this.rgmtre = tipoRegimen.getRgmtre();
        this.nomtre = tipoRegimen.getNomtre();
        this.destre = tipoRegimen.getDestre();
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
    
}
