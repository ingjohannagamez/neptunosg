package com.neptunosg.dto;

import com.neptunosg.entity.*;
import java.io.Serializable;
import java.util.Date;

public class UsuarioDTO implements Serializable {

    private Integer ideusr;
    private String epsusr;
    private String docusr;
    private String pnousr;
    private String snousr;
    private String papusr;
    private String sapusr;
    private String emlusr;
    private String sexusr;
    private Date fnausr;
    private Date fafusr;
    private String dirusr;
    private String telusr;
    private String extusr;
    private String fotusr;
    private String notusr;
    private Short estusr;
    private OficinaDTO ideofi;
    private TipoDocumentoDTO idetdo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.ideusr = usuario.getIdeusr();
        this.epsusr = usuario.getEpsusr();
        this.docusr = usuario.getDocusr();
        this.pnousr = usuario.getPnousr();
        this.snousr = usuario.getSnousr();
        this.papusr = usuario.getPapusr();
        this.sapusr = usuario.getSapusr();
        this.emlusr = usuario.getEmlusr();
        this.sexusr = usuario.getSexusr();
        this.fnausr = usuario.getFnausr();
        this.fafusr = usuario.getFafusr();
        this.dirusr = usuario.getDirusr();
        this.telusr = usuario.getTelusr();
        this.extusr = usuario.getExtusr();
        this.fotusr = usuario.getFotusr();
        this.notusr = usuario.getNotusr();
        this.estusr = usuario.getEstusr();
        this.ideofi = new OficinaDTO(usuario.getIdeofi());
        this.idetdo = new TipoDocumentoDTO(usuario.getIdetdo());
    }

    public Integer getIdeusr() {
        return ideusr;
    }

    public void setIdeusr(Integer ideusr) {
        this.ideusr = ideusr;
    }

    public String getEpsusr() {
        return epsusr;
    }

    public void setEpsusr(String epsusr) {
        this.epsusr = epsusr;
    }

    public String getDocusr() {
        return docusr;
    }

    public void setDocusr(String docusr) {
        this.docusr = docusr;
    }

    public String getPnousr() {
        return pnousr;
    }

    public void setPnousr(String pnousr) {
        this.pnousr = pnousr;
    }

    public String getSnousr() {
        return snousr;
    }

    public void setSnousr(String snousr) {
        this.snousr = snousr;
    }

    public String getPapusr() {
        return papusr;
    }

    public void setPapusr(String papusr) {
        this.papusr = papusr;
    }

    public String getSapusr() {
        return sapusr;
    }

    public void setSapusr(String sapusr) {
        this.sapusr = sapusr;
    }

    public String getEmlusr() {
        return emlusr;
    }

    public void setEmlusr(String emlusr) {
        this.emlusr = emlusr;
    }

    public String getSexusr() {
        return sexusr;
    }

    public void setSexusr(String sexusr) {
        this.sexusr = sexusr;
    }

    public Date getFnausr() {
        return fnausr;
    }

    public void setFnausr(Date fnausr) {
        this.fnausr = fnausr;
    }

    public Date getFafusr() {
        return fafusr;
    }

    public void setFafusr(Date fafusr) {
        this.fafusr = fafusr;
    }

    public String getDirusr() {
        return dirusr;
    }

    public void setDirusr(String dirusr) {
        this.dirusr = dirusr;
    }

    public String getTelusr() {
        return telusr;
    }

    public void setTelusr(String telusr) {
        this.telusr = telusr;
    }

    public String getExtusr() {
        return extusr;
    }

    public void setExtusr(String extusr) {
        this.extusr = extusr;
    }

    public String getFotusr() {
        return fotusr;
    }

    public void setFotusr(String fotusr) {
        this.fotusr = fotusr;
    }

    public String getNotusr() {
        return notusr;
    }

    public void setNotusr(String notusr) {
        this.notusr = notusr;
    }

    public Short getEstusr() {
        return estusr;
    }

    public void setEstusr(Short estusr) {
        this.estusr = estusr;
    }

    public OficinaDTO getIdeofi() {
        return ideofi;
    }

    public void setIdeofi(OficinaDTO ideofi) {
        this.ideofi = ideofi;
    }

    public TipoDocumentoDTO getIdetdo() {
        return idetdo;
    }

    public void setIdetdo(TipoDocumentoDTO idetdo) {
        this.idetdo = idetdo;
    }
    
}
