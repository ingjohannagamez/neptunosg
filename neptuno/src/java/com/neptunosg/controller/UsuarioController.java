package com.neptunosg.controller;

import com.neptunosg.controller.util.JasperControllerCDIJDBC;
import com.neptunosg.controller.util.JsfUtil;
import com.neptunosg.entity.Usuario;
import com.neptunosg.entity.Acceso;
import java.util.List;
import com.neptunosg.facade.UsuarioFacade;
import com.neptunosg.controller.util.MobilePageController;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @Inject
    private OficinaController ideofiController;
    @Inject
    private TipoDocumentoController idetdoController;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private transient JasperControllerCDIJDBC reporte;

    // Flags to indicate if child collections are empty
    private boolean isAccesoListEmpty;
    private InputStream tempPdfFile;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ideofiController.setSelected(null);
        idetdoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsAccesoListEmpty();
    }

    public boolean getIsAccesoListEmpty() {
        return this.isAccesoListEmpty;
    }

    private void setIsAccesoListEmpty() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            this.isAccesoListEmpty = ejbFacade.isAccesoListEmpty(selected);
        } else {
            this.isAccesoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Acceso entities that are
     * retrieved from Usuario and returns the navigation outcome.
     *
     * @return navigation outcome for Acceso page
     */
    public String navigateAccesoList() {
        Usuario selected = this.getSelected();
        if (selected != null) {
            UsuarioFacade ejbFacade = (UsuarioFacade) this.getFacade();
            List<Acceso> selectedAccesoList = ejbFacade.findAccesoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Acceso_items", selectedAccesoList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/acceso/index";
    }

    /**
     * Sets the "selected" attribute of the Oficina controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdeofi(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && ideofiController.getSelected() == null) {
            ideofiController.setSelected(selected.getIdeofi());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDocumento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdetdo(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idetdoController.getSelected() == null) {
            idetdoController.setSelected(selected.getIdetdo());
        }
    }

    public void getPdf() {
        try {
            if (this.getSelected() != null) {

                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

                reporte.setNombreReporte("prueba");
                reporte.addParametro("edad", this.calcularEdad(this.getSelected().getFnausr()));
                reporte.addParametro("foto", servletContext.getRealPath("").concat(this.getSelected().getFotusr()));
                reporte.addParametro("ideusr", this.getSelected().getIdeusr());
                InputStream is = reporte.PDFb();
                if (is != null) {
                    this.creaArchivo(servletContext.getRealPath("").concat("\\resources\\documentos\\".concat(this.getSelected().getDocusr().concat("_HV.pdf"))), is);
                    this.tempPdfFile = is;
                } else {
                    JsfUtil.addErrorMessage("No se pudo generar el reporte");
                }
            } else {
                JsfUtil.addErrorMessage("No se pudo generar el reporte no se a seleccionado ningún registro");
            }
        } catch (JRException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        try {
            if (this.getFile() != null && !this.getFile().getFileName().equals("")) {
                this.copyFile(this.getFile().getFileName(), this.getFile().getInputstream(), "img_user", this.getSelected().getDocusr());
                this.getSelected().setFotusr("/resources/img/img_user/".concat(this.getSelected().getDocusr() + "." + this.getFile().getFileName().split("\\.")[1]));
            }
            super.saveNew(event);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(ActionEvent event) {
        try {
            if (this.getFile() != null && !this.getFile().getFileName().equals("")) {
                this.copyFile(this.getFile().getFileName(), this.getFile().getInputstream(), "img_user", this.getSelected().getDocusr());
                this.getSelected().setFotusr("/resources/img/img_user/".concat(this.getSelected().getDocusr() + "." + this.getFile().getFileName().split("\\.")[1]));
            }
            super.save(event);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getGuardarMostrarTempPdfFile() throws IOException {
        String respuesta = "";
        if (this.tempPdfFile != null) {
            DefaultStreamedContent dsc = new DefaultStreamedContent(this.tempPdfFile, "application/pdf", "Usuario.pdf");
            //File file = dsc.;
        }
        return respuesta;
    }

    public StreamedContent getTempPdfFile() throws IOException {
        DefaultStreamedContent dsc = null;
        if (this.tempPdfFile != null) {
            dsc = new DefaultStreamedContent(this.tempPdfFile, "application/pdf", "Usuario.pdf");
        }
        return dsc;
    }

}
