package com.neptunosg.dto;

import com.neptunosg.entity.Region;
import java.io.Serializable;

public class RegionDTO implements Serializable {

    private Integer idereg;
    private String nomreg;
    private PaisDTO idepai;

    public RegionDTO() {
    }

    public RegionDTO(Region region) {
        this.idereg = region.getIdereg();
        this.nomreg = region.getNomreg();
        this.idepai = new PaisDTO(region.getIdepai());
    }

    public Integer getIdereg() {
        return idereg;
    }

    public void setIdereg(Integer idereg) {
        this.idereg = idereg;
    }

    public String getNomreg() {
        return nomreg;
    }

    public void setNomreg(String nomreg) {
        this.nomreg = nomreg;
    }

    public PaisDTO getIdepai() {
        return idepai;
    }

    public void setIdepai(PaisDTO idepai) {
        this.idepai = idepai;
    }
    
}
