package com.neptunosg.dto;

import com.neptunosg.entity.Municipio;
import java.io.Serializable;

public class MunicipioDTO implements Serializable {

    private Integer idemun;
    private String nommun;
    private DepartamentoDTO idedep;

    public MunicipioDTO() {
    }

    public MunicipioDTO(Municipio municipio) {
        this.idemun = municipio.getIdemun();
        this.nommun = municipio.getNommun();
        this.idedep = new DepartamentoDTO(municipio.getIdedep());
    }

    public Integer getIdemun() {
        return idemun;
    }

    public void setIdemun(Integer idemun) {
        this.idemun = idemun;
    }

    public String getNommun() {
        return nommun;
    }

    public void setNommun(String nommun) {
        this.nommun = nommun;
    }

    public DepartamentoDTO getIdedep() {
        return idedep;
    }

    public void setIdedep(DepartamentoDTO idedep) {
        this.idedep = idedep;
    }
    
}
