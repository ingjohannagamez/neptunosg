package com.neptunosg.dto;

import com.neptunosg.entity.Continente;
import java.io.Serializable;

public class ContinenteDTO implements Serializable {

    private Integer idecon;
    private String nomcon;

    public ContinenteDTO() {
    }

    public ContinenteDTO(Continente continente) {
        this.idecon = continente.getIdecon();
        this.nomcon = continente.getNomcon();
    }

    public Integer getIdecon() {
        return idecon;
    }

    public void setIdecon(Integer idecon) {
        this.idecon = idecon;
    }

    public String getNomcon() {
        return nomcon;
    }

    public void setNomcon(String nomcon) {
        this.nomcon = nomcon;
    }
    
}
