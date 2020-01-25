package com.neptunosg.controller;

import com.google.gson.Gson;
import com.neptunosg.controller.util.JsfUtil;
import com.neptunosg.controller.util.Session;
import com.neptunosg.dto.UsuarioDTO;
import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.Empresa;
import com.neptunosg.entity.Oficina;
import com.neptunosg.entity.Usuario;
import com.neptunosg.facade.AccesoFacade;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * @Copyrigth [2018] www.neptunosg.com
 * @LoginController
 * @Descripcion Controlador encargado de administrar los accesos de los usuarios
 * @author Johann Andres Agamez Ferres
 * @Fecha Creación: 05/11/2014
 * @Fecha ultima modificación: 06/11/2014
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private transient Session sessionNeptunoSG;
    @Inject
    private EmpresaController empController;
    @Inject
    private AccesoFacade accesoFacade;

    private Acceso acceso;

    //Combo de compañias
    private List<SelectItem> listadoItemsCompanias;

    public LoginController() {

    }

    @PostConstruct
    public void init() {
        this.setAcceso(new Acceso());
        this.getAcceso().setIdeusr(new Usuario());
        this.getAcceso().getIdeusr().setIdeofi(new Oficina());
        this.getAcceso().getIdeusr().getIdeofi().setIdeemp(new Empresa());
        listadoItemsCompanias();
    }

    /**
     * @Descripcion Metodo de busqueda de las empresas registradas en la DB
     * @autor Johan Agamez
     * @Fecha Creacion 23/09/2014
     * @Fecha Modificacion 23/09/2014
     * @return List
     */
    private List listadoItemsCompanias() {
        try {
            this.setListadoItemsCompanias(new ArrayList<>());
            empController.getItems().forEach((listadoCompaniasDB1) -> {
                this.getListadoItemsCompanias().add(new SelectItem(listadoCompaniasDB1.getIdeemp(), listadoCompaniasDB1.getNomemp()));
            });
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        return this.getListadoItemsCompanias();
    }

    /**
     * @Descripcion Metodo de autenticacioón del ususario en el sistema
     * @autor Johann Agamez
     * @Fecha Creacion 23/09/2014
     * @Fecha Modificacion 23/09/2014
     * @return String
     */
    public String validarAcceso() {
        String ruta = "";
        try {
            if (!this.getAcceso().getNikacc().equals("") && !this.getAcceso().getPswacc().equals("")) {
                this.setAcceso(accesoFacade.validarAcceso(this.getAcceso()));
                if (this.getAcceso() != null && this.getAcceso().getIdeusr().getEstusr() == 1) {
                    JsfUtil.showMessage("Bienvenido ", FacesMessage.SEVERITY_INFO, "messages");
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    sessionNeptunoSG.setIDSESION(session.getId());
                    sessionNeptunoSG.setDatosAcceso(this.getAcceso());
                    sessionNeptunoSG.setBtnCrear((this.getAcceso().getIderol().getIdeper().getEscper()));
                    sessionNeptunoSG.setBtnEditar((this.getAcceso().getIderol().getIdeper().getModper()));
                    sessionNeptunoSG.setBtnVer((this.getAcceso().getIderol().getIdeper().getLecper()));
                    sessionNeptunoSG.setBtnEliminar((this.getAcceso().getIderol().getIdeper().getEliper()));
                    sessionNeptunoSG.setMenuVisible(true);
                    sessionNeptunoSG.setMovil(false);
                    session.setAttribute("sessionNeptunoSG", sessionNeptunoSG);
                    ruta = "inicio";
                } else {
                    JsfUtil.showMessage("Error usuario no valido o inactivo", FacesMessage.SEVERITY_ERROR, "messages");
                    return "/app/login/index.xhtml?facesRedirect=true";
                }
            } else {
                JsfUtil.showMessage("Todos los datos son obligatorios", FacesMessage.SEVERITY_ERROR, "messages");
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getSimpleName()).log(Level.SEVERE, null, e);
        }
        return ruta;
    }

    /**
     * @Descripcion Metodo de autenticacioón del ususario por Movil en el
     * sistema
     * @autor Johann Agamez
     * @Fecha Creacion 23/09/2014
     * @Fecha Modificacion 23/09/2014
     * @return String
     */
    public String validarAccesoMovil() {
        String ruta = "";
        try {
            if (!this.getAcceso().getNikacc().equals("") && !this.getAcceso().getPswacc().equals("")) {
                this.setAcceso(accesoFacade.validarAcceso(this.getAcceso()));
                if (this.getAcceso() != null && this.getAcceso().getIdeusr().getEstusr().equals('1')) {
                    JsfUtil.showMessage("Bienvenido ", FacesMessage.SEVERITY_INFO);
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    sessionNeptunoSG.setIDSESION(session.getId());
                    sessionNeptunoSG.setDatosAcceso(this.getAcceso());
                    sessionNeptunoSG.setBtnCrear((this.getAcceso().getIderol().getIdeper().getEscper()));
                    sessionNeptunoSG.setBtnEditar((this.getAcceso().getIderol().getIdeper().getModper()));
                    sessionNeptunoSG.setBtnVer((this.getAcceso().getIderol().getIdeper().getLecper()));
                    sessionNeptunoSG.setBtnEliminar((this.getAcceso().getIderol().getIdeper().getEliper()));
                    sessionNeptunoSG.setMenuVisible(true);
                    sessionNeptunoSG.setMovil(true);
                    session.setAttribute("sessionNeptunoSG", sessionNeptunoSG);
                    ruta = "inicioM";
                } else {
                    JsfUtil.showMessage("Error usuario no valido", FacesMessage.SEVERITY_ERROR);
                    return "/app/login/indexM.xhtml?facesRedirect=true";
                }
            } else {
                JsfUtil.showMessage("Todos los datos son obligatorios", FacesMessage.SEVERITY_ERROR);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getSimpleName()).log(Level.SEVERE, null, e);
        }
        return ruta;
    }
    
    /**
     * @param acceso
     * @Descripcion Metodo de autenticacioón del ususario en el sistema por WS
     * @autor Johann Agamez
     * @Fecha Creacion 25/01/2020
     * @Fecha Modificacion 25/01/2020
     * @return JSON
     */
    public String validarAccesoWS(Acceso acceso) {
        String respuesta = "";
        try {
            Gson gson = new Gson();
            final AccesoFacade accesoFacadeWS = new AccesoFacade();
            if (!acceso.getNikacc().equals("") && !acceso.getPswacc().equals("")) {
                acceso = accesoFacadeWS.validarAcceso(acceso);
                if (acceso != null && acceso.getIdeusr().getEstusr() == 1) {
                    respuesta = gson.toJson(new UsuarioDTO(acceso.getIdeusr()));
                } else {
                    respuesta ="Error usuario no valido o inactivo";
                }
            } else {
                respuesta ="Todos los datos son obligatorios";
            }
        } catch (Exception e) {
            respuesta ="Error del sistema por favor comunicade con el administrador";
            Logger.getLogger(LoginController.class.getSimpleName()).log(Level.SEVERE, null, e);
        }
        return respuesta;
    }

    /**
     * Metodo que cierra session
     *
     * @return
     * @throws java.io.IOException
     * @autor Johann Andres Agamez Ferres
     * @Fecha Creacion 24/09/2014
     */
    public String closeSession() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        this.sessionNeptunoSG.cerrarSesion();
        this.sessionNeptunoSG = null;

        return "login";
    }

    private boolean isMovil() {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            //Archivo JavaScript.
            final File SCRIPT = new File(servletContext.getRealPath("/resources/js/iframe.js"));
            //Motor de ejecución JavaScript.
            final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("javascript");

            FileReader fr = new FileReader(SCRIPT);

            // Ejecutamos el JavaScript
            Object result = ENGINE.eval(fr);

            return (boolean) result;
        } catch (IOException | ScriptException ex) {
            return false;
        }
    }

    public List<SelectItem> getListadoItemsCompanias() {
        return listadoItemsCompanias;
    }

    public void setListadoItemsCompanias(List<SelectItem> listadoItemsCompanias) {
        this.listadoItemsCompanias = listadoItemsCompanias;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

}
