package com.neptunosg.dto;

import com.neptunosg.entity.*;
import java.io.Serializable;

public class OficinaDTO implements Serializable {

    private Integer ideofi;
    private String nomofi;
    private String dirofi;
    private String objofi;
    private Short estofi;
    private EmpresaDTO ideemp;
    private MunicipioDTO idemun;

    public OficinaDTO() {
    }

    public OficinaDTO(Oficina oficina) {
        this.ideofi = oficina.getIdeofi();
        this.nomofi = oficina.getNomofi();
        this.dirofi = oficina.getDirofi();
        this.objofi = oficina.getObjofi();
        this.estofi = oficina.getEstofi();
        this.ideemp = new EmpresaDTO(oficina.getIdeemp());
        this.idemun = new MunicipioDTO(oficina.getIdemun());
    }

    public Integer getIdeofi() {
        return ideofi;
    }

    public void setIdeofi(Integer ideofi) {
        this.ideofi = ideofi;
    }

    public String getNomofi() {
        return nomofi;
    }

    public void setNomofi(String nomofi) {
        this.nomofi = nomofi;
    }

    public String getDirofi() {
        return dirofi;
    }

    public void setDirofi(String dirofi) {
        this.dirofi = dirofi;
    }

    public String getObjofi() {
        return objofi;
    }

    public void setObjofi(String objofi) {
        this.objofi = objofi;
    }

    public Short getEstofi() {
        return estofi;
    }

    public void setEstofi(Short estofi) {
        this.estofi = estofi;
    }

    public EmpresaDTO getIdeemp() {
        return ideemp;
    }

    public void setIdeemp(EmpresaDTO ideemp) {
        this.ideemp = ideemp;
    }

    public MunicipioDTO getIdemun() {
        return idemun;
    }

    public void setIdemun(MunicipioDTO idemun) {
        this.idemun = idemun;
    }
    
}
