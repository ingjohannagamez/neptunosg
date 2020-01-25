package com.neptunosg.dto;

import com.neptunosg.entity.TipoDocumento;
import java.io.Serializable;

public class TipoDocumentoDTO implements Serializable {

    private Integer idetdo;
    private String tiptdo;
    private String destdo;
    private boolean rentdo;

    public TipoDocumentoDTO() {
    }

    public TipoDocumentoDTO(TipoDocumento tipoDocumento) {
        this.idetdo = tipoDocumento.getIdetdo();
        this.tiptdo = tipoDocumento.getTiptdo();
        this.destdo = tipoDocumento.getDestdo();
        this.rentdo = tipoDocumento.getRentdo();
    }
    
    public Integer getIdetdo() {
        return idetdo;
    }

    public void setIdetdo(Integer idetdo) {
        this.idetdo = idetdo;
    }

    public String getTiptdo() {
        return tiptdo;
    }

    public void setTiptdo(String tiptdo) {
        this.tiptdo = tiptdo;
    }

    public String getDestdo() {
        return destdo;
    }

    public void setDestdo(String destdo) {
        this.destdo = destdo;
    }

    public boolean isRentdo() {
        return rentdo;
    }

    public void setRentdo(boolean rentdo) {
        this.rentdo = rentdo;
    }
    
}
