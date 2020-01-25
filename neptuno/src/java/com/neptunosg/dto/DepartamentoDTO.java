package com.neptunosg.dto;

import com.neptunosg.entity.*;
import java.io.Serializable;

public class DepartamentoDTO implements Serializable {

    private Integer idedep;
    private String nomdep;
    private RegionDTO idereg;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Departamento departamento) {
        this.idedep = departamento.getIdedep();
        this.nomdep = departamento.getNomdep();
        this.idereg = new RegionDTO(departamento.getIdereg());
    }

    public Integer getIdedep() {
        return idedep;
    }

    public void setIdedep(Integer idedep) {
        this.idedep = idedep;
    }

    public String getNomdep() {
        return nomdep;
    }

    public void setNomdep(String nomdep) {
        this.nomdep = nomdep;
    }

    public RegionDTO getIdereg() {
        return idereg;
    }

    public void setIdereg(RegionDTO idereg) {
        this.idereg = idereg;
    }

}
