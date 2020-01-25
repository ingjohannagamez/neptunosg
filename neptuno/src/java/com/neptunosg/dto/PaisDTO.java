package com.neptunosg.dto;

import com.neptunosg.entity.Pais;
import java.io.Serializable;

public class PaisDTO implements Serializable {

    private Integer idepai;
    private String nompai;
    private ContinenteDTO idecon;

    public PaisDTO() {
    }

    public PaisDTO(Pais pais) {
        this.idepai = pais.getIdepai();
        this.nompai = pais.getNompai();
        this.idecon = new ContinenteDTO(pais.getIdecon());
    }

    public Integer getIdepai() {
        return idepai;
    }

    public void setIdepai(Integer idepai) {
        this.idepai = idepai;
    }

    public String getNompai() {
        return nompai;
    }

    public void setNompai(String nompai) {
        this.nompai = nompai;
    }

    public ContinenteDTO getIdecon() {
        return idecon;
    }

    public void setIdecon(ContinenteDTO idecon) {
        this.idecon = idecon;
    }
    
}
