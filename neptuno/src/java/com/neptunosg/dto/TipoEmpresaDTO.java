package com.neptunosg.dto;

import com.neptunosg.entity.TipoEmpresa;
import java.io.Serializable;

public class TipoEmpresaDTO implements Serializable {

    private Integer idetem;
    private String nomtem;
    private String destem;

    public TipoEmpresaDTO() {
    }

    public TipoEmpresaDTO(TipoEmpresa tipoEmpresa) {
        this.idetem = tipoEmpresa.getIdetem();
        this.nomtem = tipoEmpresa.getNomtem();
        this.destem = tipoEmpresa.getDestem();
    }

    public Integer getIdetem() {
        return idetem;
    }

    public void setIdetem(Integer idetem) {
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
    
}
