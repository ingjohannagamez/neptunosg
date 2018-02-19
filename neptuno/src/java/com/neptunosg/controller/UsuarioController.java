package com.neptunosg.controller;

import com.neptunosg.entity.Usuario;
import com.neptunosg.entity.Acceso;
import java.util.List;
import com.neptunosg.facade.UsuarioFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private OficinaController ideofiController;
    @Inject
    private TipoDocumentoController idetdoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isAccesoListEmpty;

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

}
