package com.neptunosg.controller;

import com.neptunosg.entity.Empresa;
import com.neptunosg.entity.Oficina;
import java.util.List;
import com.neptunosg.facade.EmpresaFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empresaController")
@ViewScoped
public class EmpresaController extends AbstractController<Empresa> {

    @Inject
    private TipoEmpresaController idetemController;
    @Inject
    private TipoRegimenController idetreController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isOficinaListEmpty;

    public EmpresaController() {
        // Inform the Abstract parent controller of the concrete Empresa Entity
        super(Empresa.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idetemController.setSelected(null);
        idetreController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsOficinaListEmpty();
    }

    public boolean getIsOficinaListEmpty() {
        return this.isOficinaListEmpty;
    }

    private void setIsOficinaListEmpty() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            this.isOficinaListEmpty = ejbFacade.isOficinaListEmpty(selected);
        } else {
            this.isOficinaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Oficina entities that are
     * retrieved from Empresa and returns the navigation outcome.
     *
     * @return navigation outcome for Oficina page
     */
    public String navigateOficinaList() {
        Empresa selected = this.getSelected();
        if (selected != null) {
            EmpresaFacade ejbFacade = (EmpresaFacade) this.getFacade();
            List<Oficina> selectedOficinaList = ejbFacade.findOficinaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Oficina_items", selectedOficinaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/oficina/index";
    }

    /**
     * Sets the "selected" attribute of the TipoEmpresa controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdetem(ActionEvent event) {
        Empresa selected = this.getSelected();
        if (selected != null && idetemController.getSelected() == null) {
            idetemController.setSelected(selected.getIdetem());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoRegimen controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdetre(ActionEvent event) {
        Empresa selected = this.getSelected();
        if (selected != null && idetreController.getSelected() == null) {
            idetreController.setSelected(selected.getIdetre());
        }
    }

}
