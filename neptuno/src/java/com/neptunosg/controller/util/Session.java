package com.neptunosg.controller.util;

import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.MenuRol;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Johann Agamez
 */
@Named
@SessionScoped
public class Session implements Serializable {

    private static final long serialVersionUID = -1521486920663927841L;

    private Map<String, Object> mapSession;
    private Acceso datosAcceso;
    private String IDSESION;
    private boolean menuVisible;
    private boolean btnCrear;
    private boolean btnEditar;
    private boolean btnVer;
    private boolean btnEliminar;
    private boolean movil;

    public boolean permisos(String permisoPer) {
        boolean permiso = false;
        if (datosAcceso != null && datosAcceso.getIderol().getMenuRolList() != null) {
            for (MenuRol r : datosAcceso.getIderol().getMenuRolList()) {
                if (r.getIdesub().getComsub().contains(permisoPer)) {
                    permiso = true;
                    break;
                }
            }
        }
        return permiso;
    }
    
    public void cerrarSesion() {
        setDatosAcceso(null);
        setIDSESION("");
        setMenuVisible(false);
        setBtnCrear(false);
        setBtnEditar(false);
        setBtnVer(false);
        setBtnEliminar(false);
        setMovil(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Map<String, Object> getMapSession() {
        mapSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return mapSession;
    }
    
    public String getRowsPerPageTemplate() {
        return "12,24,36,48,60";
    }

    public String getRows() {
        return "12";
    }

    public String getCacheTimeout() {
        return "240000";
    }

    public boolean isCaching() {
        return true;
    }

    public Acceso getDatosAcceso() {
        return datosAcceso;
    }

    public void setDatosAcceso(Acceso datosAcceso) {
        this.datosAcceso = datosAcceso;
    }

    public String getIDSESION() {
        return IDSESION;
    }

    public void setIDSESION(String IDSESION) {
        this.IDSESION = IDSESION;
    }

    public boolean isMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(boolean menuVisible) {
        this.menuVisible = menuVisible;
    }

    public boolean isBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(boolean btnCrear) {
        this.btnCrear = btnCrear;
    }

    public boolean isBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(boolean btnEditar) {
        this.btnEditar = btnEditar;
    }

    public boolean isBtnVer() {
        return btnVer;
    }

    public void setBtnVer(boolean btnVer) {
        this.btnVer = btnVer;
    }

    public boolean isBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(boolean btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public boolean isMovil() {
        return movil;
    }

    public void setMovil(boolean movil) {
        this.movil = movil;
    }
    
}
