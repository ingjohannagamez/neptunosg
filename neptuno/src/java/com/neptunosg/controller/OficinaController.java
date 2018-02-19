package com.neptunosg.controller;

import com.neptunosg.entity.Oficina;
import com.neptunosg.entity.Usuario;
import java.util.List;
import com.neptunosg.facade.OficinaFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "oficinaController")
@ViewScoped
public class OficinaController extends AbstractController<Oficina> {

    @Inject
    private EmpresaController ideempController;
    @Inject
    private MunicipioController idemunController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isUsuarioListEmpty;

    public OficinaController() {
        // Inform the Abstract parent controller of the concrete Oficina Entity
        super(Oficina.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ideempController.setSelected(null);
        idemunController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsuarioListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Empresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdeemp(ActionEvent event) {
        Oficina selected = this.getSelected();
        if (selected != null && ideempController.getSelected() == null) {
            ideempController.setSelected(selected.getIdeemp());
        }
    }

    /**
     * Sets the "selected" attribute of the Municipio controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdemun(ActionEvent event) {
        Oficina selected = this.getSelected();
        if (selected != null && idemunController.getSelected() == null) {
            idemunController.setSelected(selected.getIdemun());
        }
    }

    public boolean getIsUsuarioListEmpty() {
        return this.isUsuarioListEmpty;
    }

    private void setIsUsuarioListEmpty() {
        Oficina selected = this.getSelected();
        if (selected != null) {
            OficinaFacade ejbFacade = (OficinaFacade) this.getFacade();
            this.isUsuarioListEmpty = ejbFacade.isUsuarioListEmpty(selected);
        } else {
            this.isUsuarioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from Oficina and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioList() {
        Oficina selected = this.getSelected();
        if (selected != null) {
            OficinaFacade ejbFacade = (OficinaFacade) this.getFacade();
            List<Usuario> selectedUsuarioList = ejbFacade.findUsuarioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/usuario/index";
    }

}
