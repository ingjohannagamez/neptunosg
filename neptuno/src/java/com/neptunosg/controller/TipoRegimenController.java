package com.neptunosg.controller;

import com.neptunosg.entity.TipoRegimen;
import com.neptunosg.entity.Empresa;
import java.util.List;
import com.neptunosg.facade.TipoRegimenFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoRegimenController")
@ViewScoped
public class TipoRegimenController extends AbstractController<TipoRegimen> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEmpresaListEmpty;

    public TipoRegimenController() {
        // Inform the Abstract parent controller of the concrete TipoRegimen Entity
        super(TipoRegimen.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEmpresaListEmpty();
    }

    public boolean getIsEmpresaListEmpty() {
        return this.isEmpresaListEmpty;
    }

    private void setIsEmpresaListEmpty() {
        TipoRegimen selected = this.getSelected();
        if (selected != null) {
            TipoRegimenFacade ejbFacade = (TipoRegimenFacade) this.getFacade();
            this.isEmpresaListEmpty = ejbFacade.isEmpresaListEmpty(selected);
        } else {
            this.isEmpresaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empresa entities that are
     * retrieved from TipoRegimen and returns the navigation outcome.
     *
     * @return navigation outcome for Empresa page
     */
    public String navigateEmpresaList() {
        TipoRegimen selected = this.getSelected();
        if (selected != null) {
            TipoRegimenFacade ejbFacade = (TipoRegimenFacade) this.getFacade();
            List<Empresa> selectedEmpresaList = ejbFacade.findEmpresaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empresa_items", selectedEmpresaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/empresa/index";
    }

}
