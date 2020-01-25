package com.neptunosg.dto;

import com.neptunosg.entity.Empresa;
import java.io.Serializable;
import java.util.Date;

public class EmpresaDTO implements Serializable {

    private Integer ideemp;
    private String nomemp;
    private String rcoemp;
    private String nitemp;
    private String codemp;
    private String tluemp;
    private String tldemp;
    private String diremp;
    private Date freemp;
    private String logemp;
    private boolean estemp;
    private TipoEmpresaDTO idetem;
    private TipoRegimenDTO idetre;

    public EmpresaDTO() {
    }

    public EmpresaDTO(Empresa empresa) {
        this.ideemp = empresa.getIdeemp();
        this.nomemp = empresa.getNomemp();
        this.rcoemp = empresa.getRcoemp();
        this.nitemp = empresa.getNitemp();
        this.codemp = empresa.getCodemp();
        this.tluemp = empresa.getTluemp();
        this.tldemp = empresa.getTldemp();
        this.diremp = empresa.getDiremp();
        this.freemp = empresa.getFreemp();
        this.logemp = empresa.getLogemp();
        this.estemp = empresa.isEstemp();
        this.idetem = new TipoEmpresaDTO(empresa.getIdetem());
        this.idetre = new TipoRegimenDTO(empresa.getIdetre());
    }

    public Integer getIdeemp() {
        return ideemp;
    }

    public void setIdeemp(Integer ideemp) {
        this.ideemp = ideemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public String getRcoemp() {
        return rcoemp;
    }

    public void setRcoemp(String rcoemp) {
        this.rcoemp = rcoemp;
    }

    public String getNitemp() {
        return nitemp;
    }

    public void setNitemp(String nitemp) {
        this.nitemp = nitemp;
    }

    public String getCodemp() {
        return codemp;
    }

    public void setCodemp(String codemp) {
        this.codemp = codemp;
    }

    public String getTluemp() {
        return tluemp;
    }

    public void setTluemp(String tluemp) {
        this.tluemp = tluemp;
    }

    public String getTldemp() {
        return tldemp;
    }

    public void setTldemp(String tldemp) {
        this.tldemp = tldemp;
    }

    public String getDiremp() {
        return diremp;
    }

    public void setDiremp(String diremp) {
        this.diremp = diremp;
    }

    public Date getFreemp() {
        return freemp;
    }

    public void setFreemp(Date freemp) {
        this.freemp = freemp;
    }

    public String getLogemp() {
        return logemp;
    }

    public void setLogemp(String logemp) {
        this.logemp = logemp;
    }

    public boolean isEstemp() {
        return estemp;
    }

    public void setEstemp(boolean estemp) {
        this.estemp = estemp;
    }

    public TipoEmpresaDTO getIdetem() {
        return idetem;
    }

    public void setIdetem(TipoEmpresaDTO idetem) {
        this.idetem = idetem;
    }

    public TipoRegimenDTO getIdetre() {
        return idetre;
    }

    public void setIdetre(TipoRegimenDTO idetre) {
        this.idetre = idetre;
    }
    
}
